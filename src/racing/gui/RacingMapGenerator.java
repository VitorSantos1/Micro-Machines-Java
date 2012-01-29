package racing.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.Vector;
import java.awt.Rectangle;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollBar;
import javax.swing.Scrollable;
import javax.swing.SwingConstants;

import racing.fileIO.FileIO;
import racing.gui.imageHandling.ImageHandler;
import racing.logic.Map;
import racing.logic.RacingLogic;

public class RacingMapGenerator /*implements Scrollable*/ {

	private JFrame mapGeneratorFrame = null;  //  @jve:decl-index=0:visual-constraint="-3,116"
	private RacingMapGeneratorPanel mapGeneratorPane = null;
	private Dimension mapDim = null;
	private JMenuBar generatingMapJMenuBar = null;
	private JMenu mapOptionsMenu = null;
	private JMenuItem saveItem = null;
	private JMenuItem openPreviousMapMenuItem = null;
	private JMenuItem createMapMenuItem = null;
	private JMenuItem backMenuItem = null;
	private JMenu chooseComponentMenu = null;
	private JMenuItem wallMenuItem = null;
	private JMenuItem carMenuItem = null;
	private JMenuItem obstacleMenuItem = null;
	private JMenuItem checkpointMenuItem = null;
	private JMenuItem finishLineMenuItem = null;
	private JMenu operationsMenu = null;
	private JMenuItem undoMenuItem = null;
	private JMenuItem redoMenuItem = null;
	private JMenuItem DelayMenuItem = null;
	private Image imageMap = null;
	
	private int WidthHeight[];
	private int objects[];
	private int mapID;
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();  //  @jve:decl-index=0:
	
	private RacingLogic logic;
	
	public RacingMapGenerator(RacingLogic log, Dimension mD, int mapID)
	{
		mapDim = mD;
	    this.mapID = mapID;
	    
	    objects = new int[6];
	    WidthHeight = new int[2];
	    
	    WidthHeight[0] = 0;
	    WidthHeight[1] = 0;
		
	    this.logic = log;
		mapGeneratorFrame = getMapGeneratorFrame();

	}
	
