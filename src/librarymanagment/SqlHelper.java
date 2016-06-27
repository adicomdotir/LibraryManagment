package librarymanagment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author adicom
 */
public class SqlHelper {

    public static void insert(String query) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:library");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = query;
            stmt.executeUpdate(sql);
            c.commit();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
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

    public static String select(String query) {
        Connection c = null;
        Statement stmt = null;
        ResultSet rs = null;
        String strTemp = "";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:library");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            rs = stmt.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();
            if (rs.next()) {
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    strTemp += rs.getObject(i) + "&";
                }
                System.out.println(rs);
            } else {
                System.out.println("not exist record.");
                JOptionPane.showMessageDialog(null, "عضوی به این شماره وجود ندارد.", "پیغام", JOptionPane.INFORMATION_MESSAGE);
            }
            rs.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
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
        return strTemp;
    }

    public static void delete(String query) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:library");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            stmt.executeUpdate(query);
            c.commit();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
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
    }

    public static void update(String query) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:library");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            
            stmt.executeUpdate(query);
            c.commit();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
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
    }
}

interface ResultSetConsumer<T> {
    public T consume(ResultSet rs);
}