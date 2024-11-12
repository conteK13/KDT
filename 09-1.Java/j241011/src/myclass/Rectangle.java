package myclass;

public class Rectangle {
	int width;
	int height;

	int getArea() {
		return width * height;
	}
	
	void setWidth(int w) {
		width = w;
	}
	
	int getWidth() {
		return width;
	}
	
	void setHeight(int h) {
		height = h;
	}
	
	int getHeight() {
		return height;
	}
	
	int getArea2() {
		return getWidth() * getHeight();
	}
}
