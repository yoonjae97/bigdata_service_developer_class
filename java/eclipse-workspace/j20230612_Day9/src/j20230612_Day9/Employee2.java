package j20230612_Day9;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Employee2 {
	String name;
	int[] hours;
	
	Employee2(String name, int[] hours) {
		this.name = name;
		this.hours = hours;
	}
	
	int totalHours(){
		int sum = 0;
		for (int i : hours) {
			sum += i;
		}
		return sum;
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int empSize = sc.nextInt(); // empSize를 통해 사용자 요구에 따른 배열 크기 설정
		Employee2 [] emp = new Employee2[empSize];
		
		int [] temp = new int[7]; // 일주일 근무시간 저장할 배열 생성
		String name;
		
		ArrayList<Object> [] map = new ArrayList[empSize];
		
		for(int i=0; i<empSize;i++) {
			map[i] = new ArrayList<Object>();
		}
		
		for (int k=0; k<empSize;k++) {
			name = sc.next();
			
			for (int i = 0; i < 7; i++) {
				temp[i] = sc.nextInt();
			}
			
			emp[k] = new Employee2(name, temp);
			map[0].addAll(name, emp[k].totalHours());
			map.put(name, emp[k].totalHours());
		}
			
		
		// 생성한 해시맵에서 value를 기준으로 정렬		
		List<Map.Entry<String, Integer>> entryList = new LinkedList<>(map.entrySet());
		entryList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
		
		for(Map.Entry<String, Integer> entry: entryList) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}

}
