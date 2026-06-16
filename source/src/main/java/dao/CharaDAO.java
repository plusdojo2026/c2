package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Chara;

public class CharaDAO {
	public Chara findByCharaId(int charaId) {
	Connection conn = null;
	Chara chara = null;
	
	try {

		// JDBCドライバを読み込む
		Class.forName("com.mysql.cj.jdbc.Driver");

		// データベースに接続する
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp1?"
			+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
				"root", "daito110");
		
		// SELECT文を準備する
		String sql = "SELECT * FROM chara WHERE chara_id = ?";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		
		//SQl文を完成させる
		pStmt.setInt(1,charaId);
		
		//SELECT文を実行し、結果表（ResultSet）を取得する
		ResultSet rs = pStmt.executeQuery();
		if(rs.next()) {

            chara = new Chara(

                rs.getInt("chara_id"),

                rs.getString("image"),

                rs.getString("speak"),

                rs.getInt("type_id")
            	);
        }

    } catch(Exception e) {

        e.printStackTrace();

    } finally {
        if(conn != null) {
            try {
                conn.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }
		
	
	//結果を返す
	return chara;
	}
	
	//キャラの変更(更新)
			// 更新し、Chara_idを変更する
			public boolean update(int chara_id,String user_id) {
				Connection conn = null;
				boolean result = false;

				try {
					// JDBCドライバを読み込む
					Class.forName("com.mysql.cj.jdbc.Driver");

					// データベースに接続する
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_aibou?"
							+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
							"root", "password");

					// SQL文を準備する
					String sql = "UPDATE user SET chara_id=? WHERE user_id = ?";
					PreparedStatement pStmt = conn.prepareStatement(sql);

					// SQL文を完成させる
					pStmt.setInt(1,chara_id);
					pStmt.setString(2,user_id);
					

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
}

