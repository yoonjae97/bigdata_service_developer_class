package j20230609_Day8;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

public class VectorEx {
	public static void main(String[] args) {
		// 정수 값만 다루는 제네릭 벡터 생성
		Vector<Integer> v = new Vector<Integer>();
		v.add(5); // 5 삽입
		v.add(4); // 4 삽입
		v.add(-1); // -1 삽입
		// 벡터 중간에 삽입하기
		v.add(2, 100); // 4와 -1 사이에 정수 100 삽입
		System.out.println("벡터 내의 요소 객체 수 : " + v.size()); 
		System.out.println("벡터의 현재 용량 : " + v.capacity()); 
		// 모든 요소 정수 출력하기
		for(int i=0; i<v.size(); i++) {
			int n = v.get(i); // 벡터의 i 번째 정수
			System.out.println(n);
		}
		// 벡터 속의 모든 정수 더하기
		int sum = 0;
		for(int i=0; i<v.size(); i++) {
			int n = v.elementAt(i); // 벡터의 i 번째 정수
			sum += n;
		}
		System.out.println("벡터에 있는 정수 합 : " + sum);
		
		Iterator<Integer> itt = v.iterator();
		
		sum = 0;
		while(itt.hasNext()) {
			int n = itt.next();
			System.out.println(n);
			sum += n;
		}
		System.out.println("벡터에 있는 정수 합 : " + sum);
		
		
		HashMap<String, String> dic = new HashMap<String, String>();
		// 3 개의 (key, value) 쌍을 dic에 저장
		dic.put("baby", "아기"); // "baby"는 key, "아기"은 value
		dic.put("love", "사랑");
		dic.put("apple", "사과");
		// dic 해시맵에 들어 있는 모든 (key, value) 쌍 출력
		Set<String> keys = dic.keySet(); // 모든 키를 Set 컬렉션에 받아옴
		Iterator<String> it = keys.iterator(); // Set에 접근하는 Iterator 리턴
		while(it.hasNext()) {
		String key = it.next(); // 키
		String value = dic.get(key); // 값
		System.out.print("(" + key + "," + value + ")");
		}
		System.out.println();
		// 영어 단어를 입력받고 한글 단어 검색
		Scanner scanner = new Scanner(System.in);
		for(int i=0; i<3; i++) {
			System.out.print("찾고 싶은 단어는?");
			String eng = scanner.next();
			// eng 가 0이면 반복문을 끝냄
			if (eng.equals("0")) {
				System.out.println("종료 되었습니다.");
				break;
			}
			// string은 문자열뒤에 보이지 않은 null이 있음
			// 해시맵에서 '키' eng의 '값' kor 검색
			String kor = dic.get(eng);
			if(kor == null) 
			System.out.println(eng + 
			"는 없는 단어 입니다.");
			else 
			System.out.println(kor);
		}
	}
}
