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
				result = rs.getInt(1) == 1;
			}

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// 新規登録
	public boolean insert(String loginId, String pw) {

		boolean result = false;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection conn =
					DriverManager.getConnection(
							URL,
							USER,
							PASS);

			String sql =
					"INSERT INTO user(login_id, PW) "
					+ "VALUES(?, ?)";

			PreparedStatement ps =
					conn.prepareStatement(sql);

			if (loginId != null) {
				ps.setString(1, loginId);
			} else {
				ps.setString(1, "");
			}

			if (pw != null) {
				ps.setString(2, pw);
			} else {
				ps.setString(2, "");
			}


			result = ps.executeUpdate() > 0;

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
				userId = rs.getInt("user_id");
			}

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return userId;
	}

	// キャラ変更
	public boolean update(int charaId, int userId) {

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

			ps.setInt(1, charaId);
			ps.setInt(2, userId);

			result = ps.executeUpdate() > 0;

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	// chara_id取得
	public int getCharaId(int userId) {

		int charaId = 0;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection conn =
					DriverManager.getConnection(
							URL,
							USER,
							PASS);

			String sql =
					"SELECT chara_id "
					+ "FROM user "
					+ "WHERE user_id = ?";

			PreparedStatement ps =
					conn.prepareStatement(sql);

			ps.setInt(1, userId);

			ResultSet rs =
					ps.executeQuery();

			if (rs.next()) {
				charaId = rs.getInt("chara_id");
			}

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return charaId;
	}
}