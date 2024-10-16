package j241016.updowncasting;

class Person {
	String name;
	String id;

	public Person(String name) {
		this.name = name;
	}
}

class Student extends Person {
	String grade;
	String department;

	public Student(String name) {
		super(name);
	}
}

public class UpcastingEx {

	public static void main(String[] args) {
		Person p;
		Student s = new Student("이재문");
		s.grade = "B";
		s.department = "test";
		p = s; // 업캐스팅 발생
		System.out.println(p.name); // 오류 없음
//		p.grade = "A"; // 컴파일 오류
//		p.department = "Com"; // 컴파일 오류
		
		
		Student t = (Student)p;		
		// p가 person이나 student로 변환할때, 주소는 여전히 같으므로 데이터를 여전히 간직하고 있다.
//		t.grade = "A";
//		t.department = "Com";
		
		System.out.println(t.name);
		System.out.println(t.grade);
		System.out.println(t.department);
		
//		int x = 3;
//		if(x instanceof int) {		//int는 객체가 아닌 자료형이라 x
//			System.out.println("test");
//		}
		//객체와 자료형을 나누는 기준 -> 객체 : 크기가 가변적임
		
	}

}
