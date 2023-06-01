package bookprac;

import java.util.Scanner;

public class prac3_4 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char num = sc.next().charAt(0);
		
		for (char i=num;i>='a';i--) {
			for (char j='a';j<=i;j++) {
				System.out.print(j);	
			}
			System.out.println();
		}

	}

}
