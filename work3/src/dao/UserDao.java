package dao;

import tools.DatabaseConnection;
import vo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public User findUserByUserName(String userName){
        User user = null;
        Connection con = DatabaseConnection.getConnection();

        String sql = "select * from t_user";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet rs = null;
        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try{
            while (rs.next()) {
                if (userName.equals(rs.getString("userName"))){
                    String chrName = rs.getString("chrName");
                    String mail = rs.getString("mail");
                    String province = rs.getString("province");
                    String city = rs.getString("city");
                    String password = rs.getString("password");
                    String role = rs.getString("role");
                    user = new User(userName,chrName,mail,province,city,password,role);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        DatabaseConnection.closeAll(rs,ps,con);
        return user;
    }

    public static boolean creatUser(User user){
        boolean flag = false;

        Connection con = DatabaseConnection.getConnection();
        String sql1 = "INSERT into t_user (userName,chrName,mail,province,city,password,role) values(?,?,?,?,?,?,普通用户)";
        String sql2 = "insert into t_role_user (roleId,userName) values(?,?)";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(sql1);
            ps.setString(1,user.getUserName());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getChrName());
            ps.setString(4,user.getMail());
            ps.setString(5,user.getProvince());
            ps.setString(6,user.getCity());

            int count = 0;
            count = ps.executeUpdate();
            System.out.println("t_user数据更新条数：" + count);

            ps = con.prepareStatement(sql2);
            ps.setString(1,"2");
            //注册为普通用户
            ps.setString(2,user.getUserName());
            count = ps.executeUpdate();
            System.out.println("t_role_user数据更新条数：" + count);

            if(count != 0){
                flag = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        DatabaseConnection.closeAll(rs,ps,con);
        return flag;
    }
}
