package interfaceex2;

public class CalculatorTest {
	public static void main(String[] args) {
		// page 317-318 CalculatorTest.java 구현
		// page 328 CalculatorTest.java 내용 추가
		// page 330-331 CalculatorTest.java 내용 추가
		
		int num1 = 10;
		int num2 = 5;
		
		CompleteCalc calc = new CompleteCalc();
		System.out.println(calc.add(num1, num2));
		System.out.println(calc.substract(num1, num2));
		System.out.println(calc.times(num1, num2));
		System.out.println(calc.divide(num1, num2));
		
		
		// p319 나혼자 코딩 내용 추가
		// 1. Calc 인터페이스에 'int square(int num)' 메서드 추가
		// 2. square() 함수는 전달된 값의 제곱을 전달하는 메서드
		// 3. CompleteCalc에서 구현한다
		// 4. CompleteCalcTest 클래스에서 메서드 호출
		System.out.println(calc.square(num1)); // p319 나혼자 코딩
		System.out.println(calc.square(num2)); // p319 나혼자 코딩
		
		calc.showInfo();
		calc.description();
		
		// static 함수 호출
		int[] arr = {1,2,3,4,5};
		System.out.println(Calc.total(arr));
	}
}
