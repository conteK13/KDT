package bookshelf;
import java.util.ArrayList;

public class Shelf {
	// page 340 Shlef.java 구현
	
	// 자료를 순서대로 저장할 ArrayList 선언
	protected ArrayList<String> shelf;
	
	// 디폴트 생성자로 Shelf 클래스를 생성하면 ArrayList도 생성함
	public Shelf() {
		shelf = new ArrayList<String>();
	}
	
	// 'ArrayList<String>' 타입을 반환하는 함수 : getShelf();
	public ArrayList<String> getShelf(){
		return shelf;
	}
	
	public int getCount() {
		return shelf.size();
	}
}
