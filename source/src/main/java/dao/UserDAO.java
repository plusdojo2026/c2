package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

	private final String URL =
			"jdbc:mysql://localhost:3306/test_aibou?"
			+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9";

	private final String USER = "root";
	private final String PASS = "passward";

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
		return result;}

		
		//新規登録
		public boolean insert(String login_id, String PW) {
			Connection conn = null;
			boolean result = false;
			
			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_aibou?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "passward");
				
				// SQL文を準備する
				String sql ="INSERT INTO user (login_id, PW) VALUES (?, ?)";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				// SQL文を完成させる
				
				if (login_id!= null) {
					pStmt.setString(1, login_id);
				} else {
					pStmt.setString(1, "");
				}
				
				if (PW!= null) {
					pStmt.setString(2, PW);
				} else {
					pStmt.setString(2, "");
				}
				
				// SQL文を実行する
				if (pStmt.executeUpdate() == 1) {
					result = true;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} finally {
					
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}

			// 結果を返す
			return result;}

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