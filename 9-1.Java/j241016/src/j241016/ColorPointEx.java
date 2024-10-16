package j241016;

public class ColorPointEx {

	public static void main(String[] args) {
		Point p = new Point();
		p.set(1, 2);
		p.showPoint();
		
		ColorPoint cp = new ColorPoint();
		cp.set(3, 4);
		cp.setColor("red");
		cp.showColorPoint();
		
		ColorPoint cp2 = new ColorPoint(10,20,"Blue");
		cp2.showColorPoint();
		
		ColorPoint cp_test = new ColorPoint(5,6,"blue");
		cp_test.showColorPoint();
	}

}
