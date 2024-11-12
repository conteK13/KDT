package interfaceex4;

public class MyClassTest {
	
	public static void main(String[] args) {
		// page 338-339 구현
		
		MyClass mClass = new MyClass();
		
		// 상위 인터페이스 X형으로 대입하면 X에 선언한 메서드만 호출 가능
		X xClass = mClass;
		xClass.x();
		
		// 상위 인터페이스 Y형으로 대입하면 Y에 선언한 메서드만 호출 가능
		Y yClass = mClass;
		yClass.y();
		
		// X 인터페이스와 Y 인터페이스를 모두 상속받은 MyInterface로 대입하면 모든 메소드 호출 가능 
		MyInterface iClass = mClass;
		iClass.myMethod();
		iClass.x();
		iClass.y();
	}

}
