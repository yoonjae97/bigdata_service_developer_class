package kr.co.smhrd;
import java.util.Random;

public class smhrdLotto {

	public static void main(String[] args) {
//		System.out.println("오늘 날짜 입력");
//		
//		Scanner sc = new Scanner(System.in);
//		String dateSt = sc.next();
//		
//		LottoGenerator ltNum = new LottoGenerator();
//		ltNum.lottoPrint();
//		int number = ltNum.generateNumber();
//		System.out.println(number);
		
		Random rd = new Random();
	try {
		int [] lottoNumber = new int[6];
		
		for (int i=0;i<6;i++) {
			lottoNumber[i] = (rd.nextInt(45)+1);
			
			for(int j = 0; j < i; j++) {
				if(lottoNumber[i] == lottoNumber[j]) {
						i--;
						break;
						}
	
			}
		}
			for (int k : lottoNumber) 
				System.out.printf("%d ", k);
		
	}
	catch (ArrayIndexOutOfBoundsException e) {
		System.out.println(e+" 오류발생");		
	}
	}
}
