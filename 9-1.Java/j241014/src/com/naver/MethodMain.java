package com.naver;

public class MethodMain {

	public static void main(String[] args) {
		MethodSample func= new MethodSample();
		
		int num1 = func.val1;
		int num2 = func.val2;
		
		int result1 = func.getSum(10, 20);
		int result2 = func.getSum(10, 20, 30);
		double result3 = func.getSum(10.5, 20.2); 
		
		System.out.println("첫번째 getSum의 결과 : "+ result1);
		System.out.println("두번째 getSum의 결과 : "+ result2);
		System.out.println("세번째 getSum의 결과 : "+ result3);

		MethodSample func1= new MethodSample();
		MethodSample newfunc = func1;
		func= func1;
		
		result1 = newfunc.getSum(10, 20);
		
	}

}
