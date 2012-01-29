package racing.gui;

import racing.logic.GameSession;
import racing.logic.RacingLogic;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JMenuBar;

/**
 * @author Vitor Santos
 * @version 1.0
 * @created 16-Mai-2011 10:42:31
 */
public class RacingGUI 
{
	public RacingLogic m_RacingLogic;
	public RacingMenu m_RacingMenu;
	private JFrame racingFrame = null;  //  @jve:decl-index=0:visual-constraint="39,21"
	private RacingPanel racingPane = null;
	private JMenuBar racingJMenuBar = null;
	private String playersName = null;
	
	private RacingLogic logic; 
	private GameSession gameToBePlayed;

	private String carName = null;
	private String mapName = null;
	private JMenuItem backMenuItem;
	
	public RacingGUI(RacingLogic rlogic){

		logic = rlogic;
		gameToBePlayed = logic.getGame();
		racingFrame = getRacingFrame();
	}
	
	public void finalize() throws Throwable {

	}

	/**
	 * This method initializes racingFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	JFrame getRacingFrame() {
		if (racingFrame == null) {
			racingFrame = new JFrame();
			racingFrame.setSize(new Dimension(800, 600));
			racingFrame.setTitle("Playing Game");
			racingFrame.setJMenuBar(getRacingJMenuBar());
			racingFrame.setContentPane(getRacingPane());
			racingFrame.setJMenuBar(racingJMenuBar);
			
			racingFrame.setResizable(false);
			racingFrame.setLocationRelativeTo(null);
			
			racingFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
			racingFrame.setUndecorated(true);  
			
			racingFrame.addWindowListener(new WindowAdapter() {
			    public void windowClosing(WindowEvent we) {
			    	Integer option = JOptionPane.showConfirmDialog(null, "Do you really want to exit?", "Confirm exit", JOptionPane.YES_NO_OPTION);
					if(JOptionPane.OK_OPTION == option)
					{
						System.exit(0);
					}
			    }
			});
			
			
		}
		return racingFrame;
	}
	
	private JMenuItem getBackMenuItem() {
		if (backMenuItem == null) {
			backMenuItem = new JMenuItem("Back");
			backMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try
					{
						getRacingFrame().setVisible(false);
						RacingNewGame backMenu = new RacingNewGame(logic);
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
		return backMenuItem;
	}

	/**
	 * This method initializes racingPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public RacingPanel getRacingPane() {
		if (racingPane == null) {
			racingPane = new RacingPanel(logic);
			racingPane.setLayout(new BorderLayout());
			racingPane.addKeyListener(keyboardListener);
		}
		return racingPane;
	}

	/**
	 * This method initializes racingJMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getRacingJMenuBar() {
		if (racingJMenuBar == null) {
			racingJMenuBar = new JMenuBar();
			
			JMenu racingChoices = new JMenu("Gaming Options");
			racingJMenuBar.add(racingChoices);
			
			JMenuItem pauseGame = new JMenuItem("Pause");
			racingChoices.add(pauseGame);
			
			JMenuItem gameOptions = new JMenuItem("Options");
			gameOptions.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						getRacingFrame().setVisible(false);
						RacingOptions opt = new RacingOptions(gameToBePlayed);
						opt.getMenuFrame().setVisible(true);
					} catch (IOException e1) {
						System.out.println("Missing image...");
						System.exit(0);
					}
				}
			});
			racingChoices.add(gameOptions);
			
			JMenuItem saveGame = new JMenuItem("Save");
			saveGame.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					racingPane.timer.stop();
					
					getRacingFrame().setVisible(false);
					RacingSaveGame saveGame = new RacingSaveGame(logic.getGame());
					saveGame.getSaveGameFrame().setVisible(true);
				}
			});
			racingChoices.add(saveGame);
			
			backMenuItem = new JMenuItem("Back");
			backMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try
					{
						getRacingFrame().setVisible(false);
						RacingNewGame backMenu = new RacingNewGame(logic);
						backMenu.getMenuFrame().setVisible(true);
					} 
					catch (IOException e1)
					{
						System.out.println("Missing image...");
						System.exit(0);
					}
				}
			});
			racingChoices.add(backMenuItem);
			
		}
		return racingJMenuBar;
	}
	
	KeyListener keyboardListener = new KeyListener() {

		@Override
		public void keyPressed(KeyEvent arg0) {
			if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {
				getRacingFrame().setVisible(false);
				RacingNewGame backMenu = null;
				try {
					backMenu = new RacingNewGame(logic);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					backMenu.getMenuFrame().setVisible(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			
		}};

	public void setRacingPane(RacingPanel racingPane) {
		this.racingPane = racingPane;
	}
	
}