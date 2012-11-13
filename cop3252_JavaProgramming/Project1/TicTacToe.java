
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;



public class TicTacToe {
	
	public static JFrame app2;
	public static GamePanel gameBoard;
	
	public static void main(String[] args)
	{
		
		System.out.println("Tic-Tac-Toe by Adam Gorman");
		JLabel picLabel;
		BufferedImage image = null;
		BufferedImage image2 = null;
		try {
			image = ImageIO.read(new File("fsu.jpg"));
			image2 = ImageIO.read(new File("created.jpg"));
		}catch(IOException ex) {
			//catch it or do whatever
		}
		
		
		JFrame app = new JFrame();
		app.setResizable(false);
		app.setSize(500, 692);
		app.setLocation(100, 100);
		app.setJMenuBar(createMenus());
		app.setVisible(true);
		app.setLayout(null);
		app.setTitle("Tic-Tac-Toe");

		// Top bar
		picLabel = new JLabel(new ImageIcon(image));
		picLabel.setSize(500,92);
		picLabel.setLocation(0, 0);
		app.add(picLabel);
		
		// Main game
		gameBoard = new GamePanel();
		gameBoard.setLocation(0,92);
		gameBoard.setSize(500, 500);
		app.add(gameBoard);
		
		//bottom bar
		JLabel picLabel2 = new JLabel(new ImageIcon(image2));
		picLabel2.setSize(500,50);
		picLabel2.setLocation(0, 592);
		app.add(picLabel2);
		
		
		////////////////////////////////////////////////////////



		//options window
		
		app2 = new JFrame();	
		app2.setResizable(false);
		app2.setSize(200, 300);
		app2.setLocation(180, 200);
		app2.setVisible(false);
		app2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		app2.setLayout(new FlowLayout());


		
		//Creating window for Options
		JPanel jPanel1 = new JPanel();
		TitledBorder title;
		title = BorderFactory.createTitledBorder("Computer Difficulty");
		jPanel1.setBorder(title);
		jPanel1.setLayout(new GridLayout(3,1));
		
		
		
		JRadioButton easyComp = new JRadioButton("Easy", true); // all random
		easyComp.setName("Easy Computer");
		easyComp.setActionCommand("EC");
		
		
		JRadioButton mediumComp = new JRadioButton("Medium", false); //random + 2 in a row defense 
		mediumComp.setName("Medium Computer");
		mediumComp.setActionCommand("MC");

		JRadioButton insaneComp = new JRadioButton("Insane", false);
		insaneComp.setName("Insane Computer");
		insaneComp.setActionCommand("IC");
		
		cDiff = new ButtonGroup();
		cDiff.add(insaneComp);
		cDiff.add(mediumComp);
		cDiff.add(easyComp);	
		
		jPanel1.add(easyComp);
		jPanel1.add(mediumComp);
		jPanel1.add(insaneComp);		
		jPanel1.setPreferredSize(new Dimension(150, 100));

		app2.add(jPanel1);
		
		///////////////////////////////////////
		
		JPanel jPanel2 = new JPanel();
		TitledBorder title2;
		title2 = BorderFactory.createTitledBorder("Opponent");
		jPanel2.setBorder(title2);
		jPanel2.setLayout(new GridLayout(2,1));
		
		JRadioButton human = new JRadioButton("Human", false); 
		human.setActionCommand("H");
		JRadioButton comp = new JRadioButton("Computer", true); 
		comp.setActionCommand("C");
		opponentType = new ButtonGroup();
		opponentType.add(human);
		opponentType.add(comp);
		
		jPanel2.add(human);
		jPanel2.add(comp);		
		jPanel2.setPreferredSize(new Dimension(150, 100));
		app2.add(jPanel2);
		////////////////////////////////////////////////////////
		
		
		
		JPanel jpanel3 = new JPanel();
		JButton okSettingButton = new JButton("Okay");
		okSettingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				TicTacToe.saveSettings();
				app2.setVisible(false);
				
			}

			});
		
		JButton cancelSettingButton = new JButton("Cancel");
		cancelSettingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//don't save, do nothing but close
				app2.setVisible(false);
				
			}});
		
		jpanel3.add(okSettingButton);
		jpanel3.add(cancelSettingButton);
		
		
		
		app2.add(jpanel3);
	

		////////////////////////////////////////////////////////

		
		//JLabel whoIsPlaying = new JLabel("X's Turn");
		//whoIsPlaying.setFont(new Font("Verdana",0,20));
		//whoIsPlaying.setLocation(30,600);
		//whoIsPlaying.setSize(300,30);
		//app.add(whoIsPlaying);
		
	
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
	}
	public static ButtonGroup cDiff;
	public static ButtonGroup opponentType;
	
	private static void saveSettings() {
		String compDiff = cDiff.getSelection().getActionCommand();
		String HvC = opponentType.getSelection().getActionCommand();
		System.out.println(compDiff);
		System.out.println(HvC);
		gameBoard.saveSettings(HvC, compDiff);
		
	}
	
	private static JMenuBar createMenus()
	{
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuItem;

		//Create the menu bar.
		menuBar = new JMenuBar();

		//Build the first menu.
		menu = new JMenu("Game");
		menuBar.add(menu);

		// ////////////////////////////////////////////////////////////
		
		menuItem = new JMenuItem("New Game", KeyEvent.VK_N);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event)
			{
				// NEW GAME!
				gameBoard.resetGame();
			}
		});
		
		menuItem.setAccelerator(KeyStroke.getKeyStroke("F1"));
		menu.add(menuItem);
		
		// ////////////////////////////////////////////////////////////

		menu.addSeparator();
		
		// ////////////////////////////////////////////////////////////
		
		menuItem = new JMenuItem("Statistics", KeyEvent.VK_T);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event)
			{
				JOptionPane.showMessageDialog(null, "Statistics are not suppoted yet.");
				// Do something? show statistis
			}
		});
		menu.add(menuItem);
		
		// ////////////////////////////////////////////////////////////
		menuItem = new JMenuItem("Options");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event)
			{
				app2.setVisible(true);
				// Do something? Open Options
			}
		});
		menu.add(menuItem);
		
		// ////////////////////////////////////////////////////////////
		
		menuItem = new JMenuItem("Change Appearance", KeyEvent.VK_T);
		menu.add(menuItem);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event)
			{
				// Do something? allow user to change grahpics of icons
				JOptionPane.showMessageDialog(null, "Change Appearances are not suppoted yet.");
			}
		});
		
		// ////////////////////////////////////////////////////////////
		
		menu.addSeparator();
		
		// ////////////////////////////////////////////////////////////
		
		menuItem = new JMenuItem("Exit", KeyEvent.VK_T);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event)
			{
				System.exit(0);
			}
		});
		
		menu.add(menuItem);
		
		// ////////////////////////////////////////////////////////////
		
		
		
		//Build second menu in the menu bar.
		menu = new JMenu("Help");
		
		menu.getAccessibleContext().setAccessibleDescription("This menu does nothing");
		
		menuItem = new JMenuItem("About Tic-Tac-Toe");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event)
			{
				// Do something? Open Options
				JOptionPane.showMessageDialog(null, "Tic Tac Toe\nVersion 1.0\nCreated by Adam Gorman\nargorman@admin.fsu.edu\n\nFSU COP3252\nJava Programming");
				
			}
		});
		menuItem.setAccelerator(KeyStroke.getKeyStroke("F12"));
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Go to FSU online");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event)
			{
				try {
			         String url = "http://www.fsu.edu";
			         java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
			       }
			       catch (Exception e) {
			           System.out.println(e.getMessage());
			       }				
			}
		});
		menu.add(menuItem);
		
		
		
		menuItem = new JMenuItem("Learn more about Java");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event)
			{
				try
				{
					String url = "http://www.java.com";
					java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
				} catch (Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
		});
		
		menu.add(menuItem);
		menuBar.add(menu);
		return menuBar;
	}

}
