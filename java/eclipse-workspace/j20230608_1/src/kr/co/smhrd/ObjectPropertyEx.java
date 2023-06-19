package kr.co.smhrd;

class Point {
	private int x, y;
	
	public Point(int x, int y) {
		this.x = x; this.y = y;
	}
//	public String toString() {
//		return "Point(" + x + "," + y + ")";
//		}
	public boolean equals(Object obj) {
		Point p = (Point)obj; // obj�� Point Ÿ������ �ٿ� ĳ����
		if(x == p.x && y == p.y) return true;
		else return false;
		}

}

public class ObjectPropertyEx {
	public static void main(String[] args) {
		Point p = new Point(2,3);
		System.out.println(p.getClass().getName()); // Ŭ���� �̸�
		System.out.println(p.hashCode()); // �ؽ� �ڵ� ��
		System.out.println(p.toString()); // ��ü�� ���ڿ�
		System.out.println(p);
		
		Point q = new Point(2,3);
		System.out.println(q.getClass().getName()); // Ŭ���� �̸�
		System.out.println(q.hashCode()); // �ؽ� �ڵ� ��
		System.out.println(q.toString()); // ��ü�� ���ڿ�
		
		Point a = new Point(2,3);
		Point b = new Point(2,3);
		Point c = new Point(3,4);
		
		if(a == b) 
			System.out.println("a==b");
		if(a.equals(b)) 
			System.out.println("a is equal to b");
		if(a.equals(c)) 
			System.out.println("a is equal to c");
	}

}






