package j20230608;

public class SamsungPhone implements PhoneInterface {
	// 인터페이스 - 인터페이스 : extends
	// PhoneInterface의 모든 메소드 구현
	@Override
	public void sendCall() {
		System.out.println("띠리리리링");
	}

	@Override
	public void receiveCall() {
		System.out.println("전화가 왔습니다.");

	}
	
	@Override
	public void comNet() {
		System.out.println("5G망을 사용하고 있습니다.");

	}
	
	@Override
	public void speed() {
		System.out.println("Speed는 LTE 속도 입니다. 업그레이드 하세요-");

	}
	
	public void flash() {
		System.out.println("전화기에 불이 켜졌습니다.");
	}
	
}
