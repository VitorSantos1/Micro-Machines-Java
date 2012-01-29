package racing.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.io.IOException;
import java.awt.Insets;
import javax.swing.JButton;

import racing.gui.imageHandling.ImageLoader;
import racing.logic.GameSession;

/**
 * @author Vitor Santos 
 * @version 1.0
 * @created 16-Mai-2011 10:42:31
 */
public class RacingOptions {

	public RacingGUI m_RacingGUI;
	private JFrame menuFrame = null;  //  @jve:decl-index=0:visual-constraint="91,22"
	private JPanel menuPane = null;
	private RacingMenuButton continueButton = null;
	private JPanel buttonsPanel = null;
	private ImageLoader titleScreen = null;
	private RacingMenuButton restartButton = null;
	private RacingMenuButton soundButton = null;
	private RacingMenuButton mainMenuButton = null;
	private JButton configureButtonsButton = null;
	private GameSession backgroundGame = null;
	
	public RacingOptions() throws IOException
	{
		menuFrame = getMenuFrame();
	}

	public RacingOptions(GameSession gS) throws IOException
	{
		backgroundGame = gS;
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
			menuFrame.setTitle("Game Options");
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
		//TODO: Mudar imagem.
		if(titleScreen == null)
		{
			titleScreen = new ImageLoader("C:\\firefox.png");
			titleScreen.setBackground(new Color(150,150,255));
		}
		
		return titleScreen;
	}

	/**
	 * This method initializes continueButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private RacingMenuButton getContinueButton() {
		if (continueButton == null) {
			continueButton = new RacingMenuButton();
			continueButton.setText("Continue");
		}
		return continueButton;
	}
	
	/**
	 * This method initializes restartButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private RacingMenuButton getRestartButton() {
		if (restartButton == null) {
			restartButton = new RacingMenuButton();
			restartButton.setText("Restart");
		}
		return restartButton;
	}

	/**
	 * This method initializes soundButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private RacingMenuButton getSoundButton() {
		if (soundButton == null) {
			soundButton = new RacingMenuButton();
			soundButton.setText("Sound ON/OFF");
		}
		return soundButton;
	}

	/**
	 * This method initializes mainMenuButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private RacingMenuButton getMainMenuButton() {
		if (mainMenuButton == null) {
			mainMenuButton = new RacingMenuButton();
			mainMenuButton.setText("Return to Main Menu");
			mainMenuButton.addActionListener(new java.awt.event.ActionListener() {   
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
		return mainMenuButton;
	}
	
	/**
	 * This method initializes buttonsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getButtonsPanel() {
		if (buttonsPanel == null) {
			GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
			gridBagConstraints31.gridx = 0;
			gridBagConstraints31.insets = new Insets(0, 0, 10, 0);
			gridBagConstraints31.gridy = 3;
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
			buttonsPanel.add(getContinueButton(), gridBagConstraints);
			buttonsPanel.add(getRestartButton(), gridBagConstraints2);
			buttonsPanel.add(getSoundButton(), gridBagConstraints3);
			buttonsPanel.add(getMainMenuButton(), gridBagConstraints4);
			buttonsPanel.add(getConfigureButtonsButton(), gridBagConstraints31);
		}
		return buttonsPanel;
	}

	/**
	 * This method initializes configureButtonsButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getConfigureButtonsButton() {
		if (configureButtonsButton == null) {
			configureButtonsButton = new JButton();
			configureButtonsButton.setText("Set Controls");
			configureButtonsButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) 
				{
					try 
					{
						if(backgroundGame == null)
						{
							Component frame = null;
							JOptionPane.showMessageDialog(frame, "Option inacessible.", "Error!", JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							getMenuFrame().setVisible(false);
							RacingControllerConfig controlMenu = new RacingControllerConfig();
							controlMenu.getControllerConfigFrame().setVisible(true);
						}
					} 
					catch (IOException e1) 
					{
						System.out.println("Missing image...");
						System.exit(0);
					}
				}
			});
		}
		return configureButtonsButton;
	}
}