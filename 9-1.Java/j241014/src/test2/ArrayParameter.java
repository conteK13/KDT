package test2;

public class ArrayParameter {

	static void replaceSpace(char a[]) {
		for (int i = 0; i < a.length; i++)
			if (a[i] == ' ')
				a[i] = ',';
	}

	static void printCharArray(char a[]) {
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i]);
		System.out.println();
	}

	static void convertAlpha(char a[]) {
		//알파벳 영문자 대소문자 변환(대문자->소문자/ 소문자-> 대문자)
		//대문자인 경우
		for (int i = 0; i<a.length; i++) {
			int temp = (int) a[i];
			
			if((temp >= 65) && (temp<= 90)) {		//대문자인지 확인
				temp += 32;
			}else if((temp >=97)&&(temp<=122)) {	//소문자인지 확인
				temp -= 32;
			}
			a[i] = (char) temp;		// int를 char형태로 다시 변환
 	}
		
	}
	public static void main(String args[]) {
		char c[] = { 'T', 'h', 'i', 's', ' ', 'i', 's', ' ', 'a', ' ', 'p', 'e', 'n', 'c', 'i', 'l', '.' };
		printCharArray(c);
		
		convertAlpha(c);
		printCharArray(c);
		
		replaceSpace(c);
		printCharArray(c);
		
		convertAlpha(c);
		printCharArray(c);
	}

}