	/**
	 * This method initializes mapGeneratorFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	JFrame getMapGeneratorFrame() {
		if (mapGeneratorFrame == null) {
			mapGeneratorFrame = new JFrame("Map Generator");
			mapGeneratorFrame.setSize(mapDim);
			mapGeneratorFrame.setJMenuBar(getGeneratingMapJMenuBar());
			mapGeneratorFrame.setContentPane(getMapGeneratorPane());
			
			mapGeneratorFrame.setLocationRelativeTo(null);
			mapGeneratorFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
			mapGeneratorFrame.setUndecorated(true);
		}
		return mapGeneratorFrame;
	}

	/**
	 * This method initializes mapGeneratorPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private RacingMapGeneratorPanel getMapGeneratorPane() {
		if (mapGeneratorPane == null) {

			
			mapGeneratorPane = new RacingMapGeneratorPanel(mapID, mapDim, WidthHeight, objects);
			mapGeneratorPane.setLayout(new BorderLayout());
			
			
	        JScrollBar hbar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 60, 0, (int)mapDim.getWidth()*2);
	        JScrollBar vbar = new JScrollBar(JScrollBar.VERTICAL, 0, 60, 0, (int)mapDim.getHeight()*2);
	        
	        hbar.addAdjustmentListener(new IncWidth());
	        vbar.addAdjustmentListener(new IncHeight());
	        
	        
	        mapGeneratorPane.add(hbar, BorderLayout.SOUTH);
	        mapGeneratorPane.add(vbar, BorderLayout.EAST);
	        
			
		}
		return mapGeneratorPane;
	}

	/**
	 * This method initializes generatingMapJMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getGeneratingMapJMenuBar() {
		if (generatingMapJMenuBar == null) {
			generatingMapJMenuBar = new JMenuBar();
			generatingMapJMenuBar.add(getMapOptionsMenu());
			generatingMapJMenuBar.add(getChooseComponentMenu());
			generatingMapJMenuBar.add(getOperationsMenu());
		}
		return generatingMapJMenuBar;
	}

	/**
	 * This method initializes mapOptionsMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getMapOptionsMenu() {
		if (mapOptionsMenu == null) {
			mapOptionsMenu = new JMenu("Map Options");
			mapOptionsMenu.add(getSaveItem());
			mapOptionsMenu.add(getOpenPreviousMapMenuItem());
			mapOptionsMenu.add(getCreateMapMenuItem());
			mapOptionsMenu.add(getBackMenuItem());
		}
		return mapOptionsMenu;
	}

	/**
	 * This method initializes saveItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getSaveItem() {
		if (saveItem == null) {
			saveItem = new JMenuItem("Save");
		}
		return saveItem;
	}

	/**
	 * This method initializes openPreviousMapMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getOpenPreviousMapMenuItem() {
		if (openPreviousMapMenuItem == null) {
			openPreviousMapMenuItem = new JMenuItem("Open Previous");
			
			openPreviousMapMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					FileIO file = new FileIO(mapID, mapGeneratorPane.tree, mapGeneratorPane.checkpoints);
					file.importMap();
					System.out.println("Imported!");
					mapGeneratorPane.repaint();
					
					logic.getGame().getRace().setRaceMap(new Map(true, mapID));
					/*
					System.out.println("Importing... (show only)");
					file.importMap();*/
				}
			});
		}
		return openPreviousMapMenuItem;
	}

	/**
	 * This method initializes createMapMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getCreateMapMenuItem() {
		if (createMapMenuItem == null) {
			createMapMenuItem = new JMenuItem("Create");
			createMapMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					mapGeneratorPane.addAll();
					
					FileIO file = new FileIO(mapID, mapGeneratorPane.tree, mapGeneratorPane.checkpoints);
					file.exportMap();
					System.out.println("Exported!");
					
					logic.getGame().getRace().setRaceMap(new Map(true, mapID));
				}
			});
		}
		return createMapMenuItem;
	}

	/**
	 * This method initializes backMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getBackMenuItem() {
		if (backMenuItem == null) {
			backMenuItem = new JMenuItem("Back");
			backMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try
					{
						getMapGeneratorFrame().setVisible(false);
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
	 * This method initializes chooseComponentMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getChooseComponentMenu() {
		if (chooseComponentMenu == null) {
			chooseComponentMenu = new JMenu("Choose Component");
			chooseComponentMenu.add(getWallMenuItem());
			chooseComponentMenu.add(getObstacleMenuItem());
			chooseComponentMenu.add(getCheckpointMenuItem());
			chooseComponentMenu.add(getJumpMenuItem());
			chooseComponentMenu.add(getBoundsMenuItem());
			chooseComponentMenu.add(getDelayMenuItem());
		}
		return chooseComponentMenu;
	}

	/**
	 * This method initializes wallMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getWallMenuItem() {
		if (wallMenuItem == null) {
			wallMenuItem = new JMenuItem("Wall");
			wallMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					mapGeneratorPane.setWall(true);
					mapGeneratorPane.setObstacle(false);
					mapGeneratorPane.setCheckpoint(false);
					mapGeneratorPane.setDelayObject(false);
					mapGeneratorPane.setOutOfBounds(false);
					mapGeneratorPane.setJumpingPlatform(false);
				}
			});
		}
		return wallMenuItem;
	}

	/**
	 * This method initializes carMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJumpMenuItem() {
		if (carMenuItem == null) {
			carMenuItem = new JMenuItem("Jumping Platform");
			carMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					mapGeneratorPane.setWall(false);
					mapGeneratorPane.setObstacle(false);
					mapGeneratorPane.setCheckpoint(false);
					mapGeneratorPane.setDelayObject(false);
					mapGeneratorPane.setOutOfBounds(false);
					mapGeneratorPane.setJumpingPlatform(true);
				}
			});
		}
		return carMenuItem;
	}

	/**
	 * This method initializes obstacleMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getObstacleMenuItem() {
		if (obstacleMenuItem == null) {
			obstacleMenuItem = new JMenuItem("Obstacle");
			obstacleMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					mapGeneratorPane.setWall(false);
					mapGeneratorPane.setObstacle(true);
					mapGeneratorPane.setCheckpoint(false);
					mapGeneratorPane.setDelayObject(false);
					mapGeneratorPane.setOutOfBounds(false);
					mapGeneratorPane.setJumpingPlatform(false);
				}
			});
		}
		return obstacleMenuItem;
	}

	/**
	 * This method initializes checkpointMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getCheckpointMenuItem() {
		if (checkpointMenuItem == null) {
			checkpointMenuItem = new JMenuItem("Checkpoint");
			checkpointMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					mapGeneratorPane.setWall(false);
					mapGeneratorPane.setObstacle(false);
					mapGeneratorPane.setCheckpoint(true);
					mapGeneratorPane.setDelayObject(false);
					mapGeneratorPane.setOutOfBounds(false);
					mapGeneratorPane.setJumpingPlatform(false);
				}
			});
		}
		return checkpointMenuItem;
	}

	/**
	 * This method initializes finishLineMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getBoundsMenuItem() {
		if (finishLineMenuItem == null) {
			finishLineMenuItem = new JMenuItem("Out of bounds");
			finishLineMenuItem.addActionListener(new java.awt.event.ActionListener() {   
				public void actionPerformed(java.awt.event.ActionEvent e) { 
					mapGeneratorPane.setWall(false);
					mapGeneratorPane.setObstacle(false);
					mapGeneratorPane.setCheckpoint(false);
					mapGeneratorPane.setDelayObject(false);
					mapGeneratorPane.setOutOfBounds(true);
					mapGeneratorPane.setJumpingPlatform(false);
					}
			});
		}
		return finishLineMenuItem;
	}
	
	private JMenuItem getDelayMenuItem() {
		if (DelayMenuItem == null) {
			DelayMenuItem = new JMenuItem("Delay Object");
			DelayMenuItem.addActionListener(new java.awt.event.ActionListener() {   
				public void actionPerformed(java.awt.event.ActionEvent e) { 
					mapGeneratorPane.setWall(false);
					mapGeneratorPane.setObstacle(false);
					mapGeneratorPane.setCheckpoint(false);
					mapGeneratorPane.setDelayObject(true);
					mapGeneratorPane.setOutOfBounds(false);
					mapGeneratorPane.setJumpingPlatform(false);
					}
			});
		}
		return DelayMenuItem;
	}

	/**
	 * This method initializes operationsMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getOperationsMenu() {
		if (operationsMenu == null) {
			operationsMenu = new JMenu("Operations");
			operationsMenu.add(getUndoMenuItem());
			operationsMenu.add(getRedoMenuItem());
		}
		return operationsMenu;
	}

	/**
	 * This method initializes undoMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getUndoMenuItem() {
		if (undoMenuItem == null) {
			undoMenuItem = new JMenuItem("Undo");
			undoMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					mapGeneratorPane.rectanglesDrawn.remove(mapGeneratorPane.rectanglesDrawn.size() - 1); // TODO exception
					mapGeneratorPane.types.remove(mapGeneratorPane.types.size() - 1); // TODO exception
					mapGeneratorPane.repaint();
				}
			});
		}
		return undoMenuItem;
	}

	/**
	 * This method initializes redoMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getRedoMenuItem() {
		if (redoMenuItem == null) {
			redoMenuItem = new JMenuItem("Redo");
		}
		return redoMenuItem;
	}
	
	public class IncWidth implements AdjustmentListener{
		  public void adjustmentValueChanged(AdjustmentEvent ae){
		  //int value = ae.getValue();
		  WidthHeight[0] = ae.getValue();
		  //System.out.println("Val: " + widthInc);
		  }
		  }
	
	public class IncHeight implements AdjustmentListener{
		  public void adjustmentValueChanged(AdjustmentEvent ae){
		  WidthHeight[1] = ae.getValue();
		  //System.out.println("Val: " + heightInc);
		  }
		  }
}


