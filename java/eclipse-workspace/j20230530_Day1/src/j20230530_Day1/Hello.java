package j20230530_Day1;

public class Hello {
	public static int sum(int n, int m) {
		return n + m;
	}
	
	public static void main(String[] args) {
		int i = 20;
		int s;
		char a;
		String st;
		
		s = sum(i, 10);
		// char은 '가능하지만 String은 ' 사용 불가
		st = "스마트인재개발원 hyj";
		a = '?';
		// sysout + Ctrl + Space : print문 자동완성
		System.out.println("결과값 확인하기");
		System.out.print(st);
		System.out.println(a);
		System.out.println("Hello");
		System.out.println(s);
	}

}