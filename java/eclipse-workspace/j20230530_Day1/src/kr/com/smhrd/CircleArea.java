package kr.com.smhrd;

public class CircleArea {

	public static void main(String[] args) {
		final double PI = 3.14;
		double radius = 10.2;
		double circleArea = radius*radius*PI;
		
		System.out.println("반지름" + radius + ",");
		System.out.println("원의 면적 = " + circleArea);
		
		radius = 20.5;
		// PI는 상수이므로 변경 불가
		byte b = 127;
		int i = 100;
		System.out.println(b+i); // b가 int 타입으로 자동 변환
		System.out.println(10/4);
		System.out.println(10.0/4); // 4가 4.0으로 자동 변환
		System.out.println((char)0x12340041);
		System.out.println((byte)(b+i));
		System.out.println((int)2.9 + 1.8);
		System.out.println((int)(2.9 + 1.8));
		System.out.println((int)2.9 + (int)1.8);
		

	}

}
