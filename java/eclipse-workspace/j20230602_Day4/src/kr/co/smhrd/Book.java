package kr.co.smhrd;

public class Book {
	String title;
	String author;
	
	void show() {
		System.out.println(title + " " + author);
	}
	
	public Book() {
		this("", "");
		System.out.println("생성자 호출됨");
	}
	
	public Book(String t) { // 생성자
		this(t, "작자미상");  // 최종 호출 생성자의 변수 순서대로 입력함
//		title = t;
//		author = "작자미상";
	}
	
//	최종 호출 생성자?
	public Book(String title, String author) { // 생성자
		this.title = title;
		this.author = author;
		}
	
	public static void main(String [] args) {
		Book javaBook = new Book("Java", "황기태"); // 생성자 Book(String t, String a) 호출
		javaBook.show();
		
		Book bible = new Book("Bible"); // 생성자 Book(String t) 호출
		bible.show();
		
		Book emptyBook = new Book();
		emptyBook.show();
	
	}
}
