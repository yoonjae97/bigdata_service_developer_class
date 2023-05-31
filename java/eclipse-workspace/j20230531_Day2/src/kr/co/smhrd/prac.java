package kr.co.smhrd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class prac {

	public static void main(String[] args) {
		int c;
		try {
			BufferedReader one = new BufferedReader( new InputStreamReader(new FileInputStream("c:\\\\Temp\\\\서울시 서초구 모범음식점 신청 현황.csv"),"ms949"));
			BufferedReader two = new BufferedReader( new InputStreamReader(new FileInputStream("c:\\\\Temp\\\\서울시 서초구 모범음식점 지정 현황.csv"),"ms949"));
			BufferedReader thr = new BufferedReader( new InputStreamReader(new FileInputStream("c:\\\\Temp\\\\서울시 서초구 모범음식점 취소 현황.csv"),"ms949"));
			BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("c:\\\\Temp\\\\서초구 모범음식점 전체 현황.csv"), "ms949"));
			while ((c = one.read()) != -1) {
				fw.write((char) c);
			}
			one.close();
			
			while ((c = two.read()) != -1) {
				fw.write((char) c);
			}
			two.close();
			
			while ((c = thr.read()) != -1) {
				fw.write((char) c);
			}
			thr.close();
			fw.close();
		} catch (IOException e) {
			System.out.println("에러"+ e);
		}
		

	}

}
