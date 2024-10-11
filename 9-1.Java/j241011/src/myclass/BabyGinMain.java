package myclass;

import java.util.Scanner;

public class BabyGinMain {

	public static void main(String[] args) {
		int card;
		Scanner scanner = new Scanner(System.in); // ctrl + shift +o
		while (true) {
			// 입력값 안내문
			System.out.println("0~9 사이의 숫자 카드 6개를 연속해서 적어주세요!\n 예)1 3 5 4 6 2 X 135462 O\n");
			// 입력값
			card = scanner.nextInt();
			if (card > 99999 && card < 1000000) {
				scanner.close();
				break;
			}
		}

		// 입력값으로 BabyGin 클래스 인스턴스 생성
		BabyGin2 bg = new BabyGin2(card);
		bg.babyGinGame();
		// 인스턴스 메서드 사용

		// 하나씩 게임을 만들 수 있음. 기능+속성(게임을 하는 사람, 만드는 사람)
	}

}
