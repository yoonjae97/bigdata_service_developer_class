package kr.co.smhrd;

import j20230608.PhoneInterface;

public class LgPhone implements PhoneInterface {

	@Override
	public void sendCall() {
		System.out.println("LG ���� ���������....");
	}

	@Override
	public void receiveCall() {
		System.out.println("LG ��Ż縸 �־��....�̤�");
	}
	
	@Override
	public void comNet() {
		System.out.println("LGT�� 5G���� ����ϰ� �ֽ��ϴ�.");
	}
	
	@Override
	public void speed() {
		System.out.println("LGT Speed�� LTE �ӵ� �Դϴ�. ���׷��̵� �ϼ���~");
	}
	
	public void ring() {
		System.out.println("�Ƹ��ٿ� ���ǼҸ��� ��ȭ���� �︳�ϴ�....�𸮸�������....");
	}

}
