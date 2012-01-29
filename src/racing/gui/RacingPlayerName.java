package racing.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import racing.logic.Player;
import racing.logic.RacingLogic;

public class RacingPlayerName {

	private JFrame playerNameFrame = null;  //  @jve:decl-index=0:visual-constraint="82,40"
	private JPanel playerNameContentPane = null;
	private JLabel playerNameLabel = null;
	private JTextField playerNameTextField = null;
	private RacingMenuButton insertPlayerButton = null;

	private RacingLogic logic;
	
	RacingPlayerName(String car, String map)
	{
		playerNameFrame = getPlayerNameFrame();
	}
	
	public RacingPlayerName(Player player) {
		
		playerNameFrame = getPlayerNameFrame();
	}

	public RacingPlayerName(RacingLogic log) {
		this.logic = log;
		playerNameFrame = getPlayerNameFrame();
	}

	/**
	 * This method initializes playerNameFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	JFrame getPlayerNameFrame() {
		if (playerNameFrame == null) {
			playerNameFrame = new JFrame();
			playerNameFrame.setSize(new Dimension(200, 200));
			playerNameFrame.setTitle("Player Name");
			playerNameFrame.setContentPane(getPlayerNameContentPane());
			
			playerNameFrame.setLocationRelativeTo(null);
		}
		return playerNameFrame;
	}

	/**
	 * This method initializes playerNameContentPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPlayerNameContentPane() {
		if (playerNameContentPane == null) {
			playerNameLabel = new JLabel("Insert your name.", JLabel.CENTER);
			playerNameLabel.setForeground(Color.WHITE);
			
			playerNameContentPane = new JPanel();
			playerNameContentPane.setLayout(new GridLayout(0,1));
			playerNameContentPane.setBackground(new Color(150,150,255));
			playerNameContentPane.add(playerNameLabel, null);
			playerNameContentPane.add(getPlayerNameTextField(), null);
			playerNameContentPane.add(getInsertPlayerButton(), null);
		}
		return playerNameContentPane;
	}

	/**
	 * This method initializes playerNameTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getPlayerNameTextField() {
		if (playerNameTextField == null) {
			playerNameTextField = new JTextField("");
		}
		return playerNameTextField;
	}

	/**
	 * This method initializes insertPlayerButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private RacingMenuButton getInsertPlayerButton() {
		if (insertPlayerButton == null) {
			insertPlayerButton = new RacingMenuButton();
			insertPlayerButton.setText("Play");
			insertPlayerButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) 
				{
					getPlayerNameFrame().setVisible(false);
					
					String name = playerNameTextField.getText();
					
					logic.getPlayer().setName(name);
					RacingGUI letsPlay = new RacingGUI(logic);
					letsPlay.getRacingFrame().setVisible(true);
				}
			});
		}
		return insertPlayerButton;
	}

}
