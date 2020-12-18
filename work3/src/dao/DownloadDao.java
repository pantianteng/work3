package dao;
import tools.DatabaseConnection;
import vo.Download;

import java.sql.Connection;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DownloadDao {

    public static ArrayList<Download> getDownloadList(){
        Connection con = DatabaseConnection.getConnection();
        ArrayList<Download> downloadList = new ArrayList<>();

        String sql = "select * from t_downloadlist";
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
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String icon = rs.getString("icon");
                String info = rs.getString("info");
                String time = rs.getString("time");
                String size = rs.getString("size");
                int star = rs.getInt("star");
                String path = rs.getString("path");
                Download download = new Download(id, name, icon, info, time, size, star, path);
                downloadList.add(download);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        DatabaseConnection.closeAll(rs,ps,con);
        return downloadList;
    }

    public  static Download findDownloadById(int id){
        Connection con = DatabaseConnection.getConnection();
        Download download = null;

        String sql = "select * from t_downloadlist";
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

        try {
            while (rs.next()) {
                if (id == rs.getInt("id")) {
                    String name = rs.getString("name");
                    String icon = rs.getString("icon");
                    String info = rs.getString("info");
                    String time = rs.getString("time");
                    String size = rs.getString("size");
                    int star = rs.getInt("star");
                    String path = rs.getString("path");
                    download = new Download(id, name, icon, info, time, size, star, path);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        DatabaseConnection.closeAll(rs,ps,con);
        return download;
    }

}
