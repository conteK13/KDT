package scheduler;

import java.io.IOException;

public class SchdulerTest {
	// page 324-325 SchdulerTest.java 구현
	
	public static void main(String[] args) throws IOException{
		System.out.println("전화 상담 할당 방식을 선택하세요.");
		System.out.println("R : 한명씩 차례로 할당");
		System.out.println("L : 쉬고 있거나 대기가 가장 적은 상담원에게 할당");
		System.out.println("P : 우선순위가 높은 고객 먼저 할당");
		System.out.println("A : 상담원 할당");
		
		// 전화 상담 할당 방식을 입력받아 (int)ch 변수에 대입
		int ch = System.in.read();		//입력받은 문자의 ASCII 코드(int)를 반환
		
		Scheduler scheduler = null;
		
		// .equal()을 쓰지 않고 ==를 사용하는 이유는 char이기 때문.
		// char는 int형태로 자동으로 형변환이 된다 -> (int) 'R' 
		if(ch == 'R' || ch == 'r') {
			// 입력 받은 값이 R 또는 r이면 RoundRobin 클래스 생성
			scheduler = new RoundRobin();
		}else if(ch == 'L' || ch == 'l') {
			// 입력 받은 값이 L 또는 l이면 LeastJob 클래스 생성
			scheduler = new LeastJob();
		}else if(ch == 'P' || ch == 'p') {
			// 입력 받은 값이 P 또는 p이면 PriorityAllocation 클래스 생성
			scheduler = new PriorityAllocation();
		}else if (ch == 'A' || ch == 'a') {
			scheduler = new AgentGetCall();
		}else {
			System.out.println("지원되지 않는 기능입니다.");
			return;
		}
		
		//어떤 클레스인가 상관 없이 인터페이스에서 선언한 메서드 호출
		scheduler.getNestCall();
		scheduler.sendCallToAgent();

	}

}
