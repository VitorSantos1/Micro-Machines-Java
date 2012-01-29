package racing.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import racing.gui.imageHandling.ImageLoader;
import racing.logic.Map;
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
public class RacingMapSelection {

	public RacingGUI m_RacingGUI;
	private JFrame menuFrame = null;  //  @jve:decl-index=0:visual-constraint="91,22"
	private JPanel menuPane = null;
	private RacingMenuButton easyButton = null;
	private JPanel buttonsPanel = null;
	private ImageLoader titleScreen = null;
	private RacingMenuButton mediumButton = null;
	private RacingMenuButton hardButton = null;
	private RacingMenuButton backButton = null;
	private RacingMenuButton editorButton = null;
	
	private RacingLogic logic;
	
	public RacingMapSelection(RacingLogic log) throws IOException {
		this.logic = log;
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
			menuFrame.setTitle("Map Selection");
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
			titleScreen = new ImageLoader("C:\\firefox.png");
			titleScreen.setBackground(new Color(150,150,255));
		}
		
		return titleScreen;
	}

	/**
	 * This method initializes easyButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private RacingMenuButton getEasyButton() {
		if (easyButton == null) {
			easyButton = new RacingMenuButton();
			easyButton.setText("Easy");
			easyButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						getMenuFrame().setVisible(false);
						logic.getGame().getRace().setRaceMap(new Map(true, 5));
						RacingNewGame backMenu = new RacingNewGame(logic);
						backMenu.getMenuFrame().setVisible(true);
					} catch (IOException e1) {
						System.out.println("Missing image...");
						System.exit(0);
					}
				}
			});
		}
		return easyButton;
	}
	
	/**
	 * This method initializes mediumButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private RacingMenuButton getMediumButton() {
		if (mediumButton == null) {
			mediumButton = new RacingMenuButton();
			mediumButton.setText("Medium");

			mediumButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						// TODO AQUI OUTRO MAPA
						getMenuFrame().setVisible(false);
						logic.getGame().getRace().setRaceMap(new Map(true, 4));
						RacingNewGame backMenu = new RacingNewGame(logic);
						backMenu.getMenuFrame().setVisible(true);
					} catch (IOException e1) {
						System.out.println("Missing image...");
						System.exit(0);
					}
				}
			});

		}
		return mediumButton;
	}

	/**
	 * This method initializes hardButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private RacingMenuButton getHardButton() {
		if (hardButton == null) {
			hardButton = new RacingMenuButton();
			hardButton.setText("Hard");
			hardButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						getMenuFrame().setVisible(false);
						
						// TODO RacingNewGame backMenu = new RacingNewGame(carName, "Hard");
						//backMenu.getMenuFrame().setVisible(true);
					} catch (IOException e1) {
						System.out.println("Missing image...");
						System.exit(0);
					}
				}
			});
		}
		return hardButton;
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
						RacingNewGame backMenu = new RacingNewGame(logic);
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
	 * This method initializes editorButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private RacingMenuButton getEditorButton() {
		if (editorButton == null) {
			editorButton = new RacingMenuButton();
			editorButton.setText("Editor");
			editorButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try 
					{
						getMenuFrame().setVisible(false);
						RacingMapSizeQuery mQuery = new RacingMapSizeQuery(logic);
						mQuery.getMapQueryFrame().setVisible(true);
					} 
					catch (IOException e1) 
					{
						System.out.println("Missing image...");
						System.exit(0);
					}
					
				}
			});
		}
		return editorButton;
	}

	/**
	 * This method initializes buttonsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getButtonsPanel() {
		if (buttonsPanel == null) {
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.insets = new Insets(0, 0, 10, 0);
			gridBagConstraints1.gridy = 3;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.gridy = 4;
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
			buttonsPanel.add(getEasyButton(), gridBagConstraints);
			buttonsPanel.add(getMediumButton(), gridBagConstraints2);
			buttonsPanel.add(getHardButton(), gridBagConstraints3);
			buttonsPanel.add(getBackButton(), gridBagConstraints4);
			buttonsPanel.add(getEditorButton(), gridBagConstraints1);
		}
		return buttonsPanel;
	}
}