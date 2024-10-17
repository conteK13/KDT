package interfaceex3;

public class Customer implements Buy, Sell {
	// page 333 Customer.java 구현
	
	@Override
	public void buy() {
		System.out.println("판매하기");
	}

	@Override
	public void sell() {
		System.out.println("구매하기");
	}
	
	// Customer가 참고한 두 인터페이스 Buy, Sell이 동일한 메서드를 사용하고 있으니(중복되었으니)
	// 두 인터페이스를 구현하는 Customer 클래스에서 재 정의하라는 뜻
	@Override
	public void order() {
		System.out.println("고객 판매 주문");
	}
	

}
