package j20230608;

public interface PhoneInterface extends FiveG, LTE{
	final int TIMEOUT = 10000;     // 상수 필드 선언
	void sendCall();		// 추상 메소드
	void receiveCall();		// 추상 메소드
	default void printLogo() {   		// default 메소드
		System.out.println("**Phone**");
	// 앞에 public 생략 
	}
}
