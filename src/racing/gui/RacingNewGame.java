package racing.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import racing.gui.imageHandling.ImageLoader;
import racing.logic.RacingLogic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.io.IOException;
import java.awt.Insets;

/**
 * @author Vitor Santos 
 * @version 1.0
 * @created 16-Mai-2011 10:42:31
 */
public class RacingNewGame {

	public RacingGUI m_RacingGUI;
	private JFrame menuFrame = null;  //  @jve:decl-index=0:visual-constraint="91,22"
	private JPanel menuPane = null;
	private RacingMenuButton playButton = null;  //  @jve:decl-index=0:
	private JPanel buttonsPanel = null;
	private ImageLoader titleScreen = null;
	private RacingMenuButton chooseCarButton = null;
	private RacingMenuButton chooseMapButton = null;
	private RacingMenuButton backButton = null;
	
	private RacingLogic logic;
	

	public RacingNewGame(RacingLogic logic2)  throws IOException{
		logic = logic2;
		
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
			menuFrame.setTitle("New Game");
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
			titleScreen = new ImageLoader("Resources/Sprites/carsInARace.png");
			titleScreen.setBackground(new Color(150,150,255));
		}
		
		return titleScreen;
	}

	/**
	 * This method initializes playButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private RacingMenuButton getPlayButton() {
		if (playButton == null) {
			playButton = new RacingMenuButton();
			playButton.setText("Play");
			playButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						
							getMenuFrame().setVisible(false);
							RacingPlayerName playerQuery = new RacingPlayerName(logic);
							playerQuery.getPlayerNameFrame().setVisible(true);


					} catch (IOException e1) {
						System.out.println("Missing image...");
						System.exit(0);
					}
				}
			});
		}
		return playButton;
	}
	
	/**
	 * This method initializes chooseCarButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private RacingMenuButton getChooseCarButton() {
		if (chooseCarButton == null) {
			chooseCarButton = new RacingMenuButton();
			chooseCarButton.setText("Choose Car");
			chooseCarButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						getMenuFrame().setVisible(false);
						RacingCarSelector chooseCar = new RacingCarSelector(logic);
						chooseCar.getMenuFrame().setVisible(true);
					} catch (IOException e1) {
						System.out.println("Missing image...");
						System.exit(0);
					}
				}
			});
		}
		return chooseCarButton;
	}

	/**
	 * This method initializes chooseMapButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private RacingMenuButton getChooseMapButton() {
		if (chooseMapButton == null) {
			chooseMapButton = new RacingMenuButton();
			chooseMapButton.setText("Choose Map");
			chooseMapButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						// TODO PASSSAR ALGO
						getMenuFrame().setVisible(false);
						RacingMapSelection chooseMap = new RacingMapSelection(logic);
						chooseMap.getMenuFrame().setVisible(true);
					} catch (IOException e1) {
						System.out.println("Missing image...");
						System.exit(0);
					}
				}
			});
		}
		return chooseMapButton;
	}

	/**
	 * This method initializes backButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private RacingMenuButton getBackButton() {
		if (backButton == null) {
			backButton = new RacingMenuButton();
			backButton.setText("Back");
			backButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						getMenuFrame().setVisible(false);
						RacingMenu backMenu = new RacingMenu();
						backMenu.getMenuFrame().setVisible(true);
					} catch (IOException e1) {
						System.out.println("Missing image...");
						System.exit(0);
					}
				}
			});
		}
		return backButton;
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
			buttonsPanel.add(getPlayButton(), gridBagConstraints);
			buttonsPanel.add(getChooseCarButton(), gridBagConstraints2);
			buttonsPanel.add(getChooseMapButton(), gridBagConstraints3);
			buttonsPanel.add(getBackButton(), gridBagConstraints4);
		}
		return buttonsPanel;
	}
}