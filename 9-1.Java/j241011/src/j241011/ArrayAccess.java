package j241011;
//import java.util.Scanner;
import java.util.Arrays;

public class ArrayAccess {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int intArray[] = new int[5];
		int myArray[] = intArray;
		intArray[1] = 1;
//		System.out.println(myArray); // 배열의 위치가 출력됨
		System.out.println(Arrays.toString(myArray)); // 배열의 요소를 출력
	}
}