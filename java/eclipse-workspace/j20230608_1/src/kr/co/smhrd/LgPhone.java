package kr.co.smhrd;

import j20230608.PhoneInterface;

public class LgPhone implements PhoneInterface {

	@Override
	public void sendCall() {
		System.out.println("LG 폰이 없어졌어요....");
	}

	@Override
	public void receiveCall() {
		System.out.println("LG 통신사만 있어요....ㅜㅜ");
	}
	
	@Override
	public void comNet() {
		System.out.println("LGT의 5G망을 사용하고 있습니다.");
	}
	
	@Override
	public void speed() {
		System.out.println("LGT Speed는 LTE 속도 입니다. 업그레이드 하세요~");
	}
	
	public void ring() {
		System.out.println("아름다운 음악소리의 전화벨이 울립니다....디리리리리링....");
	}

}
