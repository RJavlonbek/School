

import java.util.Random;

public class ComputerPlayer {

	public ComputerPlayer()
	{
		// default constructor
	}

	private Random r = new Random();

	private int desiredX = 0;
	private int desiredY = 0;
	private int computerDifficulty = 1;

	// APPROXIMATELY 120+ possible moves to victory or tie for Insane computer
	private String[] moves = { "Z........", "....X....", "XO..Z....", "X.O...Z..", "X..OZ....", "X...O...Z",
			"X...ZO...", "X.Z...O..", "X...Z..O.", "X.Z.....O", "XOO.X...Z", "XO.OX...Z", "XO..XO..Z", "XO..X.O.Z",
			"XO..X..OZ", "XO..X.Z.O", "XOOZX.X.O", "XOZOX.X.O", "XO.ZXOX.O", "XO.ZX.XOO", "XOOZ..X..", "X.OO..X.Z",
			"X.OZO.X..", "X.OZ.OX..", "X.OZ..XO.", "X.OZ..X.O", "XOOO..XZX", "X.OOO.XZX", "X.OO.OXZX", "X.OOZ.XOX",
			"XO.OX...Z", "X.OOX...Z", "X..OXO..Z", "X..OX.O.Z", "X..OX..OZ", "X.ZOX...O", "XOXOX.Z.O", "XZXOXO..O",
			"XZXOX.O.O", "XZXOX..OO", "XO..O..ZX", "X.O.O.Z.X", "X..OOZ..X", "X..ZOO..X", "X.Z.O.O.X", "XZ..O..OX",
			"XOO.O.ZXX", "XO.OO.ZXX", "XO..OOZXX", "XOZ.O.OXX", "XOXOOZOXX", "XOXZOOOXX", "XOOZO.X.X", "X.OOO.XZX",
			"X.O.OOXZX", "X.OZO.XOX", "XOZOOX..X", "X.OOOXZ.X", "X.ZOOXO.X", "X.ZOOX.OX", "XOOOOXXZX", "XZOOOXXOX",
			"XO.XOOZ.X", "X.OXOOZ.X", "X.ZXOOO.X", "X..XOOZOX", "XOXXOOOZX", "XZXXOOOOX", "XOX.OZO.X", "XZXOO.O.X",
			"XZX.OOO.X", "X.X.OZOOX", "XXO.O.ZOX", "XXZOO..OX", "XXZ.OO.OX", "XXZ.O.OOX", "XXO.O.ZOX", "XXZ.OO.OX",
			"XXOOOZXOX", "XXOOOZXOX", "XXOZOOXOX", "XO..XO..Z", "X.O.XO..Z", "X..OXO..Z", "X...XOO.Z", "X...XO.OZ",
			"X.Z.XO..O", "XOX.XOZ.O", "XZXOXO..O", "XZX.XOO.O", "XZX.XO.OO", "XOX...O.Z", "XZXO..O..", "XZX.O.O..",
			"XZX..OO..", "XZX...OO.", "XZX...O.O", "XOXO.ZO.X", "XOX.OZO.X", "XOX.ZOO.X", "XOX.Z.OOX", "XO..X..OZ",
			"X.O.X..OZ", "X...X.OOZ", "X...XO.OZ", "X...X.OOZ", "X...X.ZOO", "XO.ZX.XOO", "X.OZX.XOO", "X.ZOX.XOO",
			"X..ZXOXOO", "XOX...Z.O", "XZXO....O", "XZX.O...O", "XZX..O..O", "XZX...O.O", "XZX....OO", "XOXOZ.X.O",
			"XOXZO.X.O", "XOXZ.OX.O", "XOXZ..XOO" };

	public void setComputerDifficulty(int a)
	{
		computerDifficulty = a;
	}

	public int getdesiredX()
	{
		return desiredX;
	}

	public int getdesiredY()
	{
		return desiredY;
	}

	public void decideMove(int board[][])
	{
		switch (computerDifficulty) {
		case 1:
			decideMoveEasy(board);
			break;
		case 2:
			decideMoveMedium(board);
			break;
		case 3:
			decideMoveImpossible(board);
			break;
		}
	}

	// easy stupid
	public void decideMoveEasy(int board[][])
	{
		boolean lookingForSpot = true;
		int x = 0;
		int y = 0;

		int lookingForToLong = 0; 
		while (lookingForSpot && lookingForToLong < 20)
		{
			lookingForToLong++; // should really not ever go over 9 but just intentionally overshooting
			x = r.nextInt(3);
			y = r.nextInt(3);

			if (board[x][y] == 0)
			{
				desiredX = x;
				desiredY = y;
				// aButtons[x][y].setText("O");
				// gameBoard[x][y] = -1;
				lookingForSpot = false;
			}
		}
	}

