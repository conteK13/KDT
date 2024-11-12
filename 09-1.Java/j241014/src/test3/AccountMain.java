package test3;

public class AccountMain {

	public static void main(String[] args) {
		Account account = new Account(1, "password!@#", "Park");
		
		account.deposit(100000);
		System.out.println(account.checkBalance("password!@#"));
		account.withdraw(20000, "password!@#");
		System.out.println(account.checkBalance("password!@#"));
		
		System.out.println("===================");
		Account account2 = new Account(2, "qwerty!@#", "Kang");
		
		account2.deposit(5000);
		System.out.println(account2.checkBalance("qwerty!@#"));		// 5000
		account2.withdraw(20000, "qwerty!@#");						// 잔액부족
		account2.withdraw(1000, "qwerty!@#");
		System.out.println(account2.checkBalance("qwerty!@#"));		// 4000
		System.out.println(account2.checkBalance("password!@#"));	// 비밀번호 오류\n-1
	}
}
