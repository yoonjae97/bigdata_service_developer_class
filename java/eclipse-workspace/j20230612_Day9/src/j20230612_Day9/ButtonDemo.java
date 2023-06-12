package j20230612_Day9;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class ButtonDemo implements ActionListener {
	
	int redScoreAmount = 0;
	
	int blueScoreAmount = 0;
	
	JPanel titlePanel, scorePanel, buttonPanel;
	
	JLabel redLabel, blueLabel, redScore, blueScore;
	
	JButton redButton, blueButton, resetButton;
	
	public JPanel createContentPane() {
	
	/* total panel */
	
	JPanel totalGUI = new JPanel();
	
	totalGUI.setLayout(null);
	
	/* title panel */
	
	titlePanel = new JPanel();
	
	titlePanel.setLayout(null);
	
	titlePanel.setLocation(10, 0);
	
	titlePanel.setSize(250, 30);
	
	totalGUI.add(titlePanel);
	
	redLabel = new JLabel("Red Team");
	
	redLabel.setLocation(0, 0);
	
	redLabel.setSize(100, 30);
	
	redLabel.setHorizontalAlignment(0);
	
	redLabel.setForeground(Color.red);
	
	titlePanel.add(redLabel);
	
	blueLabel = new JLabel("Blue Team");
	
	blueLabel.setLocation(120, 0);
	
	blueLabel.setSize(100, 30);
	
	blueLabel.setHorizontalAlignment(0);
	
	blueLabel.setForeground(Color.blue);
	
	titlePanel.add(blueLabel);
	
	/* score panel */
	
	scorePanel = new JPanel();
	
	scorePanel.setLayout(null);
	
	scorePanel.setLocation(10, 40);
	
	scorePanel.setSize(250, 30);
	
	totalGUI.add(scorePanel);
	
	redScore = new JLabel("0");
	
	redScore.setLocation(0, 0);
	
	redScore.setSize(100, 30);
	
	redScore.setHorizontalAlignment(0);
	
	scorePanel.add(redScore);
	
	blueScore = new JLabel("0");
	
	blueScore.setLocation(120, 0);
	
	blueScore.setSize(100, 30);
	
	blueScore.setHorizontalAlignment(0);
	
	scorePanel.add(blueScore);
	
	/* button panel */
	
	buttonPanel = new JPanel();
	
	buttonPanel.setLayout(null);
	
	buttonPanel.setLocation(10, 80);
	
	buttonPanel.setSize(250, 70);
	
	totalGUI.add(buttonPanel);
	
	redButton = new JButton("Red Score!");
	
	redButton.setLocation(0, 0);
	
	redButton.setSize(100, 30);
	
	redButton.addActionListener(this);
	
	buttonPanel.add(redButton);
	
	blueButton = new JButton("Blue Score!");
	
	blueButton.setLocation(120, 0);
	
	blueButton.setSize(100, 30);
	
	blueButton.addActionListener(this);
	
	buttonPanel.add(blueButton);
	
	resetButton = new JButton("Reset Score");
	
	resetButton.setLocation(0, 40);
	
	resetButton.setSize(220, 30);
	
	resetButton.addActionListener(this);
	
	buttonPanel.add(resetButton);
	
	totalGUI.setOpaque(true);
	
	return totalGUI;
	
	}
	
	private static void createAndShowGUI() {
	
	JFrame.setDefaultLookAndFeelDecorated(true);
	
	JFrame frame = new JFrame("[=] JButton Score! [=]");
	
	ButtonDemo demo = new ButtonDemo();
	
	frame.setContentPane(demo.createContentPane());
	
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	frame.setSize(250, 190);
	
	frame.setVisible(true);
	
	}
	public static void main(String[] args) {
	
	SwingUtilities.invokeLater(new Runnable() {
	
	  @Override
	
	   public void run() {
	
	   // TODO Auto-generated method stub
	
	         createAndShowGUI();
	
	    }
	
	  });
	
	}
	
	@Override
	
	public void actionPerformed(ActionEvent e) {
	
	// TODO Auto-generated method stub
	
	Object obj = e.getSource();
	
	if (obj == redButton) {
	
		++redScoreAmount;
		
		redScore.setText("" + redScoreAmount);
		
	} else if (obj == blueButton) {
	
		++blueScoreAmount;
		
		blueScore.setText("" + blueScoreAmount);
	
	} else if (obj == resetButton) {
	
		redScoreAmount = 0;
		
		blueScoreAmount = 0;
		
		redScore.setText("" + redScoreAmount);
		
		blueScore.setText("" + blueScoreAmount);
	
		}
	
	}

}
