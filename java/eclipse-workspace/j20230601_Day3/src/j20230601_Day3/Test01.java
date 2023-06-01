package j20230601_Day3;

import java.io.File;

public class Test01 {

	public static void main(String[] args) {
//		File f = new File("c:\\Temp");
		File f = new File("c:\\Program Files\\Java\\jdk-20\\bin");
		File[] subfiles = f.listFiles();
		
		for (int i=0;i<subfiles.length;i++) {
			System.out.println(subfiles[i].getName());
			System.out.println("\t파일 크기: " + subfiles[i].length()  + "\t 변경일: " + subfiles[i].lastModified() );
		}

	}

}
