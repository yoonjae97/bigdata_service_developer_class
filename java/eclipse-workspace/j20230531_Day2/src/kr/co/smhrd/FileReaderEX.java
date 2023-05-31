package kr.co.smhrd;

import java.io.FileReader;
import java.io.IOException;

public class FileReaderEX {

	public static void main(String[] args) {
		FileReader in = null;
		
		try {
			in = new FileReader(".\\src\\kr\\co\\smhrd\\traffic.csv");
			System.out.println(in.getEncoding());
			int c;
			while ((c = in.read()) != -1) {
				System.out.print((char) c);
			}
			in.close();
		}
		catch (IOException e) {
			System.out.println("입출력 오류");
		}
	}
}
