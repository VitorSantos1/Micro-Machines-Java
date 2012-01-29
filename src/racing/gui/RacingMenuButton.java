package racing.gui;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class RacingMenuButton extends JButton {

	public RacingMenuButton() {
		setSize(new Dimension(186, 24));
		
		addMouseListener(new java.awt.event.MouseAdapter() {   
			public void mouseClicked(java.awt.event.MouseEvent e) {    
				setBackground(new Color(100,100,100));
				setBackground(new Color(255,255,255));
			}   
			public void mouseExited(java.awt.event.MouseEvent e) {    
				setBackground(null);
			}
			public void mouseEntered(java.awt.event.MouseEvent e) {
				setBackground(new Color(255,255,255));
			}
		});
	}
	
}
