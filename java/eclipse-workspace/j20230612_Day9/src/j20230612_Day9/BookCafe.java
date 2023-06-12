package j20230612_Day9;

public class BookCafe {

	// 필드

	String name;

	String address;

	Book[] books;

	Drink[] drinks;

	int booksCnt;

	int drinksCnt;

	static int MAX = 10;

	// 생성자

	BookCafe(String cafeName, String cafeAddress) {

	name = cafeName;

	address = cafeAddress;

	books = new Book[MAX];

	booksCnt = 0;

	drinks = new Drink[MAX];

	drinksCnt = 0;

	}

	// 새 책을 등록

	void addBook(Book book) {

	books[booksCnt] = book;

	booksCnt = booksCnt + 1;

	}

	// 기존 책을 삭제

	void removeBook(String title) {

	// 반복문을 통해 책을 찾음!

	for (int i = 0; i < MAX; i++) {

	Book target = books[i];

	
	// 대상 책이 존재하고,

	if (target != null) {

	// 그 책의 이름이 삭제할 이름과 같다면,

	if (target.getTitle() == title) {

	books[i] = null; // 책을 삭제

	}

	}

	}

	}

	// 새 음료를 추가

	void addDrink(Drink drink) {

	drinks[drinksCnt] = drink;
	drinksCnt +=1;

	}

	// 기존 음료 삭제

	void removeDrink(String name) {

		for (int i = 0; i<MAX;i++) {
			Drink target = drinks[i];
			
			if (target != null) {
				if (target.getDrink() == name) {
					drinks[i] = null;
				}
			}
		}
	}
		// 카페 정보를 출력

	void showCafeInfo() {

	System.out.println("이름: " + name);

	System.out.println("주소: " + address);

	System.out.println("보유 서적:");

	for (int i = 0; i < MAX; i++) {

		Book target = books[i];

	// 대상이 되는 책이 있다면,

		if (target != null) {

			System.out.println("\t" + target.getTitle());
	
			}

	}

	System.out.println("판매 음료:");

	for (int j = 0; j < MAX; j++) {
		Drink target = drinks[j];
		
		if (target != null) {
			System.out.println("\t" + target.getDrink() + "-" + target.getPrice() + "원");
		}
	}

	}


	public static void main(String[] args) {
	// 북카페 생성!

	BookCafe itCafe = new BookCafe("IT 카페", "신림역 3번출구");

	// 신규 책 등록

	Book java = new Book("예제로 배우는 Java");

	itCafe.addBook(java);

	Book html = new Book("HTML 웹페이지 만들기");

	
	itCafe.addBook(html);

	Book slamDunk = new Book("슬램덩크");

	itCafe.addBook(slamDunk);

	Book dataStructure = new Book("자료구조");

	itCafe.addBook(dataStructure);

	// 신규 음료 등록

	Drink americano = new Drink("아메리카노", 2000);

	itCafe.addDrink(americano);

	Drink latte = new Drink("라떼", 2500);

	itCafe.addDrink(latte);

	Drink lemonAde = new Drink("레몬에이드", 3000);

	itCafe.addDrink(lemonAde);

	Drink beer = new Drink("캔맥주", 3000);

	itCafe.addDrink(beer);
	// 카페 정보 출력

	System.out.println("== 카페 정보 ==");

	itCafe.showCafeInfo();

	// 책 삭제

	System.out.println("\n== 책 삭제 ==");

	itCafe.removeBook("슬램덩크");

	// 카페 정보 출력

	System.out.println("\n== 카페 정보 ==");

	itCafe.showCafeInfo();


	// 캔맥주 삭제

	System.out.println("\n== 캔맥주 삭제 ==");

	itCafe.removeDrink("캔맥주");

	// 카페 정보 출력

	System.out.println("\n== 카페 정보 ==");

	itCafe.showCafeInfo();

	}
}