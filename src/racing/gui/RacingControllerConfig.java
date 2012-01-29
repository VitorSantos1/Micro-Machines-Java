package racing.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class RacingControllerConfig {

	private JFrame controllerConfigFrame = null;  //  @jve:decl-index=0:visual-constraint="158,78"
	private JPanel controllerConfigPane = null;
	private JButton backToOptionsButton = null;
	private JButton okButton = null;
	private JLabel accelerateLabel = null;
	private JTextField accelerateTextField = null;
	private JLabel brakeLabel = null;
	private JTextField brakeTextField = null;
	private JLabel turnRightLabel = null;
	private JTextField turnRightTextField = null;
	private JLabel turnLeftLabel = null;
	private JTextField turnLeftTextField = null;

	//TODO: Fazer depois de haver uma c�pia dos movimentos no in�cio do jogo.
	Vector<String> keyMovements = new Vector<String>(0);  //  @jve:decl-index=0:
	
	public RacingControllerConfig()
	{
		controllerConfigFrame = getControllerConfigFrame();
	}
	
	/**
	 * This method initializes controllerConfigFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	JFrame getControllerConfigFrame() {
		if (controllerConfigFrame == null) {
			controllerConfigFrame = new JFrame("Set Controls");
			controllerConfigFrame.setSize(new Dimension(400, 300));
			controllerConfigFrame.setContentPane(getControllerConfigPane());
			controllerConfigFrame.setLocationRelativeTo(null);
		}
		return controllerConfigFrame;
	}

	/**
	 * This method initializes controllerConfigPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getControllerConfigPane() {
		if (controllerConfigPane == null) {
			GridLayout gridLayout = new GridLayout(0, 2);
			gridLayout.setHgap(12);
			gridLayout.setVgap(12);
			turnLeftLabel = new JLabel("Turn Left", JLabel.CENTER);
			turnLeftLabel.setForeground(Color.WHITE);
			turnRightLabel = new JLabel("Turn Right", JLabel.CENTER);
			turnRightLabel.setForeground(Color.WHITE);
			brakeLabel = new JLabel("Brake", JLabel.CENTER);
			brakeLabel.setForeground(Color.WHITE);
			accelerateLabel = new JLabel("Accelerate", JLabel.CENTER);
			accelerateLabel.setForeground(Color.WHITE);
			controllerConfigPane = new JPanel();
			controllerConfigPane.setLayout(gridLayout);
			controllerConfigPane.setBackground(new Color(150,150,255));
			controllerConfigPane.add(accelerateLabel, null);
			controllerConfigPane.add(getAccelerateTextField(), null);
			controllerConfigPane.add(brakeLabel, null);
			controllerConfigPane.add(getBrakeTextField(), null);
			controllerConfigPane.add(turnRightLabel, null);
			controllerConfigPane.add(getTurnRightTextField(), null);
			controllerConfigPane.add(turnLeftLabel, null);
			controllerConfigPane.add(getTurnLeftTextField(), null);
			controllerConfigPane.add(getBackToOptionsButton(), null);
			controllerConfigPane.add(getOkButton(), null);
		}
		return controllerConfigPane;
	}

	/**
	 * This method initializes backToOptionsButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBackToOptionsButton() {
		if (backToOptionsButton == null) {
			backToOptionsButton = new JButton("Back To Options");
			backToOptionsButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) 
				{
					try {
						getControllerConfigFrame().setVisible(false);
						RacingOptions backMenu = new RacingOptions();
						backMenu.getMenuFrame().setVisible(true);
					} catch (IOException e1) {
						System.out.println("Missing image...");
						System.exit(0);
					}
				}
			});
		}
		return backToOptionsButton;
	}

	/**
	 * This method initializes okButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton("OK");
			okButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) 
				{
					try
					{
						boolean stopConfig = false;
						
						while(!stopConfig)
						{
							keyMovements.clear();
								
							keyMovements.add(getAccelerateTextField().getText());
							keyMovements.add(getBrakeTextField().getText());
							keyMovements.add(getTurnRightTextField().getText());
							keyMovements.add(getTurnLeftTextField().getText());
								
							boolean validVectorOfMovements = true;
							boolean properVectorOfMovements = true;
							
							for(int i = 0; i < keyMovements.size() && validVectorOfMovements && properVectorOfMovements; i++)
							{
								if(keyMovements.get(i).length() != 1)
									validVectorOfMovements = false;
								
								if(keyMovements.get(i).equals(""))
									properVectorOfMovements = false;
							}
								
							if(!validVectorOfMovements)
							{
								Component frame = null;
								JOptionPane.showMessageDialog(frame, "One or more keys typed are not valid.", "Error!", JOptionPane.ERROR_MESSAGE);
								break;
							}
							else if(!properVectorOfMovements)
							{
								Component frame = null;
								JOptionPane.showMessageDialog(frame, "Some fields are yet to be filled.", "Success!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Resources/Sprites/accept.png"));
								break;
							}
							else if(validVectorOfMovements && properVectorOfMovements)
							{
								Component frame = null;
								JOptionPane.showMessageDialog(frame, "Controls successfully edited!", "Success!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Resources/Sprites/accept.png"));
											
								getControllerConfigFrame().setVisible(false);
								RacingOptions backMenu = new RacingOptions();
								backMenu.getMenuFrame().setVisible(true);
									
								stopConfig = true;
							}
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
		return okButton;
	}

	/**
	 * This method initializes accelerateTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getAccelerateTextField() {
		if (accelerateTextField == null) {
			accelerateTextField = new JTextField("");
		}
		return accelerateTextField;
	}

	/**
	 * This method initializes brakeTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getBrakeTextField() {
		if (brakeTextField == null) {
			brakeTextField = new JTextField("");
		}
		return brakeTextField;
	}

	/**
	 * This method initializes turnRightTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTurnRightTextField() {
		if (turnRightTextField == null) {
			turnRightTextField = new JTextField("");
		}
		return turnRightTextField;
	}

	/**
	 * This method initializes turnLeftTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTurnLeftTextField() {
		if (turnLeftTextField == null) {
			turnLeftTextField = new JTextField("");
		}
		return turnLeftTextField;
	}

}
