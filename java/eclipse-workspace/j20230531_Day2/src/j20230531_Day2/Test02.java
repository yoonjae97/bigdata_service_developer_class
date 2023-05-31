package j20230531_Day2;

import java.util.Scanner;

public class Test02 {

	public static void main(String[] args) {
	      Scanner scanner = new Scanner(System.in);
	      
	      System.out.print("나이를 입력하시오:");
	      int age=scanner.nextInt();
	      
	      if(age<=39) { 
	         if(age>=30) 
	            System.out.print("30대입니다. ");
	         else if(age>=20)
	            System.out.println("20대 입니다.");
	         else if(age>=10)
	            System.out.println("10대 입니다.");
	         else
	            System.out.println("유년기 입니다.");
	      }
	      else {
	         if(age<=49)
	         System.out.println("40대 입니다.");
	         else if(age<=59)
	            System.out.println("50대 입니다.");   
	         else if(age<=69)
	            System.out.println("60대 입니다.");   
	         else 
	            System.out.println("실버세대 입니다.");   
	      }
	      scanner.close();

	}

}
