package j20230601_Day3;

public class LottoGenerator {

	public static int generateNumber() {
		return (int)(Math.random() * 45) + 1;
	}
	
	public static void main(String[] args) {
		int [] lottoNumber = new int [6];
		
		for (int i=0;i<lottoNumber.length;i++) {
			lottoNumber[i] = generateNumber();
			System.out.print(lottoNumber[i]+" ");
	}
//		System.out.println();
//		for (int n : lottoNumber)
//			System.out.printf("%d ", n);
		
}}
