package interfaceex3;

public class CustomerTest {
	public static void main(String[] args) {
		// page 334 구현
		// page 336 내용 추가
		
		Customer customer = new Customer();
		
		// Customer 클레스형에서 Buy 인터페이스형(Customer형이 Buy 인터페이스를 구현함)를 대입하여 형 변환
		Buy buyer = customer;
		buyer.buy();
		buyer.order();	//buyer가 Buy 인터페이스로 형변환 하였습지만, Customer에서 재정의한 메서드가 호출됨
		
		// Customer 클레스형에서 Sell 인터페이스형(Customer형이 sell 인터페이스를 구현함)를 대입하여 형 변환
		Sell seller = customer;
		seller.sell();
		seller.order();
		
		if (seller instanceof Customer) {	// 객체가 특정 타입에 속하는지 확인
			// seller가 Customer 객체라면, Customer타입으로 다시한번 형변환
			Customer customer2 = (Customer)seller;	
			customer2.buy();
			customer2.sell();
		}
	customer.order();
	}
}
