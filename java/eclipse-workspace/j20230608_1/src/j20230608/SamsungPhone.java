package j20230608;

public class SamsungPhone implements PhoneInterface {
	@Override
	public void sendCall() {
		System.out.println("�츮������");
	}

	@Override
	public void receiveCall() {
		System.out.println("��ȭ�� �Խ��ϴ�.");
	}
	
	@Override
	public void comNet() {
		System.out.println("5G���� ����ϰ� �ֽ��ϴ�.");
	}
	
	@Override
	public void speed() {
		System.out.println("Speed�� LTE �ӵ� �Դϴ�. ���׷��̵� �ϼ���~");
	}
	
	public void flash() { 
		System.out.println("��ȭ�⿡ ���� �������ϴ�."); 
		}
}





