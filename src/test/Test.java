package test;

import cn.itcase.dao.UserDao;
import cn.itcase.dao.impl.UserDaoImpl;
import cn.itcase.domain.User;
import cn.itcase.servise.UserServise;
import cn.itcase.servise.impl.UserServiceImpl;
import cn.itcase.util.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import sun.util.resources.cldr.ml.CalendarData_ml_IN;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Test {

    // 测试JDBC工具连接
    @org.junit.Test
    public void testJDBCUtils(){
        try {
            Connection c = JDBCUtils.getConnection();
            JDBCUtils.close(null, c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //测试dao层findall
    @org.junit.Test
    public void testFindAllDAO(){
        UserDao dao = new UserDaoImpl();
        List<User> users = dao.findAll();
        System.out.println(users);
    }

    @org.junit.Test
    public void testUserServise(){
        UserServise userServise = new UserServiceImpl();
        System.out.println(userServise.findAll());
    }

    @org.junit.Test
    public void testfindUserByUsernameAndPassword(){
        UserDao dao = new UserDaoImpl();
        User user = dao.findUserByUsernameAndPassword("root", "admin");
        System.out.println(user);
    }

    @org.junit.Test
    public void testDelUserByid(){
        UserServise servise = new UserServiceImpl();
        servise.delUserById(10);
    }

    @org.junit.Test
    public void testUpdate(){
        UserDao userDao = new UserDaoImpl();
        
    }

    //此测试只是为了添加测试数据
    @org.junit.Test
    public void addData(){
        String sql = "INSERT INTO USER(NAME,gender,age,address,qq,email) VALUE( ?, ?, ?, ?, ?, ?)";
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String data ="中文";
        for( int i =0; i< 10; i++){
            template.update(sql,data,data,i,data,data,data);
        }
    }

}
