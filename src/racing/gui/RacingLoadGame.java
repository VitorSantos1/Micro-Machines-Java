package racing.gui;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JButton;

import racing.logic.GameSession;
import racing.logic.RacingLogic;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Rectangle;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class RacingLoadGame 
{

	private JFrame loadGameFrame = null;  //  @jve:decl-index=0:visual-constraint="241,119"
	private JPanel loadGamePane = null;
	private JLabel saveGameLabel = null;
	private JTextField loadGameField = null;
	private JButton searchButton = null;
	private JButton loadGameButton = null;
	private File savedGame = null;
	private JButton backToMenuButton = null;

	public RacingLoadGame()
	{
		loadGameFrame = getLoadGameFrame();
	}
	
	/**
	 * This method initializes saveGameFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	JFrame getLoadGameFrame() {
		if (loadGameFrame == null) {
			loadGameFrame = new JFrame();
			loadGameFrame.setSize(new Dimension(376, 169));
			loadGameFrame.setTitle("Load Game");
			loadGameFrame.setContentPane(getLoadGamePane());
			loadGameFrame.setContentPane(getLoadGamePane());
			loadGameFrame.setLocationRelativeTo(null);
		}
		return loadGameFrame;
	}

	/**
	 * This method initializes saveGamePane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getLoadGamePane() {
		if (loadGamePane == null) {
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints21.gridy = 8;
			gridBagConstraints21.weightx = 1.0;
			gridBagConstraints21.gridwidth = 3;
			gridBagConstraints21.gridx = 0;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 8;
			gridBagConstraints2.gridy = 8;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 7;
			gridBagConstraints1.gridy = 10;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 4;
			gridBagConstraints.gridwidth = 3;
			gridBagConstraints.gridy = 0;
			loadGamePane = new JPanel();
			loadGamePane.setLayout(null);
			loadGamePane.add(getSaveGameLabel(), gridBagConstraints);
			loadGamePane.add(getSaveGameField(), gridBagConstraints21);
			loadGamePane.add(getSearchButton(), gridBagConstraints2);
			loadGamePane.add(getLoadGameButton(), gridBagConstraints1);
			loadGamePane.setBackground(new Color(150,150,255));
			loadGamePane.add(getBackToMenuButton(), null);
		}
		return loadGamePane;
	}

	/**
	 * This method initializes saveGameLabel	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getSaveGameLabel() {
		if (saveGameLabel == null) {
			saveGameLabel = new JLabel("Load Game", JLabel.CENTER);
			saveGameLabel.setBounds(new Rectangle(117, 14, 123, 32));
			saveGameLabel.setForeground(Color.WHITE);
		}
		return saveGameLabel;
	}

	/**
	 * This method initializes saveGameField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getSaveGameField() {
		if (loadGameField == null) {
			loadGameField = new JTextField();
			loadGameField.setSize(new Dimension(225, 26));
			loadGameField.setLocation(new Point(25, 52));
			loadGameField.setText("Search for your game in the right button");
			loadGameField.setEditable(false);
		}
		return loadGameField;
	}

	/**
	 * This method initializes searchButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSearchButton() {
		if (searchButton == null) {
			searchButton = new JButton();
			searchButton.setText("Search");
			searchButton.setBounds(new Rectangle(262, 52, 75, 26));
			searchButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) 
				{
					final JFileChooser fs = new JFileChooser();
					
					int returnVal = fs.showOpenDialog(null);
					
					if(returnVal  == JFileChooser.APPROVE_OPTION)
					{
						savedGame = fs.getSelectedFile();
						loadGameField.setText(savedGame.getAbsolutePath());
					}
				}
			});
		}
		return searchButton;
	}

	/**
	 * This method initializes loadGameButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getLoadGameButton() {
		if (loadGameButton == null) {
			loadGameButton = new JButton();
			loadGameButton.setText("Load");
			loadGameButton.setBounds(new Rectangle(209, 90, 94, 26));
			loadGameButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) 
				{
					try 
					{
						ObjectInputStream gameToLoad = new ObjectInputStream(new FileInputStream(savedGame.getAbsolutePath()));
						
						GameSession gS = (GameSession) gameToLoad.readObject();
						
						Component frame = null;
						JOptionPane.showMessageDialog(frame, "File was loaded successfully!", "Success!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Resources/Sprites/accept.png"));
						
						getLoadGameFrame().setVisible(false);
						// TODO AQUI
						/*
						RacingGUI letsPlay = new RacingGUI(gS);
						letsPlay.getRacingFrame().setVisible(true);*/
					} 
					catch (FileNotFoundException e1) 
					{
						Component frame = null;
						JOptionPane.showMessageDialog(frame, "File was not found.", "Error!", JOptionPane.ERROR_MESSAGE);
					} 
					catch (IOException e1) 
					{
						Component frame = null;
						JOptionPane.showMessageDialog(frame, "File was not properly loaded.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					catch (ClassNotFoundException e1) {
						Component frame = null;
						JOptionPane.showMessageDialog(frame, "File was not a part of any game.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					catch (NullPointerException e1)
					{
						Component frame = null;
						JOptionPane.showMessageDialog(frame, "You must search for a saved game to load.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
		return loadGameButton;
	}

	/**
	 * This method initializes backToMenuButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBackToMenuButton() {
		if (backToMenuButton == null) {
			backToMenuButton = new JButton();
			backToMenuButton.setBounds(new Rectangle(52, 90, 131, 26));
			backToMenuButton.setText("Back To Menu");
			backToMenuButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) 
				{	
					try 
					{
						getLoadGameFrame().setVisible(false);
						RacingMenu backMenu = new RacingMenu();
						backMenu.getMenuFrame().setVisible(true);
					} 
					catch (IOException e1) 
					{
						System.out.println("Missing image...");
						System.exit(0);
					}
				}
			});
		}
		return backToMenuButton;
	}

}
