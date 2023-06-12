package j20230609_Day8;

import java.util.StringTokenizer;

public class StringTokenizerEx {

	public static void main(String[] args) {
		String query = "name=kitae&addr=seoul&age=21";
		
		StringTokenizer st = new StringTokenizer(query, "&");
		
		int n = st.countTokens();
		System.out.println("토큰 개수 = " + n);
		
		while(st.hasMoreTokens()) {
			String token = st.nextToken();
			System.out.println(token);
		}
		System.out.println(Math.abs(-3.14)); // 절댓값 구하기
		System.out.println(Math.sqrt(9.0)); // 9의 제곱근 = 3
		System.out.println(Math.exp(2)); // e2
		System.out.println(Math.round(3.54)); // 반올림
		// [1, 45] 사이의 정수형 난수 5개 발생
		System.out.print("이번주 행운의 번호는 ");
		for (int i=0; i<5; i++)
		System.out.print((int)(Math.random()*45 + 1) + " "); // 난수 발생

	}

}
