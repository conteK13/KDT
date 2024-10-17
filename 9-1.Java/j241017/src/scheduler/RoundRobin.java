package scheduler;

// 상담원 한 명씩 돌아가며 동일하게 상담 업무 배문
public class RoundRobin implements Scheduler {
	// page 322-323 RoundRobin.java 구현
	
	@Override
	public void getNestCall() {
		System.out.println("상담 전화를 순서대로 대기열에서 가져옵니다.");
	}

	@Override
	public void sendCallToAgent() {
		System.out.println("다음 순서 상담원에게 배분합니다.");
	}
}
