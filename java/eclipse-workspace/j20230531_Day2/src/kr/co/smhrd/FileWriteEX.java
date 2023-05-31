package kr.co.smhrd;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileWriteEX {

	public static void main(String[] args) {
		InputStreamReader in = new InputStreamReader(System.in);
		FileWriter fout = null;
		int c;
		try {
			fout = new FileWriter("c:\\Temp\\test.txt", true);
			System.out.println("파일에 입력할 내용을 적어주세요");
			while ((c = in.read()) != -1) {
			fout.write(c); // 키보드로부터 받은 문자를 파일에 저장
			}
			System.out.println("입력 완료");	
			in.close();
			fout.close();
		} 
		catch (IOException e) {
			System.out.println("입출력 오류");
		}
	}
	
}