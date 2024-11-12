package j241011;

import java.util.Scanner;

public class ArrayLength {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("5개의 정수를 입력하세요.");
		
		int intArray[] = new int[5];
		
		double sum = 0.0;
		
		for (int i = 0; i < intArray.length; i++)
			intArray[i] = scanner.nextInt(); // 키보드에서 입력받은 정수 저장
		
		for (int i = 0; i < intArray.length; i++)
			sum += intArray[i]; // 배열에 저장된 정수 값을 더하기
		
		System.out.print("평균은 " + sum / intArray.length);
		scanner.close();
	}

}
