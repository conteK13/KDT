package test1;

import java.util.Scanner;

class Book{
	String title, author;
	public Book(String title, String author) { //생성자 함수(Constructor)
		this.title = title;
		this.author = author;
	}
}

public class BookArray {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Book [] book = new Book[2];
		
		for(int i =0; i<book.length; i++) {
			System.out.print("제목 >> ");
			String title = scanner.nextLine();
			System.out.print("저자 >> ");
			String author = scanner.nextLine();
			
			book[i] = new Book(title, author);
			// 입력받은 값을 이용하여 i번째 book 객체 배열로 사용
		}
		scanner.close();
		// 리소스 해제
		
		for(int i=0; i<book.length; i++) {
			System.out.println("("+ book[i].title+", "+book[i].author+")");
			// enter 사용하고 싶어서 ln으로 수정
		}
	}
}
