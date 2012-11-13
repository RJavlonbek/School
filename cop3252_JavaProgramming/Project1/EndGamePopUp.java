
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


//This is just for the popup at end of game, to reset game,  for X wins, O wins, or game tied
// It auto hides and disposes itself when done. 
public class EndGamePopUp extends JFrame{

	
	public EndGamePopUp(GamePanel gp, String message) {
		final GamePanel g = gp;
		
		setSize(205, 105);
		setLocation(400, 300);
		setVisible(true);
		setResizable(false);
		this.setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel label = new JLabel(message);
		label.setFont(new Font("Verdana", 0, 26));
		label.setSize(200, 50);
		label.setLocation(00,0);
		
		add(label);
		
		JButton playAgainButton = new JButton("Play Again?");
		playAgainButton.setLocation(0, 50);
		playAgainButton.setSize(100,30);
		playAgainButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				g.resetGame();
				setVisible(false);
				dispose();
			}
		});
		add(playAgainButton);
		
		
		JButton cancelSettingButton = new JButton("Cancel");
		cancelSettingButton.setLocation(100, 50);
		cancelSettingButton.setSize(100,30);
		cancelSettingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();
		}});
		
		add(cancelSettingButton);	
	}
}
