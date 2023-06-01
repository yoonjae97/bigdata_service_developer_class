package j20230601_Day3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BinaryCopy {

	public static void main(String[] args) {
		File src = new File(".\\resource\\소주연.jpg");
		File dest = new File(".\\resource\\myimg.jpg");
		
		int c;
		try {
			FileInputStream f1 = new FileInputStream(src);
			FileOutputStream fo = new FileOutputStream(dest);
			
			while ((c = f1.read()) != -1) {
				fo.write((byte)c);
			}
			f1.close();
			fo.close();
			System.out.println(src.getPath()+"를 "+dest.getPath()+"로 복사하였습니다.");
			
		} catch (IOException e) {
			System.out.println("파일 복사 오류");
		}
//		고속 복사를 위한 블록 단위 바이너리 파일 복사
//		File src = new File( "c:\\Temp\\img.jpg"); // 원본 파일
//		File dest = new File("c:\\Temp\\back.jpg"); // 복사 파일
//		try {
//		FileInputStream fi = new FileInputStream(src); // 파일 입력 바이트 스트림 생성
//		FileOutputStream fo = new FileOutputStream(dest); // 파일 출력 바이트 스트림 생성
//		byte [] buf = new byte [1024*10]; // 10KB 버퍼, 버퍼크기는 서버 수용능력에 맞게?
//		while(true) {
//		int n = fi.read(buf); // 버퍼 크기만큼 읽기. n은 실제 읽은 바이트
//		fo.write(buf, 0, n); // buf[0]부터 n 바이트 쓰기
//		if(n < buf.length)
//		break; // 버퍼 크기보다 작게 읽었기 때문에 파일 끝에 도달. 복사 종료
//		}
//		fi.close();
//		fo.close();
//		System.out.println(src.getPath() + "를 " + dest.getPath() + "로 복사하였습니다.");
//		} catch (IOException e) { System.out.println("파일 복사 오류"); }
//		}
	}

}
