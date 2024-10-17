package interfaceex2;

public abstract class Calculator implements Calc {
	// page 316 Calculator.java 구현
	
	// abstract 추상클래스로 선언된 이유 : Calc의 모든 함수를 정의하고 있지 않음.
	@Override
	public int add(int num1, int num2) {
		return num1+ num2;
	}
	
	@Override
	public int substract(int num1, int num2) {
		return num1-num2;
	}
}
