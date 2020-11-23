import GameView.gameFrame;
import GameView.gameSession;

import javax.swing.*;

/******************************************************************************
 * The RunUNO class is the main class of the UNO game.
 *
 * @author TonyChanelle
 * @author add name
 * @author add name
 ******************************************************************************/
public class RunUNO {
    public static void main(String[] args) {

        //Create Frame and invoke it.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new gameFrame();
                frame.setResizable(false);
                frame.setLocation(0, 0);
                frame.pack();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
