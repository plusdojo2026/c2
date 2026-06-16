package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

	private final String URL =
			"jdbc:mysql://localhost:3306/test_aibou?"
			+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9";

	private final String USER = "root";
	private final String PASS = "password";

	// ログイン判定
	public boolean isLoginOK(String loginId, String pw) {

		boolean result = false;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection conn =
					DriverManager.getConnection(
							URL,
							USER,
							PASS);

			String sql =
					"SELECT COUNT(*) "
					+ "FROM user "
					+ "WHERE login_id = ? "
					+ "AND PW = ?";

			PreparedStatement ps =
					conn.prepareStatement(sql);

			ps.setString(1, loginId);
			ps.setString(2, pw);

			ResultSet rs =
					ps.executeQuery();

			if (rs.next()) {
				result =
						rs.getInt(1) == 1;
			}

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// user_id取得
	public int getUserId(String loginId) {

		int userId = 0;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection conn =
					DriverManager.getConnection(
							URL,
							USER,
							PASS);

			String sql =
					"SELECT user_id "
					+ "FROM user "
					+ "WHERE login_id = ?";

			PreparedStatement ps =
					conn.prepareStatement(sql);

			ps.setString(1, loginId);

			ResultSet rs =
					ps.executeQuery();

			if (rs.next()) {
				userId =
						rs.getInt("user_id");
			}

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return userId;
	}

	// キャラ変更
	public boolean update(
			int chara_id,
			String user_id) {

		boolean result = false;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection conn =
					DriverManager.getConnection(
							URL,
							USER,
							PASS);

			String sql =
					"UPDATE user "
					+ "SET chara_id = ? "
					+ "WHERE user_id = ?";

			PreparedStatement ps =
					conn.prepareStatement(sql);

			ps.setInt(1, chara_id);
			ps.setString(2, user_id);

			result =
					ps.executeUpdate() > 0;

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}