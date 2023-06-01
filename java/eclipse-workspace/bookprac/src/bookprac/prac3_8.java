package bookprac;

import java.util.Scanner;

public class prac3_8 {

	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("정수 몇개?");
        int n = in.nextInt();
        for(int i = 1; i <= n; i++){
            int x = (int)(Math.random()*100);
            System.out.print(x + " ");
            if(i % 10 == 0){
                System.out.println();

            }
        }
	}
}