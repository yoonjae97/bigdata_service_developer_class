package j20230601_Day3;

import java.util.ArrayList;
import java.util.List;

public class prac {
	
	public static int generateNumber() {
		return (int)(Math.random() * 45) + 1;
	}
	
	public static void main(String[] args) {
		int c = 0;
		final List<Integer> lottoNumber = new ArrayList<Integer>();
		
		while(lottoNumber.size()<6) {
			c = generateNumber();
			
			if (lottoNumber.contains(c)) {
				System.out.println(c);
				System.out.println("중복 발생");
				continue;
			}
			else {
				lottoNumber.add(c);
			}			
		}
		for (int i:lottoNumber) {
			System.out.print(i + " ");
		}
	}
}
