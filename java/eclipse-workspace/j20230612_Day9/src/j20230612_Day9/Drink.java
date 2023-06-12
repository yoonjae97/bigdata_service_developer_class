package j20230612_Day9;


public class Drink {

	// 필드
	
	String name;
	
	int price;
	
	// 생성자
	
	public Drink(String name, int price) {
	
		this.name = name;
		this.price = price;
	
	}
	
	String getDrink() {
		return name;
	}
	
	int getPrice() {
		return price;
	}
	

}
