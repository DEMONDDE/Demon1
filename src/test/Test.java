package test;

import cn.itcase.util.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;

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
}
