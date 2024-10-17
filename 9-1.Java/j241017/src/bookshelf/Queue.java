package bookshelf;

public interface Queue {
	// page 341 Queue.java 구현
	
	void enQueue(String title);		// 배열의 마지막 위치에 요소 추가
	String deQueue();				// 맨 앞의 요소를 제거하고 반환
	int getSize();
}
