package model;

import java.io.Serializable;

public class Chara implements Serializable {
	
	private int charaId;
	private String image;
	private String speak;
	private int typeId;
	
	//引数なしコンストラクタ
	public Chara() {
	}
	//全項目コンストラクタ
	public Chara(int charaId,String image,String speak,int typeId) {
		this.charaId = charaId;
		this.image = image;
		this.speak = speak;
		this.typeId = typeId;
	}
	//getter setter
		public int getCharaId() {
			return charaId;
		}

		public void setCharaId(int charaId) {
			this.charaId = charaId;
		}
		
		public String getImage() {
			return image;
		}
		
		public void setImage(String image) {
			this.image = image;
		}
		
		public String getSpeak() {
			return speak;
		}
		
		public void setSpeak(String speak) {
			this.speak = speak;
		}
		
		public int getTypeId() {
			return typeId;
		}
		
		public void setTypeId(int typeId) {
			this.typeId = typeId;
		}

}
