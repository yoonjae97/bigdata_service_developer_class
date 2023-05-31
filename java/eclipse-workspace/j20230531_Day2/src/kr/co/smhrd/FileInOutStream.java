package kr.co.smhrd;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileInOutStream {

   public static void main(String[] args) {
      
      byte b[] = {7,51,3,4,-1,24};
      
      try {
         FileOutputStream fout = new FileOutputStream("c:\\Temp\\test_2.out");
         for(int i=0; i<b.length; i++)
            fout.write(b[i]); // 배열 b의 바이너리를 그대로 기록
         System.out.println("test.out 파일 생성 완료~!!!");
         fout.close();         
       } catch(IOException e) { 
         System.out.println("c:\\Temp\\test.out을 저장시 오류가 발생하였습니다.");
         }
      
      byte result[] = new byte [6]; // 비어 있는 byte 배열
      try {
    	  
         FileInputStream fin = new FileInputStream("c:\\Temp\\test_2.out");
         int n=0, c;
         while((c = fin.read())!= -1) {
            result[n] = (byte)c; // 읽은 바이트를 배열에 저장
            n++;
            }
         System.out.println("c:\\Temp\\test.out에서 읽은 배열을 출력합니다.");
         for(int i=0; i<result.length; i++)
            System.out.print(result[i]+" ");         
         System.out.println();
         fin.close();
         
         } catch(IOException e) { 
            System.out.print("파일 읽기 오류 ==> "+e);
      }
   }
}
