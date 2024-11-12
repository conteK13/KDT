package myclass;

public class BubbleSort {
	// static 설정을 해야 for문 안에서도 돌릴 수 있음.
	// 파이썬 : def BubbleSort(a, n):
	static int[] bubbleS(int a[], int n) {
		int temp;
		// 파이썬 : for i in range(n-1, 0, -1):
		for(int i=n-1; i>0; i--) {
			
			// 파이썬 : for j in range(i):
			for(int j = 0; j<i; j++) {
				
				// 파이썬 : if a[j] > a[j+1]:
				if(a[j]>a[j+1]) {
					temp = a[j];
					a[j] = a[j+1];
					a[j+1] = temp;
				}
			}
		}
		return a;
	}
	
	
	public static void main(String[] args) {
		// 파이썬 : list1 = [55, 7, 78, 12, 42]
		int [] list1 = {55, 7, 78, 12, 42};
		
		// 파이썬 : print(list1)
		for(int x : list1) {
			System.out.print(x + " ");
		}
		System.out.println();
		
		// 파이썬 : n = len(list1)
		int n = list1.length;
		
		// 파이썬 : print(BubbleSort(list1, n))
		for (int x : bubbleS(list1, n)) {
			System.out.print(x + " ");
		}
		
	}
}
