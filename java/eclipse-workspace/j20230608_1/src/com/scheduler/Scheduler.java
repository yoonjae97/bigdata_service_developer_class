package com.scheduler;

public interface Scheduler {
	void getNextCall();         // 추상메서드 2개 선언
	void sendCallToAgent();
}
