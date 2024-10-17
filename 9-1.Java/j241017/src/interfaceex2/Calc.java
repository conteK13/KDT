package interfaceex2;

public interface Calc {
	// page 315 Calc.java 구현
	// page 328 Calc.java 내용 추가
	// page 330 Calc.java 내용 추가
	// page 332 Calc.java 내용 추가
	
	
	// 인터페이스에서 선언한 변수는 컴파일 과정에서 상수로 변환됨
	double PI = 3.14;
	int ERROR = -999999999;
		
	// 인터페이스에서 선언한 메서드는 컴파일 과정에서 추상메서드로 변화됨
	int add(int num1, int num2);
	int substract(int num1, int num2);
	int times(int num1, int num2);
	int divide(int num1, int num2);
	int square(int num);
	
	default void description() {
		System.out.println("정수 계산기를 구현합니다.");
		myMethod();	// private 함수를 실행시키기 위한 코드
	}
	
	//static은 '정적 메서드'라고 부름.
	// 객체를 생성하지 않아도 사용 가능한 함수. 객체와 무관한 기능을 제공 (ex. math.random())
	// 인스턴스(필드)의 상태에 의존하지 않기 때문에 상태를 유지하거나 변경할 필요가 없는 경우 사용.
	static int total(int[] arr) {
		int total = 0;
		
		for(int i : arr) {
			total +=i;
		}
		myStaticMethod(); // private static 함수를 실행시키기 위한 코드
		return total;
	}
	
	private void myMethod() {
		System.out.println("private 메서드입니다.");
	}
	
	private static void myStaticMethod() {
		System.out.println("private static 메서드입니다.");
	}
}
