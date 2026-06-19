package model;

import java.io.Serializable;


public class User implements Serializable  {

	private int userId;
	private String loginId;
	private String pw;
	private int charaId;
	private String userNickname;
	private String charaNickname;
	
	//引数なしコンストラクタ
	public User() {
	}
	//全項目コンストラクタ
	public User(int userId,String loginId,String pw,int charaId,String userNickname,String charaNickname) {
		this.userId = userId;
		this.loginId = loginId;
		this.pw = pw;
		this.charaId = charaId;
		this.userNickname = userNickname;
		this.charaNickname = charaNickname;
	}
	//getter setter
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getLoginId() {
		return loginId;
	}
	
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	
	public String getpw() {
		return pw;
	}
	
	public void setpw(String pw) {
		this.pw = pw;
	}
	
	public int getCharaId() {
		return charaId;
	}
	
	public void setCharaId(int charaId) {
		this.charaId = charaId;
	}
	
	public String getUserNickname() {
		return userNickname;
	}
	
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	
	public String getCharaNickname() {
		return charaNickname;
	}
	
	public void setCharaNickname(String charaNickname) {
		this.charaNickname = charaNickname;
	}
}
