package project1;

import java.awt.Color;
import javax.swing.JFrame;

/**********************************************************************
 * RUN CLASS Project 1 - Count Down Timer (01/11/2018).
 * 
 * @version 1.0
 * @author TonaeChanelle_
 *********************************************************************/
class RunCDTimerGUI {

	public static void main(String[] args) {
		// RUN PROGRAM **
		JFrame frame = new JFrame();
		MainPanel panel = new MainPanel();

		frame.setTitle("Count Down Timers");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(CDTimerGUI.buttons[9]).setBounds(0, 490, 900, 50);
		frame.getContentPane().add(panel);
		panel.setBackground(Color.BLACK);
		frame.setSize(900, 600);
		frame.setVisible(true);
		frame.setJMenuBar(CDTimerGUI.menus);

	}

}