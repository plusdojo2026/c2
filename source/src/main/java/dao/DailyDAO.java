package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DailyDAO {

	private final String URL = "jdbc:mysql://localhost:3306/test_aibou?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9";

	private final String USER = "root";
	private final String PASS = "password";

	public boolean replaceTodayMissions(int userId, String[] missions) {

	    String checkSql =
	        "SELECT COUNT(*) FROM daily_mission WHERE user_id = ? AND daily = CURDATE()";

	    String deleteSql =
	        "DELETE FROM daily_mission WHERE user_id = ? AND daily = CURDATE()";

	    String insertSql =
	        "INSERT INTO daily_mission (user_id, mission_id, daily, complete) " +
	        "SELECT ?, mission_id, CURDATE(), 0 " +
	        "FROM mission WHERE mission_name = ?";

	    try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {

	        boolean exists;

	        // ① 今日データあるか確認
	        try (PreparedStatement ps = con.prepareStatement(checkSql)) {
	            ps.setInt(1, userId);
	            ResultSet rs = ps.executeQuery();
	            rs.next();
	            exists = rs.getInt(1) > 0;
	        }

	        // ② 1件でもあれば削除（これが「アップデート扱い」）
	        if (exists) {
	            try (PreparedStatement ps = con.prepareStatement(deleteSql)) {
	                ps.setInt(1, userId);
	                ps.executeUpdate();
	            }
	        }

	        // ③ 再登録（0件でもそのままここに来る）
	        try (PreparedStatement ps = con.prepareStatement(insertSql)) {
	            for (String mission : missions) {
	                ps.setInt(1, userId);
	                ps.setString(2, mission);
	                ps.executeUpdate();
	            }
	        }

	        return true;

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return false;
	}
}