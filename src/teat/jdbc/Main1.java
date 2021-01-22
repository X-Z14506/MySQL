package teat.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created with Intellij IDEA
 * Description:
 * Users:123
 * Date:2020-11-18
 * Time:9:55
 */

public class Main1 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/chong?user=root&password=1450618603&useUnicode=true&useSSL=false&characterEncoding=UTF-8");
        System.out.println(connection);
    }
}
