package j20230614_Day11;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class L02AnonymousClass {

	public static void main(String[] args) {
	      JFrame f=new JFrame("버튼 이벤트 리스너 테스트");
	      f.setBounds(0, 0, 300, 300);
	      JButton btn=new JButton("버튼");
	      f.add(btn,BorderLayout.NORTH);
	      JLabel label=new JLabel("라벨");
	      
	   // L2AnonymousClass$1.class : ActionListener를 구현한 익명클래스 생성
//	      btn.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				label.setText("안녕"); // 컴파일러가 지역변수를 전역변수로 변경 취급
//				
//			}
//		});
	      
	      // 함수만 매개변수로 사용하는 함수형 언어에 비해 불편
	      // -> 람다식 (화살표 함수) : 함수가 1개 있는 인터페이스를 마치 함수를 매개변수로 사용하는 것처럼 작성가능
	      // 익명클래스와 아주 유사하게 컴파일러가 구현하지만 클래스를 미리 만들어진 것을 사용하기 때문에 익명클래스가 없다.
	      // 함수형 언어인척 하는 것 -> 문법적 설탕(static sugar)
	      
	      btn.addActionListener((e)->{ // (e)->{} : public void actionPerformed(Action Event e) {}
	    	  label.setText("람다식 안녕");
	      });
	      f.add(label);
	      f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	      f.setVisible(true);

	}

}
