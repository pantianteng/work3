package tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class DatabaseConnection {
    // 数据库参数配置文件名
    private static final String JDBCPROPERTY = "jdbc.properties";
    // 准备数据库的四大参数：
    private static String DBDRIVER = "";
    private static String DBURL = "";
    private static String DBUSER = "";
    private static String PASSWORD = "";


    public static Connection getConnection() {
        Properties property = new Properties();
        InputStream is = DatabaseConnection.class.getClassLoader().getResourceAsStream("resource/"+JDBCPROPERTY);
        try {
            property.load(new InputStreamReader(is, "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DBDRIVER = property.getProperty("DBDRIVER");
        DBURL = property.getProperty("DBURL");
        DBUSER = property.getProperty("DBUSER");
        PASSWORD = property.getProperty("PASSWORD");
        Connection con = null;
        try {
            Class.forName(DBDRIVER);// 加载驱动，只需注册一次就行
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(DBURL, DBUSER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void closeAll(ResultSet rs, PreparedStatement pst ,Connection con) {
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}