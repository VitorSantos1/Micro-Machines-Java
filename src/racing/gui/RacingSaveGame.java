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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class RacingSaveGame
{

	private JFrame saveGameFrame = null;  //  @jve:decl-index=0:visual-constraint="241,119"
	private JPanel saveGamePane = null;
	private JLabel saveGameLabel = null;
	private JTextField loadGameField = null;
	private JButton searchButton = null;
	private JButton saveGameButton = null;
	private File gameToLoad = null;
	private JButton backToGameButton = null;
	private GameSession gameToBeSaved = null;

	public RacingSaveGame(GameSession gS)
	{
		gameToBeSaved = gS;
		saveGameFrame = getSaveGameFrame();
	}
	
	/**
	 * This method initializes saveGameFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	JFrame getSaveGameFrame() {
		if (saveGameFrame == null) {
			saveGameFrame = new JFrame();
			saveGameFrame.setSize(new Dimension(391, 169));
			saveGameFrame.setTitle("Save Game");
			saveGameFrame.setContentPane(getSaveGamePane());
			saveGameFrame.setContentPane(getSaveGamePane());
			saveGameFrame.setLocationRelativeTo(null);
		}
		return saveGameFrame;
	}

	/**
	 * This method initializes saveGamePane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getSaveGamePane() {
		if (saveGamePane == null) {
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
			saveGamePane = new JPanel();
			saveGamePane.setLayout(null);
			saveGamePane.add(getSaveGameLabel(), gridBagConstraints);
			saveGamePane.add(getSaveGameField(), gridBagConstraints21);
			saveGamePane.add(getSearchButton(), gridBagConstraints2);
			saveGamePane.add(getSaveGameButton(), gridBagConstraints1);
			saveGamePane.setBackground(new Color(150,150,255));
			saveGamePane.add(getBackToGameButton(), null);
		}
		return saveGamePane;
	}

	/**
	 * This method initializes saveGameLabel	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getSaveGameLabel() {
		if (saveGameLabel == null) {
			saveGameLabel = new JLabel("Save Game", JLabel.CENTER);
			saveGameLabel.setBounds(new Rectangle(133, 14, 123, 32));
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
			loadGameField.setSize(new Dimension(246, 26));
			loadGameField.setLocation(new Point(25, 52));
			loadGameField.setText("Choose where you want to save your game.");
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
			searchButton.setBounds(new Rectangle(279, 52, 77, 26));
			searchButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) 
				{
					final JFileChooser fs = new JFileChooser();
					
					int returnVal = fs.showOpenDialog(null);
					
					if(returnVal  == JFileChooser.APPROVE_OPTION)
					{
						gameToLoad = fs.getSelectedFile();
						loadGameField.setText(gameToLoad.getAbsolutePath());
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
	private JButton getSaveGameButton() {
		if (saveGameButton == null) {
			saveGameButton = new JButton();
			saveGameButton.setText("Save");
			saveGameButton.setBounds(new Rectangle(221, 90, 94, 26));
			saveGameButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) 
				{
					try 
					{
						ObjectOutputStream savingGame = new ObjectOutputStream(new FileOutputStream(gameToLoad.getAbsolutePath()));
						
						savingGame.writeObject(gameToBeSaved);
						
						Component frame = null;
						JOptionPane.showMessageDialog(frame, "File was saved successfully!", "Success!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Resources/Sprites/accept.png"));
						
						getSaveGameFrame().setVisible(false);
						/*RacingGUI backToGame = new RacingGUI(gameToBeSaved);
						backToGame.getRacingFrame().setVisible(true);*/
					} 
					catch (FileNotFoundException e1) 
					{
						Component frame = null;
						JOptionPane.showMessageDialog(frame, "File was not found.", "Error!", JOptionPane.ERROR_MESSAGE);
					} 
					catch (IOException e1) 
					{
						Component frame = null;
						JOptionPane.showMessageDialog(frame, "File was not properly saved.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					catch (NullPointerException e1)
					{
						Component frame = null;
						JOptionPane.showMessageDialog(frame, "You must select a file (or folder) to save your game.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
		return saveGameButton;
	}

	/**
	 * This method initializes backToMenuButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBackToGameButton() {
		if (backToGameButton == null) {
			backToGameButton = new JButton();
			backToGameButton.setBounds(new Rectangle(52, 90, 131, 26));
			backToGameButton.setText("Back To Game");
			backToGameButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) 
				{	
					getSaveGameFrame().setVisible(false);
					
					/*
					RacingGUI backToGame = new RacingGUI(logic);
					backToGame.getRacingFrame().setVisible(true);*/
				}
			});
		}
		return backToGameButton;
	}

}