	public void decideMoveImpossible(int b[][])
	{

		String currentBoard = "";

		// compile string from current board
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				char c = 0;
				if (b[i][j] == 1)
				{
					c = 'X';
				} else if (b[i][j] == -1)
				{
					c = 'O';
				} else
				{
					c = '.';
				}
				currentBoard += c;
			}
		}

		String moveString = "";
		for (int i = 0; i < moves.length; i++)
		{
			if (customCompare(currentBoard, moves[i]))
			{
				moveString = moves[i];
				// System.out.println(i);
				break; // exit for loop when found
			}
		}

		// find the first occurrence of Z
		int movePoint = moveString.indexOf('Z');

		System.out.println(movePoint);
		// remainder after dividing by 3
		// I am using a 0 to 8 location of 3x3 matrix, so for example, the (1,1)
		// position will be 5
		// 0, 1, 2
		// 3, 4, 5
		// 6, 7, 8

		// so 5%3 = 2 for x
		int y = movePoint % 3;

		int x = 0;
		// reduce x down to 0,1,2 for x location
		if (movePoint >= 3 && movePoint <= 5)
		{
			x = 1;
		} else if (movePoint >= 6 && movePoint <= 8)
		{
			x = 2;
		}

		desiredX = x;
		desiredY = y;
	}

	private boolean customCompare(String a, String b)
	{

		for (int i = 0; i < a.length(); i++)
		{
			// ignore the Z
			if (b.charAt(i) != 'Z')
			{
				if (a.charAt(i) != b.charAt(i))
				{
					return false;
				}
			}
		}

		return true;
	}

	
	
	//Medium only blocks against 2 in a row winning
	// Medium won't go for 2 in a win always. its randomized
	
	// easy medium
	public void decideMoveMedium(int board[][])
	{
		boolean lookingForSpot = true;
		int x = 0;
		int y = 0;

		int lookingForToLong = 0;
		while (lookingForSpot && lookingForToLong < 20)
		{
			lookingForToLong++;
			boolean found = false;

			for (int i = 0; i < 3; i++)
			{
				if (Math.abs(board[i][0] + board[i][1] + board[i][2]) == 2)
				{
					for (int yy = 0; yy < 3; yy++)
					{
						if (board[i][yy] == 0)
						{
							desiredY = yy; // set it to the one unselected
						}
					}
					desiredX = i;
					found = true;
					break;
				}
			}

			if (!found)
			{
				for (int i = 0; i < 3; i++)
				{
					if (Math.abs(board[0][i] + board[1][i] + board[2][i]) == 2)
					{
						for (int xx = 0; xx < 3; xx++)
						{
							if (board[xx][i] == 0)
							{
								desiredX = xx; // set it to the one unselected
							}
						}
						desiredY = i;
						found = true;
						break;
					}

				}
			}
			if (!found)
			{
				System.out.println("" + board[0][0] + " " + board[1][1] + " " + board[2][2] + " ");
				if (Math.abs(board[0][0] + board[1][1] + board[2][2]) == 2)
				{
					if (board[0][0] == 0)
					{
						desiredX = 0;
						desiredY = 0;
					} else if (board[1][1] == 0)
					{
						desiredX = 1;
						desiredY = 1;
					} else if (board[2][2] == 0)
					{
						desiredX = 2;
						desiredY = 2;
					}
					System.out.println("NOTTT");
					found = true;
				} else if (Math.abs(board[2][0] + board[1][1] + board[0][2]) == 2)
				{
					if (board[2][0] == 0)
					{
						desiredX = 2;
						desiredY = 0;
					} else if (board[1][1] == 0)
					{
						desiredX = 1;
						desiredY = 1;
					} else if (board[0][2] == 0)
					{
						desiredX = 0;
						desiredY = 2;
					}
					found = true;
				}
			}

			if (found)
			{
				lookingForSpot = false;
			} else
			{
				x = r.nextInt(3);
				y = r.nextInt(3);

				if (board[x][y] == 0)
				{
					desiredX = x;
					desiredY = y;
					// aButtons[x][y].setText("O");
					// gameBoard[x][y] = -1;
					lookingForSpot = false;
				}
			}
		}
	}

}
