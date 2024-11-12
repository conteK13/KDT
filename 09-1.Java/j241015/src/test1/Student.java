package test1;

public class Student {
	public String department;
	public int stid;
	
	public Student(){
	}
	
	public Student(String department, int stid){
		this.department = department;
		this.stid = stid;
	}
	
	public static void main(String[] args) {
		Student std1 = new Student();
		std1.stid = 20173751;
		std1.department = "컴퓨터공학과";
		
		Student std2 = new Student("정보통신과", 20142950);

		System.out.println("학번 "+std1.stid + "의 학과는 "+std1.department);
		System.out.println("학번 "+std2.stid + "의 학과는 "+std2.department);

	}

}
