package j20230613_Day10;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class StudentMember extends JFrame {
	
	public StudentMember () {
		super("학생관리 프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		Container contentPane = getContentPane();
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		JLabel labelNum = new JLabel("번호 :");
		JLabel labelName = new JLabel("이름 :");
		JLabel labelPhone = new JLabel("전화번호 :");
		JLabel labelAddr = new JLabel("주소 :");
		JLabel labelBirth = new JLabel("생년월일 :");
		
		JTextField textNum = new JTextField(10);
		JTextField textName = new JTextField(10);
		JTextField textPhone = new JTextField(15);
		JTextField textAddr = new JTextField(50);
		JTextField textBirth = new JTextField(8);
		
		JButton insertBtn = new JButton ("DB 입력");
		JButton selectBtn = new JButton ("DB 출력");
		JButton resetBtn = new JButton ("출력 삭제");
		
		labelNum.setBounds(50, 20, 100, 20); panel.add(labelNum); 
		textNum.setBounds(150, 20, 100, 20); panel.add(textNum);
		
		labelName.setBounds(50, 50, 100, 20); panel.add(labelName); 
		textName.setBounds(150, 50, 100, 20); panel.add(textName);
		
		labelPhone.setBounds(50, 80, 100, 20); panel.add(labelPhone); 
		textPhone.setBounds(150, 80, 100, 20); panel.add(textPhone);
		
		labelAddr.setBounds(50, 110, 100, 20); panel.add(labelAddr); 
		textAddr.setBounds(150, 110, 300, 20); panel.add(textAddr); 
		
		labelBirth.setBounds(50, 140, 100, 20); panel.add(labelBirth); 
		textBirth.setBounds(150, 140, 100, 20); panel.add(textBirth);
		 
		insertBtn.setBounds(50, 180, 100, 40); panel.add(insertBtn); 
		selectBtn.setBounds(150, 180, 100, 40); panel.add(selectBtn);
		resetBtn.setBounds(300, 180, 100, 40); panel.add(resetBtn);
		
		JTextArea ta = new JTextArea(20, 20);
		JScrollPane sp = new JScrollPane(ta);
		sp.setBounds(50, 230, 420, 300);		
		panel.add(sp);
		
		add(panel);
		setVisible(true);
		setSize(550, 700);
		setLocationRelativeTo(null);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		resetBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ta.setText("");
			}
		});
		
		insertBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int num = Integer.parseInt(textNum.getText());
	              String name = textName.getText();
	              String phone = textPhone.getText();
	              String addr = textAddr.getText();
	              String birth = textBirth.getText();

				Connection conn = null;
				Statement stmt = null;	
				

				try {
				
				conn = ConnectionFactory.getConnection();
				stmt = conn.createStatement();
				String sql = "insert into student values(" + num + ", '" + name + "'"
						 + ", '" + phone + "', '" + addr + "', '" + birth + "')";
				stmt.executeUpdate(sql);
				
				} catch (SQLException se) {
					System.out.println("SQL 오류 = " + se.getMessage());

				} finally {

				ConnectionFactory.close(conn, stmt);

				}
			}
		});
		
		selectBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Connection conn = null;
				Statement stmt = null;	
				ResultSet rs = null;

				try {
					
			         conn = ConnectionFactory.getConnection();
			         stmt = conn.createStatement();
			         String sql = "select * from student order by num"; // default 오름(asc)
			         rs = stmt.executeQuery(sql);
			         System.out.println("학번     이름     전화번호             주소          생년월일 " );
			         ta.append("학번 \t 이름 \t 전화번호 \t 주소 \t 생년월일 " );
			         ta.append("\n");
			         while(rs.next()) {
			            
			            int num = rs.getInt("num");
			            String name = rs.getString("name");
			            String phone = rs.getString("phone");
			            String address = rs.getString("address"); 
			            String birthday = rs.getString("birthday").substring(0, 8);   // 0~7

			            
			            System.out.print(num + "\t");
			            System.out.print(name+ "\t");
			            System.out.print(phone+ "\t");
			            System.out.printf("%10s", address+ "\t");
			            System.out.print(birthday+ "\t");
			            System.out.println();
			            
			            ta.append(num + "\t" + name + "\t" + phone + "\t" + address + "\t" + birthday);
			            ta.append("\n"); 
            
			         }
			      
			      } catch(SQLException se) {
			         System.out.println("SQL 오류 = " + se.getMessage());
			      
			      } finally {
			         ConnectionFactory.close(conn, stmt, rs);
			      }
			}
		});
}
	public static void main(String[] args) {
		new StudentMember();

	}
}
