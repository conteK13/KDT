package j241021;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListEx {
	public static void main(String[] args) {
		ArrayList<String> a = new ArrayList<String>();
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < 4; i++) {
			System.out.print("이름을 입력하세요>>");
			String s = sc.next(); // 키보드로부터 이름 입력
			a.add(s); // ArrayList 컬렉션에 삽입
		}
		// ArrayList에 들어 있는 모든 이름 출력
		for (int i = 0; i < a.size(); i++) {
			// ArrayList의 i 번째 문자열 얻어오기
			String name = a.get(i);
			System.out.print(name + " ");
		}
		// 가장 긴 이름 출력
		int longestIndex = 0;
		for (int i = 1; i < a.size(); i++) {
			if (a.get(longestIndex).length() < a.get(i).length())
				longestIndex = i;
		}
		System.out.println("\n가장 긴 이름은 : " + a.get(longestIndex));

	}

}
