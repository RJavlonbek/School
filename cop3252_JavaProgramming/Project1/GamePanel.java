
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class GamePanel extends JPanel {

	private JButton aButtons[][] = new JButton[3][3];
	private int gameBoard[][] = new int[3][3];
	private boolean XWin = false;
	private boolean OWin = false;
	private boolean playing = true;
	private Color colorGarnet = new Color(81, 2, 21);
	private Color colorBlack = new Color(35, 31, 32);
	private Color colorGold = new Color(180, 151, 90);
	private boolean computerEnabled = false;

	private int computerDifficulty = 1; // 1 easy, 2 medium, 3 insane
	private int turn = 0;

	public void resetGame()
	{
		gameBoard = new int[3][3];
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				aButtons[i][j].setText("");
				aButtons[i][j].setEnabled(true);
				aButtons[i][j].setBackground(colorBlack);
				aButtons[i][j].setForeground(colorGold);
			}
		}
		playing = true;
		XWin = false;
		OWin = false;

		// computerEnabled = true;

		// System.out.println(UIManager.getColor("Button.background"));

		// insane computer must go first
		if (computerDifficulty == 3 && computerEnabled)
		{
			aButtons[0][0].setText("X");
			gameBoard[0][0] = 1;
			aButtons[0][0].setEnabled(false);
		}
	}

	public GamePanel()
	{
		UIManager.getDefaults().put("Button.disabledText", colorGold);
		UIManager.getDefaults().put("Button.disabledBackground", colorBlack);

		GridLayout gl = new GridLayout(3, 3);
		this.setLayout(gl);

		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				aButtons[i][j] = new JButton("");
				aButtons[i][j].setFont(new Font("Verdana", Font.BOLD, 140));
				aButtons[i][j].setBackground(colorBlack);
				aButtons[i][j].setName(i + "" + j);
				aButtons[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt)
					{
						aButtonActionPerformed(evt);
					}
				});
				add(aButtons[i][j]);
			}
		}
	}

	private void aButtonActionPerformed(ActionEvent evt)
	{
		System.out.println("You pressed " + ((JButton) evt.getSource()).getName());

		turn++;
		if (!XWin && !OWin && playing)
		{
			int locX = Integer.parseInt(String.valueOf(((JButton) evt.getSource()).getName().charAt(0)));
			int locY = Integer.parseInt(String.valueOf(((JButton) evt.getSource()).getName().charAt(1)));

			if (computerEnabled)
			{
				aButtons[locX][locY].setText("O");
				gameBoard[locX][locY] = -1;
			} else
			{
				if (turn % 2 == 0)
				{
					aButtons[locX][locY].setText("X");
					gameBoard[locX][locY] = 1;
				} else
				{
					aButtons[locX][locY].setText("O");
					gameBoard[locX][locY] = -1;
				}
			}

			aButtons[locX][locY].setEnabled(false);

			checkWinner();
		}

		if (computerEnabled && playing)
		{
			ComputerPlayer comp = new ComputerPlayer();
			comp.setComputerDifficulty(computerDifficulty);

			// computer takes turn
			if (!OWin && !XWin)
			{
				// turn++;

				comp.decideMove(gameBoard);
				int compX = comp.getdesiredX();
				int compY = comp.getdesiredY();

				aButtons[compX][compY].setText("X");
				gameBoard[compX][compY] = 1;
				aButtons[compX][compY].setEnabled(false);
				checkWinner();

			}
		}

	}

	private void checkWinner()
	{
		// check rows
		for (int i = 0; i < 3; i++)
		{
			if (gameBoard[i][0] + gameBoard[i][1] + gameBoard[i][2] == 3)
			{
				XWin = true;
				aButtons[i][0].setBackground(colorGarnet);
				aButtons[i][1].setBackground(colorGarnet);
				aButtons[i][2].setBackground(colorGarnet);

			} else if (gameBoard[i][0] + gameBoard[i][1] + gameBoard[i][2] == -3)
			{
				OWin = true;
				aButtons[i][0].setBackground(colorGarnet);
				aButtons[i][1].setBackground(colorGarnet);
				aButtons[i][2].setBackground(colorGarnet);
			}
		}
		// check columns
		for (int j = 0; j < 3; j++)
		{
			if (gameBoard[0][j] + gameBoard[1][j] + gameBoard[2][j] == 3)
			{
				XWin = true;
				aButtons[0][j].setBackground(colorGarnet);
				aButtons[1][j].setBackground(colorGarnet);
				aButtons[2][j].setBackground(colorGarnet);
			} else if (gameBoard[0][j] + gameBoard[1][j] + gameBoard[2][j] == -3)
			{
				OWin = true;
				aButtons[0][j].setBackground(colorGarnet);
				aButtons[1][j].setBackground(colorGarnet);
				aButtons[2][j].setBackground(colorGarnet);
			}
		}

		// check diagonals
		if (gameBoard[0][0] + gameBoard[1][1] + gameBoard[2][2] == 3)
		{
			XWin = true;
			aButtons[0][0].setBackground(colorGarnet);
			aButtons[1][1].setBackground(colorGarnet);
			aButtons[2][2].setBackground(colorGarnet);
		} else if (gameBoard[0][0] + gameBoard[1][1] + gameBoard[2][2] == -3)
		{
			OWin = true;
			aButtons[0][0].setBackground(colorGarnet);
			aButtons[1][1].setBackground(colorGarnet);
			aButtons[2][2].setBackground(colorGarnet);
		}

		// check diagonals
		if (gameBoard[2][0] + gameBoard[1][1] + gameBoard[0][2] == 3)
		{
			XWin = true;
			aButtons[2][0].setBackground(colorGarnet);
			aButtons[1][1].setBackground(colorGarnet);
			aButtons[0][2].setBackground(colorGarnet);
		} else if (gameBoard[2][0] + gameBoard[1][1] + gameBoard[0][2] == -3)
		{
			OWin = true;
			aButtons[2][0].setBackground(colorGarnet);
			aButtons[1][1].setBackground(colorGarnet);
			aButtons[0][2].setBackground(colorGarnet);
		}

		int possibleMoves = 9;
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				if (gameBoard[i][j] != 0)
				{
					possibleMoves--;
				}
			}
		}

		playing = true;

		if (XWin)
		{
			System.out.println("X WINS");
			// JOptionPane.showMessageDialog(null, "X WINS!");
			new EndGamePopUp(this, "     X Wins!!");
			playing = false;
		} else if (OWin)
		{
			System.out.println("O WINS");

			new EndGamePopUp(this, "     O Wins!!");

			// JOptionPane.showMessageDialog(null, "O WINS!");
			playing = false;
		} else if (possibleMoves <= 0 && playing)
		{
			// JOptionPane.showMessageDialog(null, "Game Tied!!");
			new EndGamePopUp(this, "  Game Tied!!!");
			playing = false;
		}

		// [ 0 , 1 , 2 ] , [ 3 , 4 , 5 ] , [ 6 , 7 , 8 ] ,
		// [ 0 , 3 , 6 ] , [ 1 , 4 , 7 ] , [ 2 , 5 , 8 ] ,
		// [ 0 , 4 , 8 ] , [ 2 , 4 , 6 ]

	}

	public void saveSettings(String hvC, String compDiff)
	{
		if (hvC.compareTo("H") == 0)
		{
			computerEnabled(false);
		} else
		{ // C
			computerEnabled(true);
		}

		if (compDiff.compareTo("EC") == 0)
		{
			setComputerDifficulty(1);
		} else if (compDiff.compareTo("IC") == 0)
		{
			setComputerDifficulty(3);
		} else
		{
			setComputerDifficulty(2); // medium
		}

		resetGame();
	}

	private void setComputerDifficulty(int c)
	{
		computerDifficulty = c;
	}

	private void computerEnabled(boolean b)
	{
		computerEnabled = b;
	}
}
