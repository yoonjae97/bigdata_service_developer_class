package com.scheduler;

import java.io.IOException;

public class SchedulerTest {

	public static void main(String[] args) {
		System.out.println("전화 상담 배분 방식을 선택하세요 ");
		System.out.println("R : 한명씩 차례로 할당");
		System.out.println("L : 쉬고 있거나 대기가 가장 적은 상담원에게 할당");
		System.out.println("P : 우선순위가 높은 고객 먼저 할당");
		System.out.println("=====>   ");
	
	try {
		int ch = System.in.read();    // 할당 방식을 입력 받아 ch 변수에 대입
		
		Scheduler scheduler = null;    //  Interface 변수 선언
		if(ch == 'R' || ch == 'r') {	// 입력받은 값이 R 또는 r이면
			scheduler = new RoundRobin();  // RoundRobin 클래스 생성	
		}else if(ch == 'L' || ch == 'l') {   // 입력받은 값이 L 또는 l이면
			scheduler = new LeastJob();		// LeastJob 클래스 생성
		}else if(ch == 'P' || ch == 'p') {		// 입력받은 값이 P 또는 p이면
			scheduler = new PriorityAllocation();	// PriorityAllocation 클래스 생성
		}else {
			System.out.println("지원하지 않는 기능입니다.");
		return;
		}
		scheduler.getNextCall();
		scheduler.sendCallToAgent();
	}catch(IOException e) {
		System.out.println("예외 발생 => " + e);
	}
	}
}
