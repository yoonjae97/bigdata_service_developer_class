package j20230608;

public class InterfaceEx {

	public static void main(String[] args) {

		SamsungPhone ph = new SamsungPhone();
		
		ph.printLogo();
		ph.sendCall();
		ph.receiveCall();
		ph.flash();
		
		System.out.println(ph.TIMEOUT);

	}

}
