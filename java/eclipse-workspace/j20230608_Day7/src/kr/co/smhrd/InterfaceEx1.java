package kr.co.smhrd;

import j20230608.SamsungPhone;

public class InterfaceEx1 {

	public static void main(String[] args) {
		SamsungPhone ph = new SamsungPhone();
		ph.printLogo();
		ph.sendCall();
		ph.receiveCall();
		ph.flash();
		ph.comNet();
		ph.speed();
		
		System.out.println(ph.TIMEOUT);
		
		LgPhone lgph = new LgPhone();
		
		lgph.printLogo();
		lgph.sendCall();
		lgph.receiveCall();
		lgph.ring();
		lgph.comNet();
		lgph.speed();
		
		System.out.println(ph.TIMEOUT);
	}

}
