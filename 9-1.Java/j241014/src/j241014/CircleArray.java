package j241014;

class Circlei {
	int radius;

	public Circlei(int radius) {
		this.radius = radius;
	}

	public double getArea() {
		return 3.14 * radius * radius;
	}
}

public class CircleArray {
	public static void main(String[] args) {
		Circlei[] c;
		c = new Circlei[5];
		for (int i = 0; i < c.length; i++)
			c[i] = new Circlei(i);
		for (int i = 0; i < c.length; i++)
			System.out.print((int) (c[i].getArea()) + " ");
	}

}
