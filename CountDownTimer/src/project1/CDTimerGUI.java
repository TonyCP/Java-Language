package project1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**********************************************************************
 * GUI CLASS
 * Project 1 - Count Down Timer (01/11/2018).
 * 
 * @version 1.0
 * @author TonaeChanelle_
 *********************************************************************/
public class CDTimerGUI extends JPanel implements ActionListener{	

	private static final long serialVersionUID = 1L;

	/** CDTimer class instance **/
	private CountDownTimer t;

	/** Java Timer**/
	private Timer javaTimer;

	/** timer label **/
	private JLabel label;

	/** timer buttons **/
	static JButton[] buttons;

	/** File Menu - items **/
	static JMenuBar menus;
	private JMenu file;
	private JMenuItem exit;

	/**Panel for timer buttons **/
	private JPanel bPanel;

	/**Main constructor **/
	public CDTimerGUI() {

		t = new CountDownTimer(0,0,0);
		javaTimer = new Timer(1000, new ActionListener() {

			public void actionPerformed(ActionEvent a) {
				t.dec();
				label.setText(t.toString());		
			}
		});

		setLayout(new GridBagLayout());
		GridBagConstraints loc = new GridBagConstraints();;

		Font clockFont = new Font("Digital-7" ,Font.BOLD,100);
		label = new JLabel(t.toString());
		label.setBorder(BorderFactory.createEmptyBorder(10, 0, 50, 0));
		label.setFont(clockFont);
		label.setForeground(Color.WHITE);
		add(label);

		//Create the buttons
		bPanel = new JPanel();
		bPanel.setBackground(Color.BLACK);

		buttons = new JButton[10];
		buttons[0] = new JButton("Start");
		buttons[1] = new JButton("Stop");
		buttons[2] = new JButton("Reset");
		buttons[3] = new JButton("Add");
		buttons[4] = new JButton("Sub");
		buttons[5] = new JButton("+");
		buttons[6] = new JButton("-");
		buttons[7] = new JButton("Save");
		buttons[8] = new JButton("Load");
		buttons[9] = new JButton("Suspend");

		for(int i = 0; i < buttons.length; i++) {
			loc.gridx = 0;
			loc.gridy = 10;
			buttons[i].addActionListener(this);
			bPanel.add(buttons[i]);
			//bPanel.remove(buttons[9]);
			add(bPanel,loc);
		}


		//Create file menu
		menus = new JMenuBar();
		file = new JMenu("File");
		exit = new JMenuItem("Exit");

		menus.add(file);
		file.add(exit);
		exit.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) {
		try {
			//START	
			if(e.getSource() == buttons[0]) {
				CountDownTimer.suspend(false);
				javaTimer.stop();
				try{
					String timer = 
							JOptionPane.showInputDialog
							("To start: Enter desired time for Count Down \n" +
									"To continue: Press Ok");
					if(timer.length() == 0) {
						CountDownTimer.suspend(false);
						javaTimer.start();

					}
					CountDownTimer.suspend(false);
					t = new CountDownTimer(timer);
					javaTimer.start();

				}catch(Exception error) {
					JOptionPane.showInputDialog
					("Error: re-enter time in HH:MM:SS format");
				}
			}
			//STOP 
			else if (e.getSource() == buttons[1]) {
				javaTimer.stop();
			}
			//RESET
			else if (e.getSource() == buttons[2]) {
				CountDownTimer.suspend(false);
				t = new CountDownTimer(); 
				//			t.setHours(0);
				//			t.setMinutes(0);
				//			t.setSeconds(0);
			}
			//ADD
			else if (e.getSource() == buttons[3]) {
				javaTimer.stop();
				String add = 
						JOptionPane.showInputDialog
						("To add: enter desired amount of time");
				try {
					//converting int to string
					int getInput= Integer.parseInt(add);
					t.add(getInput);
				}

				catch(IllegalArgumentException ex) {
					JOptionPane.showMessageDialog
					(null, "Input invalid: only # > 0");
				}
				javaTimer.start();
			}
			//SUB
			else if (e.getSource() == buttons[4]) {
				javaTimer.stop();
				String sub =
						JOptionPane.showInputDialog
						("To sub: enter the desired amount of time");
				try {
					//converting int to string
					int getInput= Integer.parseInt(sub);
					t.sub(getInput);
				}

				catch(IllegalArgumentException ex) {
					JOptionPane.showMessageDialog
					(null, "Input invalid: only # > 0");
				}
				javaTimer.stop();
			}
			//INC
			else if (e.getSource() == buttons[5]) {
				CountDownTimer.suspend(false);
				t.inc();

			}
			//DEC
			else if (e.getSource() == buttons[6]) {
				javaTimer.stop();
				t.dec();
				javaTimer.start();

			}
			//SAVE
			else if (e.getSource() == buttons[7]) {
				javaTimer.stop();

				String fileName = 
						JOptionPane.showInputDialog
						("To save: Enter name of this timer file");
				t.save(fileName);
				javaTimer.start();

			}
			//LOAD
			else if (e.getSource() == buttons[8]) {
				javaTimer.stop();
				String fileName = 
						JOptionPane.showInputDialog
						("To load: Enter name of timer file");
				t.load(fileName);
				javaTimer.start();
			}
			//SUSPEND
			else if (e.getSource() == buttons[9]) {
				CountDownTimer.suspend(true);	
			}
			//EXIT
			else if (e.getSource() == exit) {
				System.exit(1);
			}

		}catch(Exception error) {
			System.out.print("Something went wrong, try again");
		}

		// Update text on label no matter what
		label.setText(t.toString());

		//Update
//		repaint();
//		revalidate();
	}

	/** Method for black background **/
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		setBackground(Color.BLACK);
	}

}

