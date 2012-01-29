package racing.gui;
import java.awt.BasicStroke;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.swing.JPanel;
import javax.swing.Timer;

import racing.gui.imageHandling.ImageHandler;
import racing.gui.imageHandling.ImageUtils;
import racing.logic.Car;
import racing.logic.Drifter;
import racing.logic.GameEngine;
import racing.logic.GameSession;
import racing.logic.HighScores;
import racing.logic.Line;
import racing.logic.Map;
import racing.logic.Point;
import racing.logic.Race;
import racing.logic.RacingLogic;
import racing.logic.Runner;
import racing.logic.Solid;
import racing.logic.Speeder;
import racing.logic.keyNotation;
import racing.physics.Collision;
import racing.sound.Sound;
import static racing.logic.keyNotation.*;
import java.io.*;
import sun.audio.*;

public class RacingPanel extends JPanel implements ActionListener {

	public RacingGUI m_RacingGUI;
	private Car carRef;
	private RacingLogic logicRef;
	ImageHandler imgHandler = null;

	boolean startKey = false;
	int keyCode = -1;
	int noKeyCounter = 0;
	
	// Times
	long timeElapsed = 0;
	
	public static Timer timer;
	int delay = 25; // ms 100
	
	// Counters
	int counter = 0;
	int counterDir = 0;
	int counterAcc = 0;
	int throttleCap = 3;

	int animations = 0;
	
	/**
	 * Index:
	 * 0 - UP
	 * 1 - Down
	 * 2 - Left
	 * 3 - Right
	 */
	int[] keysPressed;
	
	int ACC;
	int DEC;
	int LEFT;
	int RIGHT;
	int HBRAKE;
	
	// Images
	private Image mapBackground;
    private Image carSprite;
    
	int width;
	int height; 
    
	Line line;
	private Race race;
	private GameEngine engine;
	boolean keyActivated;
	
    KeyListener listener = new KeyListener() {
	      
		public void keyPressed(KeyEvent e) {
			startKey = true;
			 int constKey = e.getKeyCode();
			 if (keyActivated) {
	    	  if (ACC == constKey)
	    		  keysPressed[keyNotation.ACCELERATE] = 1;
	    	  else if (DEC == constKey)
	    		  keysPressed[keyNotation.DECELERATE] = 1;
	    	  else if (LEFT == constKey)
	    		  keysPressed[keyNotation.LEFT] = 1;
	    	  else if (RIGHT == constKey)
	    		  keysPressed[keyNotation.RIGHT] = 1;
	    	  else if (HBRAKE == constKey)
	    		  keysPressed[keyNotation.HANDBRAKE] = 1;
		        }}
	      

	      public void keyReleased(KeyEvent e) {
	    	  int constKey = e.getKeyCode();
	    	  if (keyActivated) {
	    	  if (ACC == constKey)
	    		  keysPressed[ACCELERATE] = 0;
	    	  else if (DEC == constKey)
	    		  keysPressed[DECELERATE] = 0;
	    	  else if (LEFT == constKey)
	    		  keysPressed[keyNotation.LEFT] = 0;
	    	  else if (RIGHT == constKey)
	    		  keysPressed[keyNotation.RIGHT] = 0;
	    	  else if (HBRAKE == constKey)
	    		  keysPressed[keyNotation.HANDBRAKE] = 0;
		        }}
	      
	      public void keyTyped(KeyEvent e) {
	      }
	};
	
	private Image carSpriteAir;

	
	@SuppressWarnings("restriction")
	public RacingPanel(RacingLogic logic){

		
		/** Assign references */
		engine = new GameEngine();
		logicRef = logic;
		race = logic.getGame().getRace();
		carRef = logic.getPlayer().getM_Car();
		imgHandler = new ImageHandler(true);
		keyActivated = true;
		
		/** Load Sprites */
		mapBackground = imgHandler.getBackground(this, race.getRaceMap().getFileName(), race.getRaceMap().getWidth(), race.getRaceMap().getHeight()); // Select map according to the selected in logic
		
		System.out.println("CAR TYPE: " + carRef.getID());
		carSprite = imgHandler.getCar(carRef.getID(), 0);
		carSpriteAir = imgHandler.getCar(carRef.getID(), 1); 
		
		addKeyListener(listener);
		
		/** Key Management */
		keysPressed = new int[5];  
		
		ACC = logic.getGame().getConfig().getKeyUp();
		DEC = logic.getGame().getConfig().getKeyDown();
		LEFT = logic.getGame().getConfig().getKeyLeft();
		RIGHT = logic.getGame().getConfig().getKeyRight();
		HBRAKE = logic.getGame().getConfig().getKeyHBrake();
		
		setFocusable(true);

		timer = new Timer(delay, this);
		timer.setInitialDelay(100);
		timeElapsed = System.currentTimeMillis();
		timer.start(); 
	}

	
	public void pauseGame(boolean pause) {
		if (pause)
			timer.stop();
		else
			timer.start();
	}

	public void finalize() throws Throwable {

	}
	
	/**
	 * Action that timer executes. Executes the core method -> gameEngine
	 */
	public void actionPerformed(ActionEvent e) {
		
		if (startKey) {
			long curTime = System.currentTimeMillis();
			engine.gameEngine(curTime, keysPressed, race); 
			
		}
		repaint();
    }
	
	boolean once = false;
	
public void paintComponent(Graphics g) {
    
	if (race.getEndRace()) {
		keyActivated = false;

		if (race.getRaceCompleted()) {

		}

		if (!once) {
			HighScores teste = new HighScores(race.getRaceMap().getFileName());

			teste.writeHighScore(race.getM_Player().getName(), race.getM_Player().getPoints(), race.getFinishTime());

			System.out.println("Cur lap:" + race.getCurLap());
			System.out.println("Total laps:" + race.getMaxLaps());
			System.out.println("Race ended!");

			once = true;
		}

	}

	super.paintComponent(g);

	width  = this.getWidth()/2;
	height = this.getHeight()/2;

	g.drawImage(mapBackground, (-1)*((int)carRef.getCoordX()) + width, (-1)*((int)carRef.getCoordY()) + height, this);

	if (carRef.getOnAir())
		g.drawImage(ImageUtils.rotateImage(carSpriteAir, carRef.getYaw()), width, height, this);
	else
		g.drawImage(ImageUtils.rotateImage(carSprite, carRef.getYaw()), width, height, this);


	if (keyActivated)
		if (startKey) { 
			// Only updates animations if game is still running and has started
			imgHandler.showLapTime(System.currentTimeMillis() - race.getLapStart(), g, this);
		}
		else
			imgHandler.showLapTime(race.getFinishTime(), g, this);
	}


	public int[] getKeysPressed() {
		return keysPressed;
	}

	public void setKeysPressed(int[] keysPressed) {
		this.keysPressed = keysPressed;
	}
}
	
