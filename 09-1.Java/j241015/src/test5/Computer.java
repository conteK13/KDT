package test5;

public class Computer {
	public static final String[] osType = {"윈도11", "애플 OS X", "안드로이드"};
	public int index;
	public int ram;
	
	public Computer() {
	}
	
	public Computer(int index, int ram) {
		this.index = index;
		this.ram = ram;
	}
	
	public void print() {
		System.out.println("운영체제: "+ osType[this.index] +", 메인메모리: "+this.ram+" GB");
	}
	
	public static void main(String[] args) {
		Computer pc = new Computer(0,16);
		Computer apple = new Computer(1,32);
		Computer galaxy = new Computer(2,16);
		
		pc.print();
		apple.print();
		galaxy.print();
	}

}
