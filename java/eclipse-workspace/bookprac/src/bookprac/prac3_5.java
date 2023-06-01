package bookprac;

import java.util.Scanner;

public class prac3_5 {

	public static void main(String[] args) {
		int lst [] = new int[10];
		Scanner sc = new Scanner(System.in);
		System.out.println("양의 정수 10개를 입력하시오>>");
		for(int i=0; i<10; i++) {
			lst[i] = sc.nextInt();
		}
		
		for(int j:lst) {
			if (j%3==0) {
				System.out.print(j);
			}
		}

	}

}
