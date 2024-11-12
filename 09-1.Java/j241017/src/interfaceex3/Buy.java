package interfaceex3;

public interface Buy {
	// page 333 Buy.java 구현
	// page 335 Buy.java 내용 추가
	
	void buy();
	
	default void order() {
		System.out.println("판매 주문");
	}
}
