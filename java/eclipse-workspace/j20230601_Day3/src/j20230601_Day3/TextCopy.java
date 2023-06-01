package j20230601_Day3;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TextCopy {  // 파일 복사 완료 후 C:\\Temp 폴더 리스트 출력
						// 파일/폴더명, 크기, 마지막수정일자 표시
	public static void listDirectory(File dir) {
		System.out.println("-----" + dir.getPath() + "의 서브 리스트 입니다.-----");
		File[] subfiles = dir.listFiles();
		
		for (int i=0; i<subfiles.length; i++) {
			System.out.print("파일/폴더명 : " + subfiles[i].getName() + " 크기 : " + subfiles[i].length() + " ");
			long t = subfiles[i].lastModified();
			System.out.printf("마지막 수정일자 : %tb %td %ta %tT\n", t, t, t, t);

		}
	}
	public static void main(String[] args) {
		File src = new File("c:\\windows\\system.ini");
		File dest = new File("c:\\Temp\\system.txt");
		int c;
		try {
			FileReader fr = new FileReader(src);
			FileWriter fw = new FileWriter(dest);
			while ((c = fr.read()) != -1) {
				fw.write((char)c);
			}
			
			fr.close();
			fw.close();
			System.out.println(src.getPath() + "를 " + dest.getPath() + "로 복사하였습니다.");

			listDirectory(new File(dest.getParent()));
//			File subfilest[] = new File(dest.getParent()).listFiles();

		} catch (IOException e) {
			System.out.println("파일 복사 오류");
		}

	}

}
