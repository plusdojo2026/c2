package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.User;

public class UserDAO {

	
	private final String URL = "jdbc:mysql://localhost:3306/test_aibou?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9";

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
	public boolean update(int charaId, String userId) {

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
			ps.setString(2, userId);

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
	
	// type_id取得
	public int getTypeId(int userId) {

		int typeId = 0;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection conn =
					DriverManager.getConnection(
							URL,
							USER,
							PASS);

			String sql =
					"SELECT c.type_id "
					+ "FROM user u "
					+ "INNER JOIN chara c "
					+ "ON u.chara_id = c.chara_id "
					+ "WHERE u.user_id = ?";

			PreparedStatement ps =
					conn.prepareStatement(sql);

			ps.setInt(1, userId);

			ResultSet rs =
					ps.executeQuery();

			if (rs.next()) {
				typeId = rs.getInt("type_id");
			}

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return typeId;
	}
	
	//user_idを使ってユーザー情報を取得
	public User findByUserId(int userId) {
		
		Connection conn = null;
		User user = null;
		
		try {
			//SQLへ接続
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL,USER,PASS);
			//SQL文を準備
			String sql = "SELECT * FROM user WHERE user_id=?";
			//SQLを実行
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1, userId);
			
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next()) {
				user = new User(
						rs.getInt("user_id"),
						rs.getString("login_id"),
						rs.getString("PW"),
						rs.getInt("chara_id"),
						rs.getString("user_nickname"),
						rs.getString("chara_nickname")
						);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		//結果を返す
		return user;
	}
}