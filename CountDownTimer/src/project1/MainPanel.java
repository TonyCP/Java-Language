package project1;

import javax.swing.JPanel;
/**********************************************************************
 * GUI PANEL CLASS
 * Project 1 - Count Down Timer (01/11/2018).
 * 
 * @version 1.0
 * @author TonaeChanelle_
 *********************************************************************/
public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/** 3 GUI panels **/
	private CDTimerGUI[] panel;

	public MainPanel() {
		panel = new CDTimerGUI[3];

		for(int i = 0; i < panel.length; i++) {
			panel[i] = new CDTimerGUI();
			add(panel[i]);
		}

	}

}
