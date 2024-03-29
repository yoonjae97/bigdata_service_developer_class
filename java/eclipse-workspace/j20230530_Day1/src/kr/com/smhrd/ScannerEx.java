package kr.com.smhrd;

import java.util.Scanner;

public class ScannerEx {

	public static void main(String[] args) {
		System.out.println("이름, 도시, 나이, 체중, 독신 여부를 빈칸으로 분리하여 입력하세요");
		
		// ctrl + shift + o 자동으로 import
		Scanner sc = new Scanner(System.in);
		String name = sc.next();
		String city = sc.next();
		int age = sc.nextInt();
		double weight = sc.nextDouble();
		boolean single = sc.nextBoolean();
		
		System.out.println("당신의 이름은 " + name + "입니다");
		System.out.println("당신이 사는 도시는 " + city + "입니다");
		System.out.println("당신의 나이은 " + age + "입니다");
		System.out.println("당신의 체중은 " + weight + "kg 입니다");
		System.out.println("당신의 독신 여부는 " + single + "입니다");
		
		sc.close();
	}

}
