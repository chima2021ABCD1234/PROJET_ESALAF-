package ma.fst.model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseCon {
    static String user = "root";
    static String password = "";
    static String url ="jdbc:mysql://localhost:3306/Esalaf";
    static String driver = "com.mysql.cj.jdbc.Driver";

    public static Connection getCon(){
        Connection con = null;
        try {
            Class.forName(driver);
            try {
                con = DriverManager.getConnection(url,user,password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return con;
    }
}
