package librarymanagment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;

/**
 *
 * @author adicom
 */
public class SqlHelper {
    
    public static void insert(int id, String name, String cat, String writer,
            int year, int price, int number) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:library");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "INSERT INTO book (id,name,category,writer,year,price,number) " +
                         "VALUES ("+id+",'"+name+"','"+cat+"','"+writer+"',"+year+","+price+","+number+");"; 
            stmt.executeUpdate(sql);
            c.commit();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        } finally {
            try {
                stmt.close();
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(SqlHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Insert into database successfully");
    }
    
    public static String select(int index) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:library");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM book WHERE id=" + index + ";" );
            if(rs.next()) {
                JSONObject obj = new JSONObject();
                obj.put("id", rs.getInt("id"));
                obj.put("name", rs.getString("name"));
                obj.put("category", rs.getString("category"));
                obj.put("writer", rs.getString("writer"));
                obj.put("year", rs.getInt("year"));
                obj.put("price", rs.getInt("price"));
                obj.put("number", rs.getInt("number"));
                return obj.toString();
            } else {
                System.out.println("not exist record.");
            }
            rs.close();
        } catch ( ClassNotFoundException | SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        } finally {
            try {
                stmt.close();
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(SqlHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Operation done successfully");
        return null;
    }

    public static void delete(int index) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:library");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "DELETE from book where id="+ index +";";
            stmt.executeUpdate(sql);
            c.commit();
        } catch ( ClassNotFoundException | SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }  finally {
            try {
                stmt.close();
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(SqlHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Operation done successfully");
    }
    
    public static void update(int id, String name, String cat, String writer,
            int year, int price, int number) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:library");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "UPDATE book set name='"+name+"'"
                    + ",category='"+cat+"',writer='"+writer+"'"
                    + ",year="+year+",price="+price+",number="+number
                    + " where id="+id+";";
            stmt.executeUpdate(sql);
            c.commit();
        } catch ( ClassNotFoundException | SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }  finally {
            try {
                stmt.close();
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(SqlHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Operation done successfully");
    }
}
