package racing.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import racing.gui.imageHandling.ImageLoader;
import racing.logic.Drifter;
import racing.logic.Player;
import racing.logic.RacingLogic;
import racing.logic.Runner;
import racing.logic.Solid;
import racing.logic.Speeder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;
import java.io.IOException;
import java.awt.Insets;

public class RacingCarSelector {

	public RacingGUI m_RacingGUI;
	private JFrame menuFrame = null;  //  @jve:decl-index=0:visual-constraint="91,22"
	private JPanel menuPane = null;
	private RacingMenuButton drifterButton = null;  //  @jve:decl-index=0:
	private JPanel buttonsPanel = null;
	private ImageLoader titleScreen = null;
	private RacingMenuButton runnerButton = null;  //  @jve:decl-index=0:
	private RacingMenuButton speederButton = null;  //  @jve:decl-index=0:
	private RacingMenuButton backButton = null;
	private RacingMenuButton solidButton = null;  //  @jve:decl-index=0:
	
	private RacingLogic logic;

	public RacingCarSelector(RacingLogic log) throws IOException {
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
			menuFrame = new JFrame("Car Selection");
			menuFrame.setSize(new Dimension(600, 480));
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
			titleScreen = new ImageLoader("");
			titleScreen.setBackground(new Color(150,150,255));
		}
		
		return titleScreen;
	}

	/**
	 * This method initializes easyButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private RacingMenuButton getDrifterButton() {
		if (drifterButton == null) {
			drifterButton = new RacingMenuButton();
			drifterButton.setText("Drifter");
			drifterButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						getMenuFrame().setVisible(false);
						logic.getPlayer().setM_Car(new Drifter());
						logic.getGame().getRace().setCarPos();
						RacingNewGame backMenu = new RacingNewGame(logic);
						backMenu.getMenuFrame().setVisible(true);
					} catch (IOException e1) {
						System.out.println("Missing image...");
						System.exit(0);
					}
				}
			});
		}
		return drifterButton;
	}
	
	/**
	 * This method initializes mediumButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private RacingMenuButton getRunnerButton() {
		if (runnerButton == null) {
			runnerButton = new RacingMenuButton();
			runnerButton.setText("Runner");
			runnerButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						getMenuFrame().setVisible(false);
						logic.getPlayer().setM_Car(new Runner());
						logic.getGame().getRace().setCarPos();
						RacingNewGame backMenu = new RacingNewGame(logic);
						backMenu.getMenuFrame().setVisible(true);
					} catch (IOException e1) {
						System.out.println("Missing image...");
						System.exit(0);
					}
				}
			});
		}
		return runnerButton;
	}

	/**
	 * This method initializes hardButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private RacingMenuButton getSpeederButton() {
		if (speederButton == null) {
			speederButton = new RacingMenuButton();
			speederButton.setText("Speeder");
			speederButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						getMenuFrame().setVisible(false);
						logic.getPlayer().setM_Car(new Speeder());
						logic.getGame().getRace().setCarPos();
						RacingNewGame backMenu = new RacingNewGame(logic);
						backMenu.getMenuFrame().setVisible(true);
					} catch (IOException e1) {
						System.out.println("Missing image...");
						System.exit(0);
					}
				}
			});
		}
		return speederButton;
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
	private RacingMenuButton getSolidButton() {
		if (solidButton == null) {
			solidButton = new RacingMenuButton();
			solidButton.setText("Solid");
			solidButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						getMenuFrame().setVisible(false);
						logic.getPlayer().setM_Car(new Solid());
						logic.getGame().getRace().setCarPos();
						RacingNewGame backMenu = new RacingNewGame(logic);
						backMenu.getMenuFrame().setVisible(true);
					} catch (IOException e1) {
						System.out.println("Missing image...");
						System.exit(0);
					}
				}
			});
		}
		return solidButton;
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
			buttonsPanel.add(getDrifterButton(), gridBagConstraints);
			buttonsPanel.add(getRunnerButton(), gridBagConstraints2);
			buttonsPanel.add(getSpeederButton(), gridBagConstraints3);
			buttonsPanel.add(getSolidButton(), gridBagConstraints1);
			buttonsPanel.add(getBackButton(), gridBagConstraints4);
		}
		return buttonsPanel;
	}
	
	public JFrame putFrameOnCenter(JFrame frameToCenter)
	{
		//Size of the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		//Center coordinates
		int width = frameToCenter.getSize().width;
		int height = frameToCenter.getSize().height;
		
		int xCenter = (dim.width-width)/2;
		int yCenter = (dim.height-height)/2;
		
		//Move frame
		frameToCenter.setLocation(xCenter, yCenter);
		
		return frameToCenter;
	}
}