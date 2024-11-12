package test2;

public class Mypro1 {

	public static void main(String[] args) {
		Mypro1 a = new Mypro2();
		Mypro1 b = new Mypro2();
		System.out.println(a.compute(5,3)+b.compute(5,3));
	}
	
	int compute(int x, int y) {
		return x+y;
	}

}

class Mypro2 extends Mypro1{
	int compute(int x, int y) {
		return x-y +super.compute(x, y);		// x-y + (x+y)
	}
}