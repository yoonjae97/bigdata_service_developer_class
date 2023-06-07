package j20230607_Day6;

class A {
	void f() {
		System.out.println("A의 f () 호출");
	}
}

class B extends A{
	void f() {
		System.out.println("B의 f () 호출");
	}
}

public class OverridingTest {

	public static void main(String[] args) {
		A a = new A();
		a.f();
		
		A a1 = new B(); // 업캐스팅된 객체의 f 메소드가 이미 오버라이딩된 상태?
		a1.f();
		
//		B b = new A(); // 생성자가 아닌 이미 만들어진 a 객체를 할당해서 다운캐스팅 해야함
//		B b = (B)a; // 다운캐스팅
//		b.f();
		
		B b1 = new B();
		b1.f();

	}

}
