package kr.co.smhrd;

abstract class Calculator {
	public abstract int add(int a, int b);
	public abstract int subtract(int a, int b);
	public abstract double average(int[] a);
}

class GoodCalc1 extends Calculator {
	@Override
	public int add(int a, int b) { // 추상 메소드 구현
		return a + b;
	}
	
	@Override
	public int subtract(int a, int b) { // 추상 메소드 구현
		return a - b;
	}
	
	@Override
	public double average(int[] a) { // 추상 메소드 구현
		double sum = 0;
		for (int i = 0; i < a.length; i++)
			sum += a[i];
		return sum/a.length;
	}
}

class GoodCalc2 extends Calculator {
	@Override
	public int add(int a, int b) { // 추상 메소드 구현
		return b + a;
	}
	
	@Override
	public int subtract(int a, int b) { // 추상 메소드 구현
		return b - a;
	}
	
	@Override
	public double average(int[] a) { // 추상 메소드 구현
		double sum = 0;
		for (int i : a)
			sum += i;
		return sum/a.length;
	}
}


public class GoodCalc{
	public static void main(String[] args) {
		GoodCalc1 c = new GoodCalc1();
		System.out.println(c.add(2, 3));
		System.out.println(c.subtract(2, 3));
		System.out.println(c.average(new int [] {10 ,20 ,30 ,40}));
	
		GoodCalc2 d = new GoodCalc2();
		System.out.println(d.add(2, 3));
		System.out.println(d.subtract(2, 3));
		System.out.println(d.average(new int [] {10 ,20 ,30 ,40}));
	
	}
}









