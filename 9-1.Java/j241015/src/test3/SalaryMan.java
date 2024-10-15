package test3;

public class SalaryMan {
	private int salary;
	
	public SalaryMan() {
		this.salary = 2300000;
	}
	
	public SalaryMan(int salary) {
		this.salary= salary;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	public int getAnnualGross() {
		return (salary *17);
	}

}
