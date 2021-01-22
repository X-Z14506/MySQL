import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with Intellij IDEA
 * Description:
 * Users:123
 * Date:2020-11-28
 * Time:16:31
 */
public class TestDemo {
    public static void main(String[] args) throws SQLException {
        //0.前提
        DataSource dataSource=new MysqlDataSource();
        MysqlDataSource mysqlDataSource=(MysqlDataSource)dataSource;
        mysqlDataSource.setUrl("jdbc:mysql://localhost:3306/chongci?user=root&password=1450618603&useUnicode=true&useSSL=false&characterEncoding=UTF-8");

        //1.需要一个数据库链接对象Connextion
        Connection connection=dataSource.getConnection();

        //2.处理语句Statment
        String sql="select id,name,role,salary from emp where id=?";
        PreparedStatement statement= (PreparedStatement) connection.prepareStatement(sql);
        statement.setString(1,"1");

        //3.存放查询结果的集合ResultSet
        ResultSet resultSet=statement.executeQuery();

        //4.打印结果
        if(resultSet.next()){
            int id=resultSet.getInt("id");
            String name=resultSet.getString("name");
            String role=resultSet.getString("role");
            BigDecimal salsry=resultSet.getBigDecimal("salary");
            System.out.println(id);
            System.out.println(name);
            System.out.println(role);
            System.out.println(salsry);
        }

        //关闭连接对象

    }



}
