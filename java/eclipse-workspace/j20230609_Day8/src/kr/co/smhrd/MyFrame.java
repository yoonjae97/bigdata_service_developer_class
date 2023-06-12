package kr.co.smhrd;

import javax.swing.JFrame;

public class MyFrame extends JFrame{

	
	public MyFrame() {
		setTitle("500x300 스윙 프레임 만들기");
		setSize(500, 300);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		MyFrame mf = new MyFrame();
	}

}
