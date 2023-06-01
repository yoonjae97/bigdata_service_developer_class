package bookprac;

public class prac3_10 {

	public static void main(String[] args) {
		int lst [][] = new int[4][4];
		int temp [] = new int [10];
		
		for(int i=0;i<10;i++) {
			temp[i] = (int)(Math.random()*10+1);
		}
		
		System.out.println(temp);

	}

}
