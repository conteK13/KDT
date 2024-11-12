package myclass;

public class RectMain {

	public static void main(String[] args) {
		Rectangle rect = new Rectangle(); // 생성자 함수를 활용해서 객체를 생성함

		rect.setWidth(100);					// 가로값 세팅
		rect.setHeight(13);					// 세로값 세팅
		
		int area1 = rect.getArea();			// member 변수에 직접 접근해서 너비값을 구함 
		int area2 = rect.getArea2();		// member 변수에 getter() 함수로 접근해서 너비값을 구함
		
		System.out.println(area1);
		System.out.println(area2);	
	}
}
