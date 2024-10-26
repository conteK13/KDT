package com.kosmo.model;

import java.sql.Date;

// DTO Data Transfer Object : OB또는 html에서 넘어온 데이터를 담는 객체
public class MemberDTO {
	private int idx;
	private String name;
	private String userId;
	private String userPw;
	private String email;
	private int mstate;
	private java.sql.Date indate;
	
	//기본 생성자
	public MemberDTO() {
		
	}
	
	// 인자 생성자(매개변수 생성자)
	public MemberDTO(int idx, String name, String userId, String userPw, String email, int mstate, Date indate) {
		super();
		this.idx = idx;
		this.name = name;
		this.userId = userId;
		this.userPw = userPw;
		this.email = email;
		this.mstate = mstate;
		this.indate = indate;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getMstate() {
		return mstate;
	}

	public void setMstate(int mstate) {
		this.mstate = mstate;
	}

	public java.sql.Date getIndate() {
		return indate;
	}

	public void setIndate(java.sql.Date indate) {
		this.indate = indate;
	}
	
	// 회원의 상태 정보를 문자열로 반환하는 메서드
	public String getMstateStr() {
		String str = "활동회원";
		if(mstate ==0) {
			str = "정지회원";
		}else if (mstate == -1) {
			str = "탈퇴회원";
		}else if (mstate == 9) {
			str = "관리자";
		}
		return str;
	}

}
