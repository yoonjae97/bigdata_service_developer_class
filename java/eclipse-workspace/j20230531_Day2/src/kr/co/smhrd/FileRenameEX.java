package kr.co.smhrd;

import java.io.File;

public class FileRenameEX {

	public static void main(String[] args) {
		File file = new File("c:\\Temp\\test.txt");
		
		File newFile = new File("c:\\Temp\\test_2.txt");
		
		boolean result = file.renameTo(newFile);
		
		System.out.println(result);

	}

}



//public class FileToFile {
//
//	   public static void main(String[] args) {
//	      // TODO Auto-generated method stub
//	      FileReader in = null;
//	      FileWriter fout = null;
//	      int c;
//	      try {
//
//	         in = new FileReader(".\\src\\kr\\co\\smhrd\\traffic.csv");
//	         fout = new FileWriter("c:\\Temp\\test.txt", true);
//	         while ((c = in.read()) != -1) {
//	            fout.write(c);
//	         }
//	         System.out.println("파일 입력이 완료 되었습니다.");
//	         in.close();
//	         fout.close();
//	      }
//	      catch(IOException e){
//	         System.out.println("입출력 오류");
//	      }
//	      
//	   }
//
//	}