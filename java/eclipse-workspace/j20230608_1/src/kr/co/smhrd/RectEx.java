package kr.co.smhrd;

class Rect {
	int width, height;
	public Rect(int width, int height) {
		this.width = width; this.height = height;
	}
	public boolean equals(Object obj) { 
		Rect p = (Rect)obj; // obj�� Point Ÿ������ �ٿ� ĳ����
		if (width*height == p.width*p.height) 
			return true;
		else 
			return false;
	}
}

public class RectEx {

	public static void main(String[] args) {
		Rect a = new Rect(2,3); // ���� 6
		Rect b = new Rect(3,2); // ���� 6
		Rect c = new Rect(3,4); // ���� 12
		if(a.equals(b)) 
			System.out.println("a is equal to b");
		if(a.equals(c)) 
			System.out.println("a is equal to c");
		if(b.equals(c)) 
			System.out.println("b is equal to c");
	}

}
