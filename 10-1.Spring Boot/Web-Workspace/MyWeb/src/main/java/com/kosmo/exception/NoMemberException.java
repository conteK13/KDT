package com.kosmo.exception;


// 사용자 정의 예외 - 회원이 아닐 경우 발생시킨다.(아이디 또는 비밀번호가 틀릴 경우)
public class NoMemberException extends Exception{
	public NoMemberException() {
		super("회원이 아닙니다");
	}
	
	public NoMemberException(String msg) {
		super(msg);
	}

}
