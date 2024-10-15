package test2;

public class StudentMain {

	public static void main(String[] args) {
		Student std1 = new Student();
		std1.setStid(20173751);
		std1.setDepartment("컴퓨터공학과");
		
		Student std2 = new Student("정보통신과", 20142950);

		System.out.println("학번 "+std1.getStid() + "의 학과는 "+std1.getDepartment());
		System.out.println("학번 "+std2.getStid() + "의 학과는 "+std2.getDepartment());

		System.out.println(std1);
		System.out.println(std2);
	}

}
