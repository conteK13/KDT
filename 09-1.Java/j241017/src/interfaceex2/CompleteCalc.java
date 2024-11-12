package interfaceex2;

public class CompleteCalc extends Calculator {
	// page 317 CompleteCalc.java 구현
	// page 329 CompleteCalc.java 내용 추가
	
	@Override
	public int times(int num1, int num2) {
		return num1 * num2;
	}
	
	@Override
	public int divide(int num1, int num2) {
		if (num2!=0) // 정수를 0으로 나누는 경우를 필터링하기 위한 if문
			return num1/num2;
		else // num2=0 인 경우 ERROR 호출 
			return Calc.ERROR;
	}
	
	@Override
	public int square(int num) {
		return num * num;	
	}
	
	
	public void showInfo() {
		// CompleteCalc에서 추가로 구현한 메소드 -> Super class나 interface엔 포함되지 않은 내용
		System.out.println("Calc 인터페이스를 구현하였습니다.");
	}

	
	// alt+shift+s :Override/Implement Methods... 클릭 -> Calc의 description 선택 
	@Override
	public void description() {
		// TODO Auto-generated method stub
		super.description();
	}
	
}
