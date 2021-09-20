package com.mimi.vo;

public class Vo {
	private String id;
	private int pw;
	
	public Vo(String id, int pw) {
		this.id = id;
		this.pw = pw;
	}
	


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPw() {
		return pw;
	}
	public void setPw(int pw) {
		this.pw = pw;
	}



	@Override
	public String toString() {
		return "Vo [id=" + id + ", pw=" + pw + "]";
	}

	


}
