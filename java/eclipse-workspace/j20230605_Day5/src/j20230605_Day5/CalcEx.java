package j20230605_Day5;

class Calc {
	int n;
	static int m;

//	아직 실행되지 않아 에러 발생 x
	void f1(int x) {
		n = x;
	}
	
	void f2(int x ) {
		m = x;
	}
	
//  클래스 선언시 메서드도 선언되는 형태인데
//	n이 선언되지 않아 에러 발생함
//	static void s1(int x) {
//		n = x;
//	}
//	
//	static void s2(int x) {
//		f1(3);
//	}
	
//	m은 static으로 이미 선언되있어서 가능
	static void s3(int x ) {
		m = x;
	}
	
	static void s4(int x) {
		s3(3);
	}
	
	public static int abs(int a) {return a>0?a:-a;}
	public static int max(int a, int b) {return (a>b)?a:b;}
	public static int min(int a, int b) {return (a>b)?b:a;}
}

public class CalcEx {

	public static void main(String[] args) {
		System.out.println(Calc.abs(-5));
		System.out.println(Calc.max(10, 8));
		System.out.println(Calc.min(-3, -8));
		
//		Calc.n = 10; // 객체 생성 전, 인스턴스 변수 접근 불가
		Calc.m = 20; // static 변수이므로 shared data 공간에 생성됨
		
//		f1(50); // 객체 생성 전, 인스턴스 메소드는 접근 불가.
//		f2(50); // 객체 생성 전, 인스턴스 메소드는 접근 불가.
		
		Calc cal = new Calc();
		cal.n = 10;
		cal.m = 30;
		
		cal.f1(50);
		cal.f2(50);
	}
}
