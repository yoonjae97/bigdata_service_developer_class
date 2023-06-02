package kr.co.smhrd;

public class Circle {
	
	Circle () {} // 생성자 함수 1
	Circle(int radius, String name) { // 생성자 함수 2
		this.radius = radius;
		this.name = name;
	}
	
	Circle(String n, int r) { // 생성자 함수 3
		radius = r;
		name = n;
	}

// 자바 컴파일러는 위와 같은 객체로 인식해서 사용 불가
//	Circle(String m, int s) { // 생성자 함수 2
//		radius = s;
//		name = m;
//	}
	
	int radius;
	String name;
	
	public double getArea() {
		return 3.14*radius*radius;
	}
	
	public static void main(String[] args) {
		Circle pizza = new Circle(); // Circle 객체 생성, 생성자 함수 1 사용
		pizza.radius = 10;
		pizza.name = "자바피자";
		
		double area = pizza.getArea();
		
		System.out.println(pizza.name + "의 면적은 " + area);
		
		Circle donut = new Circle(2, "자바도넛"); // Circle 객체 생성, 생성자 함수 2 사용
//		donut.radius = 2;
//		donut.name = "자바도넛";
		area = donut.getArea();
		System.out.println(donut.name + "의 면적은 " + area);
		
		Circle bdd = new Circle(12, "빈대떡");
		area = bdd.getArea();
		System.out.println(bdd.name + "의 면적은 " + area);
		 
		Circle pj = new Circle("파전", 8);
		area = pj.getArea();
		System.out.println(pj.name + "의 면적은 " + area);
		
		CircleIn ci = new CircleIn(10);
		double result = ci.getArea();
		System.out.println(result);
	}

}
