package racing.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import racing.gui.imageHandling.ImageLoader;
import racing.logic.RacingLogic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.io.IOException;
import java.awt.Insets;

/**
 * @author Vitor Santos 
 * @version 1.0
 * @created 16-Mai-2011 10:42:31
 */
public class RacingMenu {

	public RacingGUI m_RacingGUI;
	private JFrame menuFrame = null;  //  @jve:decl-index=0:visual-constraint="91,22"
	private JPanel menuPane = null;
	private RacingMenuButton newGameButton = null;
	private JPanel buttonsPanel = null;
	private ImageLoader titleScreen = null;
	private RacingMenuButton loadGameButton = null;
	private RacingMenuButton optionsButton = null;  //  @jve:decl-index=0:
	private RacingMenuButton exitButton = null;
	private RacingLogic logic;
	
	public RacingMenu() throws IOException
	{
		logic = new RacingLogic();
		menuFrame = getMenuFrame();
	}

	public void finalize() throws Throwable {

	}

	/**
	 * This method initializes menuFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 * @throws IOException 
	 */
	JFrame getMenuFrame() throws IOException {
		if (menuFrame == null) {
			menuFrame = new JFrame();
			menuFrame.setSize(new Dimension(600, 480));
			menuFrame.setTitle("Main Menu");
			menuFrame.setContentPane(getMenuPane());
			menuFrame.setVisible(true);
			menuFrame.setResizable(false);
			menuFrame.setLocationRelativeTo(null);
		}
		return menuFrame;
	}

	/**
	 * This method initializes menuPane	
	 * 	
	 * @return javax.swing.JPanel	
	 * @throws IOException 
	 */
	private JPanel getMenuPane() throws IOException {
		if (menuPane == null) {
			menuPane = new JPanel();
			BorderLayout bL = new BorderLayout();
			menuPane.setLayout(bL);
			menuPane.setBackground(new Color(150,150,255));
			
			menuPane.add(getTitleScreen(), BorderLayout.NORTH);
			menuPane.add(getButtonsPanel(), BorderLayout.CENTER);
		}
		return menuPane;
	}

	private ImageLoader getTitleScreen() throws IOException 
	{
		if(titleScreen == null)
		{
			titleScreen = new ImageLoader("Resources/Sprites/machines.png");
			titleScreen.setBackground(new Color(150,150,255));
		}
		
		return titleScreen;
	}

	/**
	 * This method initializes newGameButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private RacingMenuButton getNewGameButton() {
		if (newGameButton == null) {
			newGameButton = new RacingMenuButton();
			newGameButton.setText("New Game");
			newGameButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						getMenuFrame().setVisible(false);
						RacingNewGame newGame = new RacingNewGame(logic);
						newGame.getMenuFrame().setVisible(true);
					} catch (IOException e1) {
						System.out.println("Missing image...");
						System.exit(0);
					}
				}
			});
		}
		return newGameButton;
	}
	
	/**
	 * This method initializes loadGameButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private RacingMenuButton getLoadGameButton() {
		if (loadGameButton == null) {
			loadGameButton = new RacingMenuButton();
			loadGameButton.setText("Load Game");
			loadGameButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						getMenuFrame().setVisible(false);
						RacingLoadGame loadGame = new RacingLoadGame();
						loadGame.getLoadGameFrame().setVisible(true);
					} catch (IOException e1) {
						System.out.println("Missing image...");
						System.exit(0);
					}
				}
			});
		}
		return loadGameButton;
	}

	/**
	 * This method initializes optionsButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private RacingMenuButton getOptionsButton() {
		if (optionsButton == null) {
			optionsButton = new RacingMenuButton();
			optionsButton.setText("Options");
			optionsButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						getMenuFrame().setVisible(false);
						RacingOptions opt = new RacingOptions();
						opt.getMenuFrame().setVisible(true);
					} catch (IOException e1) {
						System.out.println("Missing image...");
						System.exit(0);
					}
				}
			});
		}
		return optionsButton;
	}

	/**
	 * This method initializes exitButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private RacingMenuButton getExitButton() {
		if (exitButton == null) {
			exitButton = new RacingMenuButton();
			exitButton.setText("Exit");
			exitButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return exitButton;
	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, IOException
	{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		new RacingMenu();
	}

	/**
	 * This method initializes buttonsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getButtonsPanel() {
		if (buttonsPanel == null) {
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.gridy = 3;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 0;
			gridBagConstraints3.insets = new Insets(0, 0, 10, 0);
			gridBagConstraints3.gridwidth = 2;
			gridBagConstraints3.gridy = 2;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.insets = new Insets(0, 0, 9, 0);
			gridBagConstraints2.gridy = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.insets = new Insets(0, 0, 10, 0);
			gridBagConstraints.gridy = 0;
			buttonsPanel = new JPanel();
			buttonsPanel.setLayout(new GridBagLayout());
			buttonsPanel.setBackground(new Color(150,150,255));
			buttonsPanel.add(getNewGameButton(), gridBagConstraints);
			buttonsPanel.add(getLoadGameButton(), gridBagConstraints2);
			buttonsPanel.add(getOptionsButton(), gridBagConstraints3);
			buttonsPanel.add(getExitButton(), gridBagConstraints4);
		}
		return buttonsPanel;
	}
}