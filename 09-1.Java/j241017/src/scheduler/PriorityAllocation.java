package scheduler;

//고객 등급이 높은 고객의 전화부터 대기열에서 가져와 업무 능력이 높은 상담원 우선 배분
public class PriorityAllocation implements Scheduler {
	// page 323-324 PriorityAllocation.java 구현
	
	@Override
	public void getNestCall() {
		System.out.println("고객 등급이 높은 고객의 전화를 먼저 가져옵니다.");
	}

	@Override
	public void sendCallToAgent() {
		System.out.println("업무 skill 값이 높은 상담원에게 우선적으로 배분합니다.");
	}

}
