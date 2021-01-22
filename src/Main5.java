import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created with Intellij IDEA
 * Description:
 * Users:123
 * Date:2020-11-20
 * Time:9:36
 */
public class Main5 {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            //创建数据库连接池
            DataSource dataSource = new MysqlDataSource();
            ((MysqlDataSource) dataSource).setUrl("jdbc:mysql://localhost:3306/chongci?user=root&password=1450618603&useUnicode=true&useSSL=false&characterEncoding=UTF-8");
            // 第一步：创建数据库连接
            connection = dataSource.getConnection();
            System.out.println(connection);

            // 第二步：创建了操作命令对象
            statement = connection.createStatement();

            // 第三步：执行sql
            resultSet = statement.executeQuery("select name from student");

            // 第四步：处理结果集ResultSet（类似List<Map<String, 字段类型>>）
            while (resultSet.next()) {//遍历每一行数据
//                int id = resultSet.getInt("id");
//                int sn = resultSet.getInt("sn");
//                int class_id = resultSet.getInt("class_id");
                String name = resultSet.getString("name");
//                String qq_mail = resultSet.getString("qq_mail");
                System.out.printf("name=%s%n", name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }
}
