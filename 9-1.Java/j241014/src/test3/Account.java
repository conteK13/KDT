package test3;

public class Account {
	private int id;
	private String password;
	private String ownerName;
	private int balance;
	
	public Account(int id, String password, String ownerName) {
		this.id = id;
		this.password = password;
		this.ownerName = ownerName;
		this.balance = 0;		//초기값은 0
	}
	
	private boolean isCorrectPassword(String password) {
		return this.password.equals(password);
	}
	
	public void deposit(int money) {
		if (money > 0)			// 잔고를 0미만으로 만들지 않기 위하여
			this.balance += money;
	}
	
	public int withdraw(int money, String password) {
		if (isCorrectPassword(password)) {
			if (this.balance >= money && money>=0) {
				this.balance -= money;
				}
			else {
				System.out.println("잔액부족");
			}
			return this.balance;
		}
		System.out.println("비밀번호 오류");
		return -1;
	}
	
	public int checkBalance(String password) {
		if (isCorrectPassword(password)) {
			return this.balance;
		}
		System.out.println("비밀번호 오류");
		return -1;
	}
}
