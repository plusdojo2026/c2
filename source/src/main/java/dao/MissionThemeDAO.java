package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MissionThemeDAO {

    private final String URL =
        "jdbc:mysql://localhost:3306/test_aibou?characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Tokyo";

    private final String USER = "root";
    private final String PASS = "password";

    public List<String> findMissionsByThemeName(String themeName) {

        List<String> missions = new ArrayList<>();

        String sql =
            "SELECT m.mission_name " +
            "FROM mission m " +
            "JOIN theme t ON m.theme_id = t.theme_id " +
            "WHERE t.theme_name = ?";

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            try (
                Connection con =
                    DriverManager.getConnection(URL, USER, PASS);

                PreparedStatement ps =
                    con.prepareStatement(sql)
            ) {

                ps.setString(1, themeName);

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    missions.add(rs.getString("mission_name"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return missions;
    }
}