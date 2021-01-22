import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with Intellij IDEA
 * Description:
 * Users:123
 * Date:2020-11-20
 * Time:10:48
 */
public class Main7 {
    public static void main(String[] args) {
//        query("马云' or '1' = '1");//使用PreparedStatement可以防止sql注入
        update("马");
    }

    public static void update(String updateName) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //创建数据库连接池
            DataSource dataSource = new MysqlDataSource();
            ((MysqlDataSource) dataSource).setUrl("jdbc:mysql://localhost:3306/chong?user=root&password=1450618603&useUnicode=true&useSSL=false&characterEncoding=UTF-8");
            // 第一步：创建数据库连接
            connection = dataSource.getConnection();
            System.out.println(connection);

            String sql = "update emp set salary=salary+1 where name like ?";
            // 第二步：创建了操作命令对象（带占位符的sql在数据库预编译，可以提高效率，占位符的方式，可以防止sql注入）
            statement = connection.prepareStatement(sql);

            //替换占位符
            statement.setString(1, "%" + updateName + "%");
            System.out.println(statement);
            // 第三步：执行sql，新增/修改/删除都是executeUpdate
            int r = statement.executeUpdate();
            System.out.println(r);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

//    public static void main(String[] args) {
////        query("马云' or '1' = '1");//使用preparedStatement可以防止sql注入
//        update("马");
//    }
//
//    public static void update(String updateName) {
//        Connection connection = null;
//        PreparedStatement statement = null;
//        try {
//            //创建数据库连接池
//            DataSource dataSource = new MysqlDataSource();
//            ((MysqlDataSource) dataSource).setUrl("jdbc:mysql://localhost:3306/chongci?user=root&password=1450618603&useUnicode=true&useSSL=false&characterEncoding=UTF-8");
//            // 第一步：创建数据库连接
//            connection = dataSource.getConnection();
//            System.out.println(connection);
//            String sql = "update emp set salary = salary + 1 where name like ?";
//            // 第二步：创建了操作命令对象（是、带占位符？的sql再数据库中预编译，可以提高效率，占位符的方式，可以防止sql注入）
//            statement = connection.prepareStatement(sql);
//
//            //替换占位符
//            statement.setString(1, "%" + queryName + "%");
//
//            System.out.println(statement);
//            // 第三步：执行sql
//            int ret  = statement.executeUpdate();
//            System.out.println(ret);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            //释放资源
//                if (statement != null)
//                    statement.close();
//                if (connection != null)
//                    connection.close();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }


    }
}
