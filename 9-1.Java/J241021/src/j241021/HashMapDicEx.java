package j241021;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class HashMapDicEx {
	public static void main(String[] args) {
		HashMap<String, String> dic = new HashMap<String, String>();
		dic.put("baby", "아기");
		dic.put("love", "사랑");
		dic.put("apple", "사과");

		Set<String> keys = dic.keySet(); // 모든 키를 Set 컬렉션에 받아옴
		Iterator<String> it = keys.iterator(); // Set에 접근하는 Iterator 리턴
		while (it.hasNext()) {
			String key = it.next(); // 키
			String value = dic.get(key); // 값
			System.out.print("(" + key + "," + value + ")");
		}
		System.out.println();

		// 영어 단어를 입력받고 한글 단어 검색
		Scanner scanner = new Scanner(System.in);
		for (int i = 0; i < 3; i++) {
			System.out.print("찾고 싶은 단어는?");
			String eng = scanner.next();
			// 해시맵에서 '키' eng의 '값' kor 검색
			String kor = dic.get(eng);
			if (kor == null)
				System.out.println(eng + "는 없는 단어 입니다.");
			else
				System.out.println(kor);
		}
		System.out.println("종료되었습니다.");
	}
	

}
