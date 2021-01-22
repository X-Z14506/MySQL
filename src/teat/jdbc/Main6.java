package teat.jdbc;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;

/**
 * Created with Intellij IDEA
 * Description:
 * Users:123
 * Date:2020-11-20
 * Time:10:22
 */
public class Main6 {
    public static void main(String[] args) {
        query("马云' or '1' = '1");//使用preparedStatement可以防止sql注入
    }

    public static void query(String queryName) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            //创建数据库连接池
            DataSource dataSource = new MysqlDataSource();
            ((MysqlDataSource) dataSource).setUrl("jdbc:mysql://localhost:3306/chongci?user=root&password=1450618603&useUnicode=true&useSSL=false&characterEncoding=UTF-8");
            // 第一步：创建数据库连接
            connection = dataSource.getConnection();
            System.out.println(connection);
            String sql = "select id,name,role,salary from emp where name like ?";
            // 第二步：创建了操作命令对象（是、带占位符？的sql再数据库中预编译，可以提高效率，占位符的方式，可以防止sql注入）
            statement = connection.prepareStatement(sql);

            //替换占位符
            statement.setString(1, "%" + queryName + "%");

            System.out.println(statement);
            // 第三步：执行sql
            resultSet = statement.executeQuery();

            // 第四步：处理结果集ResultSet（类似List<Map<String, 字段类型>>）
            while (resultSet.next()) {//遍历每一行数据
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String role = resultSet.getString("role");
                BigDecimal salary = resultSet.getBigDecimal("salary");
                System.out.printf("id=%s, name=%s, role=%s, salary=%s%n",
                        id, name, role, salary);
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
