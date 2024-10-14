package j241014;

public class Circle {
	int radius;			//멤버 변수
	String name;		//멤버 변수
	
	public Circle() {	//생성자 함수(Constructor)
		// 생성자 함수를 만들때는 class와 같은 이름을 사용
		radius=1;
		name = "";
	}
	
	public Circle(int r) {		//생성자 함수 오버로딩(Function OverLoading)
		// 같은 이름의 함수가 두개이다 -> 함수 오버로딩
		radius = r;
		name="";
	}
	
	public Circle(int r, String n) {		//생성자 함수 오버로딩(Function OverLoading)
		radius = r;
		name=n;
	}
	
	public double getArea() {				
		return 3.14*radius*radius;
	}
	
	public double getAround() {
		return 2*3.14*radius;
	}
	
	public void printLine() {
		System.out.println("------------------------");
	}
	
	public void copyrights() {
		System.out.println("Program Maker : csko");
		System.out.println("Email : csko@naver.com");
	}
	
	public static void main(String[] args) {
		Circle pizza = new Circle();	//기본 생성자 함수를 활용해서 객체 생성
		pizza.name = "피자";
		Circle donut = new Circle(5);	// 오버로딩 된 생성자 함수를 활용해서 객체생성
		donut.name = "도넛";
		Circle coin = new Circle(2, "코인");// 오버로딩 된 생성자 함수를 활용해서 객체생성
		
		pizza.printLine();
		System.out.println(pizza.name+"의 반지름은 " + pizza.radius);
		System.out.println(donut.name+"의 반지름은 "+donut.radius);
		System.out.println(coin.name+"의 반지름은 "+coin.radius);
		pizza.printLine();
		
		System.out.println(pizza.name+"의 면적은 "+pizza.getArea());
		System.out.println(donut.name+"의 면적은 "+donut.getArea());
		System.out.println(coin.name+"의 면적은 "+coin.getArea());
		donut.printLine();
		
		System.out.println(pizza.name + "의 둘레는 " + pizza.getAround());
		System.out.println(donut.name + "의 둘레는 " + donut.getAround());
		System.out.println(coin.name + "의 둘레는 " + coin.getAround());
		coin.printLine();
		
		coin.copyrights();
	}

}
