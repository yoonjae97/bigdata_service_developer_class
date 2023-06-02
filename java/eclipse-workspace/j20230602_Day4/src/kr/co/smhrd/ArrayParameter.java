package kr.co.smhrd;

// 같은 패키지내에 같은 이름의 class 존재하므로 다른이름으로 교체해야함
class CircleP {
	int radius;
	public CircleP(int radius) {
		this.radius = radius;
	}
	public double getArea() {
		return 3.14*radius*radius;
	}
}

public class ArrayParameter {
	static void increase(CircleP m) {
		m.radius++;
	}
	
	static void replaceSpace(char a[]) {
		for (int i=0; i<a.length; i++)
		if (a[i] == ' ')
			a[i] = ',';
	}
	static void printCharArray(char a[]) {
		for (int i=0; i<a.length; i++)
			System.out.print(a[i]);
		System.out.println();
	}
	public static void main (String args[]) {
		char c[] = {'T','h','i','s',' ','i','s',' ','a',' ','p','e','n','c','i','l','.'};
		printCharArray(c);
		
		replaceSpace(c);
		
		printCharArray(c);
		
		CircleP pizza = new CircleP(14);
		double result = pizza.getArea();
		System.out.println(result);
		
		increase(pizza);
		result = pizza.getArea();
		System.out.println(result);
		
		
		
	}
}