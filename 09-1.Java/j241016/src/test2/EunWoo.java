package test2;

class Parent{
	int func(int n) {
		if(n<=1)
			return n;
//		System.out.println("test");
		return func(n-1)+func(n-2);
	}
}

class Child extends Parent{
	int func(int n) {
		if(n<=1) {
			return n;
		}
//		System.out.println("test2");
		return func(n-1)+func(n-3);
	}
}

public class EunWoo {
	public static void main(String[] args) {
		Parent obj = new Child();
		System.out.println(obj.func(7));
		
		//func(7) = func(6)+func(4) = 1+1 = 2
		//func(6) = func(5)+func(3) = 1+0 = 1
		//func(5) = func(4)+func(2) = 1+0 = 1
		//func(4) = func(3)+func(1) = 0+1 = 1
		//func(3) = func(2)+func(0) = 0+0 = 0
		//func(2) = func(1)-func(-1) = 1+(-1) = 0
		//func(1) = 1
	}

}
