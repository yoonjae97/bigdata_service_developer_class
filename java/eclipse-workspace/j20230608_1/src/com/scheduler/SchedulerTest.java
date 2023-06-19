/*
고객센터 콜 배분 프로그램

고객 센터에 전화가 오면 대기열에 저장된다. 
상담원이 지정되기 전까지 대기상태가 된다.

각 전화를 상담원에게 배분하는 정책은 여러 방식으로 구현할 수 있다.
1. 순서대로 배분: 모든 상담원이 동일한 상담 건수를 처리하도록 들어오는 전화 순서대로 배분
2. 짧은 대기열 찾아 배분: 고객 대기 시간을 줄이기 위해 상담을 하지 않는 상담원이나 가장 짧은 대기열을 보유한 상담원에게 배분
3. 우선순위에 따라 배분: 고객 등급에 따라 등급이 높은 고객의 전화를 우선 가져와 업무 능력이 좋은 상담원에게 우선 배분한다. 

인터페이스 : Scheduler 
구현 클래스 : RoundRobin(순서대로),LeastJob(짧은 대기열 먼저),PriorityAllocation(우선순위)
*/


package com.scheduler;
import java.io.IOException;

public class SchedulerTest {

	public static void main(String[] args)  {  
		System.out.println("전화 상담 배분방식을 선택하세요 ");
		System.out.println("R : 한명씩 차례로 할당");
		System.out.println("L : 쉬고 있거나 대기가 가장 적은 상담원에게 할당");
		System.out.println("P : 우선순위가 높은 고객 먼저 할당");
		System.out.print("=====>  ");
		try {	
			int ch = System.in.read();// 할당 방식을 입력받아 ch 변수에 대입
			
			Scheduler scheduler = null;	 // Interface 변수 선언		
			if( ch == 'R' || ch == 'r') { 			// 입력받은 값이 R 또는 r이면 
				scheduler = new RoundRobin(); 		// RoundRobin클래스 생성				
			} else if( ch == 'L' || ch == 'l') { 	// 입력받은 값이 L 또는 l이면
				scheduler = new LeastJob();      	// LeastJob 클래스 생성
			} else if (ch == 'P' || ch == 'p') { 	// 입력받은 값이 P 또는 p이면
				scheduler = new PriorityAllocation(); 	// PriorityAllocation 클래스 생성
			} else {
				System.out.println("지원하지 않는 기능입니다.");
				return;
			}
			scheduler.getNextCall();         // 어떤 정책인가 상관없이 
			scheduler.sendCallToAgent(); // 인터페이스에 선언한 메서드 호출		
		}catch(IOException e) {
			System.out.println("예외발생 => "+ e);
	  }
	}
	
}
