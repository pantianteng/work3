package dao;

import tools.DatabaseConnection;
import vo.City;
import vo.Province;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProvinceCityDao {
    public static ArrayList<Province> queryProvince() {
        Connection con = DatabaseConnection.getConnection();
        ArrayList<Province> provinces = new ArrayList<>();

        String sql = "select * from t_province";
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
                String provinceCode = rs.getString("provinceCode");
                String provinceName = rs.getString("provinceName");
                Province province = new Province(provinceCode, provinceName);
                provinces.add(province);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return provinces;
    }

    public static ArrayList<City> queryCityByProvinceCode(String provinceCode){
        Connection con = DatabaseConnection.getConnection();
        ArrayList<City> cities = new ArrayList<>();

        String sql = "select * from t_city\n" +
                     "where provinceCode = ?";

        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,provinceCode);
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
                String cityCode = rs.getString("cityCode");
                String cityName = rs.getString("cityName");
                String provinceCode1 = rs.getString("provinceCode");
                City city = new City(cityCode, cityName, provinceCode1);
                cities.add(city);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        DatabaseConnection.closeAll(rs,ps,con);
        return cities;
    }

}
