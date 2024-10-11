package myclass;

import java.util.Scanner;

public class BabyGin {
	static int[] babbyInput() {
		int card = 0;
		int n = 0;
		int[] c = new int[12];
		Scanner scanner = new Scanner(System.in); // ctrl + shift +o
		while (true) {
			System.out.println("0~9 사이의 숫자 카드 6개를 연속해서 적어주세요!\n 예)1 3 5 4 6 2 X 135462 O\n");
			card = scanner.nextInt();
			if (card > 99999 && card < 1000000) {
				break;
			}
		}
		scanner.close();
		System.out.println(card);
		
		for(int i =0; i<6; i++) {
			n = card % 10;
			c[n] += 1;
			card = card / 10;
		}
		
		for (int x : c) {
			System.out.print(x + " ");
		}
		System.out.println();
		return c;

	}

	static void babyG() {
		// 파이썬 : c =[0]*12

		int tri = 0;
		int run = 0;

		// 파이썬 : card = int(input('0~9 사이의 숫자 카드 6개를 연속해서 적어주세요!\n 예)1 3 5 4 6 2 X
		// 135462 O\n'))

		int[] c = babbyInput();

		for(int i =0; i<10; i++) {
			if (c[i] >= 3) {
				c[i] -= 3;
				tri++;
				i--;
				continue;
			}
			if (c[i] >= 1 && c[i + 1] >= 1 && c[i + 2] >= 1) {
				c[i] -= 1;
				c[i + 1] -= 1;
				c[i + 2] -= 1;
				run += 1;
				i--;
				continue;
			}
		}
		
//		while (i < 10) {
//			if (c[i] >= 3) {
//				c[i] -= 3;
//				tri++;
//				continue;
//			}
//			if (c[i] >= 1 && c[i + 1] >= 1 && c[i + 2] >= 1) {
//				c[i] -= 1;
//				c[i + 1] -= 1;
//				c[i + 2] -= 1;
//				run += 1;
//				continue;
//			}
//			i++;
//		}
		if (run + tri == 2) {
			System.out.println("Baby Gin");
		} else {
			System.out.println("Lose");
		}
	}

	public static void main(String[] args) {
		babyG();

	}
}
