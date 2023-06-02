package kr.co.smhrd;

public class Account {
	private int id;
	private String password;
	private String ownerName;
	private int balance;
	
	public void deposit(int balance) {
		this.balance += 100000;
//		System.out.println("잔액 : "+ this.balance);
	}
	
	public int withdraw(int val, String password) {
		if (isCorrectPassword(password)==true) {
			this.balance -= val;
//			System.out.println(this.balance + "원에서 " + val+ "원이 출금되었습니다.");	
		}
		return 1;
	}
	
	public int checkBalance(String password) {
		if (isCorrectPassword(password)==true) {
			System.out.println("남은 잔액은 " + this.balance + "원 입니다.");
		}
		return 1;
	}
			
	private boolean isCorrectPassword(String password) {
		if (this.password == password) {
			return true;
		}
		else 
			return false;
	}
	
	public Account (int id, String password, String ownerName) {
		this.id = id;
		this.password = password;
		this.ownerName = ownerName;
	}
	

	
	public static void main(String[] args) {
		Account ac = new Account(1, "1234", "dd");
		ac.deposit(100000);
		ac.checkBalance("1234");
		ac.withdraw(20000, "1234");
		ac.checkBalance("1234");

	}

}
