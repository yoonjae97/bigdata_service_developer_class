package j20230602_Day4;

public class LottoGenerator {
		
		public static int generateNumber(){
			return (int)(Math.random()*45)+1 ; 
		}
		
		public static void lottoPrint () {
			System.out.println("이번주 로또 행운의 숫자");
			
			int [] lottoNumber = new int[6];
			
			try {
				for (int i = 0; i < lottoNumber.length; i++) {
					lottoNumber[i] = generateNumber();
				
					//중복 숫자 체크 
					for(int j = 0; j < i; j++) {
						if(lottoNumber[i] == lottoNumber[j]) {
								i--;
								break;
								}
						}
				}
		      
				for(int n : lottoNumber) 
					System.out.printf("%d ", n);
				} 
		      
			catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("오류코드 ==> " + e);  
			}
	         
	    	finally {
	    		System.out.println();
	    		System.out.println("이번주에 이 숫자가 행운을 가져다 드릴 겁니다 🤑 로또 당첨은 행복 ❤️");
	    		}
		}
		
		public static void main(String[] args) {
			
			lottoPrint();
			}		

		}

