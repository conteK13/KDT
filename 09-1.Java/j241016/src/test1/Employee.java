package test1;

public class Employee {
	public String name;
	public String grade;
	
	//생성자 함수 추가 -> 인수를 아무것도 받지 않는 경우에 대한 생성자 함수가 없어서 추가함
	public Employee() {}
	
	public Employee(String name) {
		this.name= name;
	}
}
