package kr.co.smhrd;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ContentPaneEx extends JFrame {

	public ContentPaneEx() {
		setTitle("ContentPane과 JFrame 예제"); // 프레임의 타이틀 달기
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		Container contentPane = getContentPane(); // 컨텐트팬 알아내기
		contentPane.setBackground(Color.ORANGE); // 오렌지색 배경 설정
		contentPane.setLayout(new FlowLayout()); // 컨텐트팬에 FlowLayout
		// 배치관리자 달기
		JButton okbt = new JButton("OK");
		okbt.setSize(100, 150); // 버튼 크기
		contentPane.add(okbt); // OK 버튼 달기
		
		contentPane.add(new JButton("Cancel")); // Cancel 버튼 달기
		contentPane.add(new JButton("Ignore")); // Ignore 버튼 달기
		setSize(500, 250); // 프레임 크기 300x150 설정
		setVisible(true); // 화면에 프레임 출력
		}
	
	public static void main(String[] args) {
		new ContentPaneEx();
	} 
}

