package interfaceex3;

public interface Sell {
	// page 333 Sell.java 구현
	// page 335 Sell.java 내용 추가
	
	void sell();
	
	default void order() {
		System.out.println("구매 주문");
	}
}
