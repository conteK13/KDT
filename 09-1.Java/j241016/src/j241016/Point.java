package j241016;

public class Point {
	private int x;
	private int y;

	public Point() {
		this.x = 0;
		this.y = 0;
		System.out.println("초기 x, y 값 : " + this.x+", "+this.y );
	}
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
		System.out.println("초기 x, y 값 : " + this.x+", "+this.y );
	}
	
	public void set(int x, int y) {
		this.x = x;
		this.y = y;
		System.out.println("수정 x, y 값 : " + this.x+", "+this.y );
	}

	public void showPoint() {
		System.out.println("(" + this.x + "," + this.y + ")");
	}
}
