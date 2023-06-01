package bookprac;

import java.util.Scanner;

public class prac3_6 {

	public static void main(String[] args) {
		int [] unit = {50000, 10000, 1000, 500, 100, 50, 10, 1};
		Scanner sc = new Scanner(System.in);
		System.out.println("금액을 입력하시오 >>");
		int num = sc.nextInt();
		
		for (int i:unit) {
			System.out.println(i+"원 짜리 : "+(num/i)+"개");
			num %= i;
		}
	}

}
