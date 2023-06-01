package bookprac;

public class prac3_9 {

	public static void main(String[] args) {
		int lst[][] = new int[4][4];
		
		for (int i=0;i<4;i++) {
			for (int j=0;j<4;j++) {
				lst[i][j] = (int)(Math.random()*10);
			}
		}
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                System.out.print(lst[i][j] + " ");
            }
            System.out.println();
        }

	}
}
