package myclass;

import java.util.Scanner;

public class BabyGin2 {
	private int card;
	private int[] c = new int[12];
	private int tri;
	private int run;

	public BabyGin2(int card) {
		this.card = card;
	}

	public boolean babyGinCheck() {
		// 입력받은 card로 babygin인지 판단 여부 코드
		return tri + run == 2;
	}

	void babyGinGame() {
		int n;
		for (int i = 0; i < 6; i++) {
			n = card % 10;
			c[n] += 1;
			card = card / 10;
		}
		
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
		
		if (babyGinCheck()) {
			System.out.println("BabyGin");
		} else {
			System.out.println("Lose");
		}
	}

}
