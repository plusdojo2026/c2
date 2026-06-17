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

		String checkSql = "SELECT COUNT(*) FROM daily_mission WHERE user_id = ? AND daily = CURDATE()";

		String deleteSql = "DELETE FROM daily_mission WHERE user_id = ? AND daily = CURDATE()";

		String insertSql = "INSERT INTO daily_mission (user_id, mission_id, daily, complete) "
				+ "SELECT ?, mission_id, CURDATE(), 0 " + "FROM mission WHERE mission_name = ?";

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

//ホーム画面でのミッション取得
	public String[] getTodayMissions(int userId) {

		System.out.println("getTodayMissions開始");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver読込成功");
		} catch (Exception e) {
			System.out.println("Driver読込失敗");
			e.printStackTrace();
		}

		String sql = "SELECT m.mission_name " + "FROM daily_mission d " + "INNER JOIN mission m "
				+ "ON d.mission_id = m.mission_id " + "WHERE d.user_id = ? " + "AND d.daily = CURDATE()";

		String[] missions = new String[3];

		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();

			int i = 0;

			while (rs.next() && i < 3) {
				missions[i] = rs.getString("mission_name");

				System.out.println("取得=" + missions[i]);

				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return missions;
	}

	// 今日の達成状況取得
	public int[] getTodayCompletes(int userId) {

		int[] completes = {-1, -1, -1};

		String sql = "SELECT complete " + "FROM daily_mission " + "WHERE user_id = ? " + "AND daily = CURDATE()";

		try (Connection con = DriverManager.getConnection(URL, USER, PASS);

				PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();

			int i = 0;

			while (rs.next() && i < 3) {

				completes[i] = rs.getInt("complete");

				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return completes;
	}

	// 今日達成したミッション数取得
	public int getTodayCompleteCount(int userId) {

		int count = 0;

		String sql = "SELECT COUNT(*) " + "FROM daily_mission " + "WHERE user_id = ? " + "AND daily = CURDATE() "
				+ "AND complete = 1";

		try (Connection con = DriverManager.getConnection(URL, USER, PASS);

				PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;
	}
}