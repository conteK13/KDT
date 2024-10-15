package test2;

public class Student {
	private String department;
	private int stid;
	
	public Student(){
	}
	
	public Student(String department, int stid){
		this.department = department;
		this.stid = stid;
	}
	
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getStid() {
		return stid;
	}

	public void setStid(int stid) {
		this.stid = stid;
	}

	@Override
	public String toString() {
		return "Student [department=" + department + ", stid=" + stid + "]";
	}

	
}
