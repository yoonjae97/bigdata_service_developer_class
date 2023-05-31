package kr.co.smhrd;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class prac {

	public static void main(String[] args) {
		File one = new File("c:\\Temp\\서울시 서초구 모범음식점 신청 현황.csv");
		File two = new File("c:\\Temp\\서울시 서초구 모범음식점 지정 현황.csv");
		File thr = new File("c:\\Temp\\서울시 서초구 모범음식점 취소 현황.csv");
		File total = new File("c:\\Temp\\서초구 모범음식점 전체 현황.csv");
		
		int c;
		try {
			FileReader one_fr = new FileReader(one);
			FileReader two_fr = new FileReader(one);
			FileReader thr_fr = new FileReader(one);
			BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("c:\\\\Temp\\\\서초구 모범음식점 전체 현황.csv"), "cp949"));
			while ((c = one_fr.read()) != -1) {
				fw.write((char) c);
			}
			one_fr.close();
			
			while ((c = two_fr.read()) != -1) {
				fw.write((char) c);
			}
			two_fr.close();
			
			while ((c = thr_fr.read()) != -1) {
				fw.write((char) c);
			}
			thr_fr.close();
			fw.close();
		} catch (IOException e) {
			System.out.println("에러"+ e);
		}
		

	}

}