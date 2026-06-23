package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HomeDAO {

	private final String URL = "jdbc:mysql://localhost:3306/test_aibou?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9";

	private final String USER = "root";
	private final String PASS = "password";

	// ホーム画面ミッションの達成有無の登録
	public boolean updateComplete(int userId, String missionName, boolean complete) {

		System.out.println("===== updateComplete開始 =====");
		System.out.println("userId=" + userId);
		System.out.println("missionName=" + missionName);
		System.out.println("complete=" + complete);

		String sql = "UPDATE daily_mission d " + "INNER JOIN mission m ON d.mission_id = m.mission_id "
				+ "SET d.complete = ? " + "WHERE d.user_id = ? " + "AND d.daily = CURDATE() "
				+ "AND m.mission_name = ?";

		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setBoolean(1, complete);
			ps.setInt(2, userId);
			ps.setString(3, missionName);

			return ps.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	/* レーダーチャートへ表示する値を取得 */

	public int[] getRadarData(int userId) {

		int[] data = new int[5];

		String sql = "SELECT t.theme_name, COUNT(*) cnt " + "FROM daily_mission d " + "INNER JOIN mission m "
				+ "ON d.mission_id = m.mission_id " + "INNER JOIN theme t " + "ON m.theme_id = t.theme_id "
				+ "WHERE d.user_id = ? " + "AND d.complete = true " + "GROUP BY t.theme_name";

		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				String theme = rs.getString("theme_name");

				int count = rs.getInt("cnt");

				System.out.println(theme + ":" + count);

				switch (theme) {

				case "生活":
					data[0] = count;
					break;

				case "運動":
					data[1] = count;
					break;

				case "お金":
					data[2] = count;
					break;

				case "趣味":
					data[3] = count;
					break;

				case "勉強":
					data[4] = count;
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}

	public int getTotalClearCount(int userId) {

		int count = 0;

		String sql = "SELECT COUNT(*) cnt " + "FROM daily_mission " + "WHERE user_id = ? " + "AND complete = true";

		try (Connection con = DriverManager.getConnection(URL, USER, PASS);
				PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				count = rs.getInt("cnt");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;
	}

}