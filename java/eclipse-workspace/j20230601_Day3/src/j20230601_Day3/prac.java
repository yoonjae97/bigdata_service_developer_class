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
		
		int cnt = 0;
		while(cnt<6) {
			c = generateNumber();
			
			if (lottoNumber.contains(c)) {
				System.out.println("중복 발생");
				continue;
				
			}
			else {
				lottoNumber.add(c);
				cnt += 1;
			}			
		}
		for (int i:lottoNumber) {
			System.out.print(i + " ");
		}
		
	}
}
