package dao;

import tools.DatabaseConnection;
import vo.Resource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ResourceDao {
    public static ArrayList<Resource> findResourceListByUserName(String userName){
        Connection con = DatabaseConnection.getConnection();
        ArrayList<Resource> resourcesList = new ArrayList<>();

        String sql= "SELECT * FROM t_resource\n" +
                    "WHERE\n" +
                    "\tresourceId IN (\n" +
                    "\t\tSELECT resourceId FROM t_role_resource\n" +
                    "\t\tWHERE\n" +
                    "\t\t\troleId IN (\n" +
                    "\t\t\t\tSELECT roleId FROM t_user_role\n" +
                    "\t\t\t\tWHERE\n" +
                    "\t\t\t\t\tuserName = ?\n" +
                    "\t\t\t)\n" +
                    "\t)";

        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,userName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ResultSet rs = null;
        try {
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            while (rs.next()) {
                int resourceId = rs.getInt("resourceId");
                String resourceName = rs.getString("resourceName");
                String url = rs.getString("url");
                Resource resource = new Resource(resourceId,resourceName,url);
                resourcesList.add(resource);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        DatabaseConnection.closeAll(rs,ps,con);

        return resourcesList;
    }

}
