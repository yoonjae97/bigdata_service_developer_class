package j20230531_Day2;

public class Test01 {

	public static void main(String[] args) {
		int a, b=4;
		a = b++*3;
		System.out.println("a="+a + ", b="+b);
		
		a=0; b=4;
		a=++b*3;
		System.out.println("a="+a + ", b="+b);
				
		a=3; b=5;
		System.out.println("두 수의 차는 "+((a>b)?(a-b):(b-a)));

	}

}
