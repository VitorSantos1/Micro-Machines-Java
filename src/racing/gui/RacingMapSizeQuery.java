package racing.gui;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

import racing.logic.RacingLogic;

public class RacingMapSizeQuery {

	private JFrame mapQueryFrame = null;  //  @jve:decl-index=0:visual-constraint="200,73"
	private JPanel mapQueryPane = null;
	private JPanel buttonsPanel = null;
	private JLabel mapQueryLabel = null;
	private JLabel map1Label = null;
	private JLabel map2Label = null;
	private RacingMenuButton backButton = null;
	private RacingMenuButton okButton = null;
	private JLabel map3Label = null;
	private JRadioButton map1RadioButton = null;
	private JRadioButton map2RadioButton = null;
	private JRadioButton map3RadioButton = null;
	
	private String carName = null;
	private String mapName = null;
	
	private RacingLogic logic;
	
	public RacingMapSizeQuery(RacingLogic log)
	{
		this.logic = log;
		mapQueryFrame = getMapQueryFrame();
	}
	
	/**
	 * This method initializes mapQueryFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	JFrame getMapQueryFrame() {
		if (mapQueryFrame == null) {
			mapQueryFrame = new JFrame("Map Size");
			mapQueryFrame.setSize(new Dimension(300, 300));
			mapQueryFrame.setContentPane(getMapQueryPane());
			
			mapQueryFrame.setLocationRelativeTo(null);
		}
		return mapQueryFrame;
	}

	/**
	 * This method initializes mapQueryPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getMapQueryPane() {
		if (mapQueryPane == null) {
			BorderLayout borderLayout = new BorderLayout();
			borderLayout.setVgap(10);
			map1Label = new JLabel();
			map1Label.setText("Map 1");
			map1Label.setForeground(Color.WHITE);
			
			mapQueryLabel = new JLabel("Please choose a image of a map.", JLabel.CENTER);
			mapQueryLabel.setForeground(Color.WHITE);
			
			Font originalFont = mapQueryLabel.getFont();
			mapQueryLabel.setFont(new Font(originalFont.getFontName(), originalFont.getStyle(), 13));
			
			mapQueryPane = new JPanel();
			mapQueryPane.setLayout(borderLayout);
			mapQueryPane.add(getButtonsPanel(), BorderLayout.CENTER);
			mapQueryPane.setBackground(new Color(150,150,255));
			mapQueryPane.add(mapQueryLabel, java.awt.BorderLayout.NORTH);
		}
		return mapQueryPane;
	}

	/**
	 * This method initializes buttonsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getButtonsPanel() {
		if (buttonsPanel == null) {
			map3Label = new JLabel();
			map3Label.setText("Map 3");
			map3Label.setForeground(Color.WHITE);
			
			GridLayout gridLayout = new GridLayout(0, 2);
			gridLayout.setHgap(13);
			gridLayout.setVgap(15);
			
			map2Label = new JLabel("Height");
			map2Label.setForeground(Color.WHITE);
			map2Label.setText("Map 2");
			
			buttonsPanel = new JPanel();
			buttonsPanel.setLayout(gridLayout);
			buttonsPanel.setBackground(new Color(150,150,255));
			buttonsPanel.add(map1Label, null);
			buttonsPanel.add(getMap1RadioButton(), null);
			buttonsPanel.add(map2Label, null);
			buttonsPanel.add(getMap2RadioButton(), null);
			buttonsPanel.add(map3Label, null);
			buttonsPanel.add(getMap3RadioButton(), null);
			buttonsPanel.add(getBackButton(), null);
			buttonsPanel.add(getOkButton(), null);
			
		    ButtonGroup group = new ButtonGroup();
		    group.add(map1RadioButton);
		    group.add(map2RadioButton);
		    group.add(map3RadioButton);
		}
		return buttonsPanel;
	}

	/**
	 * This method initializes backButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private RacingMenuButton getBackButton() {
		if (backButton == null) {
			backButton = new RacingMenuButton();
			backButton.setText("Back to Selector");
			backButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try 
					{
						getMapQueryFrame().setVisible(false);
						RacingMapSelection backMenu = new RacingMapSelection(logic);
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
		return backButton;
	}

	/**
	 * This method initializes okButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private RacingMenuButton getOkButton() {
		if (okButton == null) {
			okButton = new RacingMenuButton();
			okButton.setText("Proceed");
			okButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try
					{
						Image newMap = null;
						Dimension dm = new Dimension();
						int id = 0;
						
						if(map1RadioButton.isSelected()) {
							dm.setSize(1440, 2880);
							id = 0;
						}
						else if(map2RadioButton.isSelected()) {
							id = 1;
							dm.setSize(1536, 1056);
						}
						else if(map2RadioButton.isSelected()) {
							id = 2;
							newMap = ImageIO.read(new File("Micro Machines - Foamy Fjords.png"));}
						
						
						getMapQueryFrame().setVisible(false);
						
						RacingMapGenerator mapGen = new RacingMapGenerator(logic, dm, id);
						mapGen.getMapGeneratorFrame().setVisible(true);
					}
					catch(NumberFormatException e1)
					{
						Component frame = null;
						JOptionPane.showMessageDialog(frame, "Dimension not valid.", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					catch(IOException e1)
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
	 * This method initializes map1RadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getMap1RadioButton() {
		if (map1RadioButton == null) {
			map1RadioButton = new JRadioButton();
			map1RadioButton.setBackground(new Color(150,150,255));
		}
		return map1RadioButton;
	}

	/**
	 * This method initializes map2RadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getMap2RadioButton() {
		if (map2RadioButton == null) {
			map2RadioButton = new JRadioButton();
			map2RadioButton.setBackground(new Color(150,150,255));
		}
		return map2RadioButton;
	}

	/**
	 * This method initializes map3RadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getMap3RadioButton() {
		if (map3RadioButton == null) {
			map3RadioButton = new JRadioButton();
			map3RadioButton.setBackground(new Color(150,150,255));
		}
		return map3RadioButton;
	}

}
