package bookprac;

public class prac3_7 {

	public static void main(String[] args) {
		
		double sum = 0;
		int lst[] = new int[10];
		System.out.print("랜덤한 정수들 :");
		for (int i=0;i<lst.length;i++) {
			int j = (int)(Math.random()*10 + 1);
			System.out.print(j + " ");
			sum += j;
		}
		System.out.println("");
		System.out.print("평균은 " +(sum/lst.length));

	}

}
