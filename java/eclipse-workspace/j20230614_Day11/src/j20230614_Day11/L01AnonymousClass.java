package j20230614_Day11;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

// class : 객체의 타입 (객체를 추상화 한것)
// abstract class : class가 되다 만 것 (class를 추상화 한 것)
// interface : 만들 class의 실행의 구조(추상함수)만 정의한 것
// 추상메서드가 있으면 덜 구현되어 있어 객체가 될 수 없다.

class BtnApp {
	JLabel label;
	class btnHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			label.setText("안녕");
		}
	}
	// 함수를 매개변수(콜백함수)로 사용하기 위해 타입을 정의하는 것은 시간과 돈이 낭비된다(생산성 저하) 
	// 자바 컴파일러가 타입정의하고 구현하는 것 대신해줄게 -> 익명 클래스 (내부클래스 + 이름이 숫자)
	public void run(String[] args) {
		JFrame f = new JFrame("버튼 이벤트 리스너 테스트");
		f.setBounds(0, 0, 300, 300);
		
		JButton btn = new JButton("버튼");
		f.add(btn, BorderLayout.NORTH);
		label = new JLabel("라벨");
		f.add(label);
		
		// 자바는 함수를 매개변수로 사용할 수 없다. (기본형 or 자료형)
		// 자바에서 함수는 객체일 수 없다. (함수형 언어는 함수를 객체로 만들 수 있다)
		//L01AnonymousClass anonymousClass = new L01AnonymousClass();
		
	 
		btn.addActionListener(new btnHandler() );
		
		
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setVisible(true);
		
	}

}

public class L01AnonymousClass  {
	public static void main(String[] args) {
		BtnApp btnApp = new BtnApp();
		btnApp.run(args);
	}
}
