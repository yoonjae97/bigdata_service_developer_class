package kr.co.smhrd;

class CircleIn {
	int radius;
	public CircleIn(int radius) {
		this.radius = radius;
	}
	public double getArea() {
		return 3.14*radius*radius;
	}
}



public class CircleArray {

	public static void Saprint() {
		System.out.println("이 함수는 Saprint ()이고 CircleArray 클래스에 포함되어 있다.");
	}
	
	public static void main(String[] args) {
		CircleIn [] c = new CircleIn[5];
		
		for(int i=0;i<c.length; i++) {
			c[i] = new CircleIn(i);
			System.out.print((int)(c[i].getArea()) + " ");
		}
	}

}
