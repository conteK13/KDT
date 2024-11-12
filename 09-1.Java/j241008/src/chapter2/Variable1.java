package chapter2;

public class Variable1 {
	public static void main(String[] args) {
		int level;              // 정수형 변수 level을 선언
		level = 10;             // level 변수에 10을 대입
		System.out.println("게임 레벨 : " + level);
		
		int age = 19;			// 4byte 크기
		System.out.println("나이 : " + age);
		
		short sVal = 10;		// 2byte
		byte  bVal = 20;		// 1byte
		System.out.println( sVal + bVal );
		//더 큰 타입에 맞춰짐
		
		int iVal_1 = 10;
		int iVal_2 = 20;
		
		int result = iVal_1 + iVal_2;
		System.out.println( result );
		
		result = iVal_1 - iVal_2;
		System.out.println( result );
		
		result = iVal_1 * iVal_2;
		System.out.println( result );
		
		result = iVal_1 / iVal_2;
		System.out.println( result );
		
		result = iVal_1 % iVal_2;
		System.out.println( result );
		
		
		
		int iVal = 3;
		result = iVal_1 / iVal;
		System.out.println( result );
		
		
		double dVal = 3.0;		// 8 byte
		double dresult;
		// double은 소수점 아래 16자리까지 설정
		// 결과를 저장할 공간을 double로 설정(int로 설정하면 type error 발생)
		dresult = iVal_1 / dVal;
		System.out.println( dresult );
		
		// ival1 / ival2 -> int/int = int
		
		float fVal = 3.0f;		// 4 byte
		float fresult;
		// double은 소수점 아래 6자리까지 설정(나오는 건 7번째까지 나오긴함)
		fresult = iVal_1 / fVal;
		System.out.println( fresult );
		
		//int num1 = 12345678900;
		long num2 = 12345678900L;
		long num3 = 1000;
				
		System.out.println(num2 + num3);
		char ch1 = '한';
		char ch2 = '\uD55C';
		
		System.out.println(ch1);
		System.out.println(ch2);
		
		int a = 65;
		int b = -66;
		
		char a2 = 65;	// char a2 = 'A';
		// char b2 = -66; ¿¡·¯ ³²  
		
		System.out.println((char)a);
		System.out.println((char)b);
		System.out.println((char)a2);
		
		byte bs1 = -128;
	  	byte bs2 = 127;
		
		System.out.println(bs1);
		System.out.println(bs2);
		
		short s = 32767;
		System.out.println(s);
		
		int num = 10;
		int bNum = 0B1010;
		int oNum = 012;
		int hNum = 0XA;
		
		System.out.println(num);
		System.out.println(bNum);
		System.out.println(oNum);
		System.out.println(hNum);
		
		
		double dNum1 = 1.2;
		float fNum2 = 0.9F;

		int iNum3 = (int)dNum1 + (int)fNum2; //1+0
		int iNum4 = (int)(dNum1 + fNum2);    //1.2+0.9 = 2.1 -> 2
		System.out.println(iNum3);
		System.out.println(iNum4);
		
		int i = 10;
		var j = 10.0;
		var str = "hello";
		
		System.out.println(i);
		System.out.println(j);
		System.out.println(str);
		
		str = "test";
	}
}
