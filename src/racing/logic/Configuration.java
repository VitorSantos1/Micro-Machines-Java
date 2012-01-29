package racing.logic;

import java.awt.event.KeyEvent;
import java.io.Serializable;

/**
 * Represents a keyboard layout for use in-game, which can be
 * used in another game session.
 *
 */
public class Configuration implements Serializable {

	private static final long serialVersionUID = -4137636088483935763L;
	
	private int keyAccelerate;
	private int keyBrake;
	private int keyUp;
	private int keyDown;
	private int keyLeft;
	private int keyMenu;
	private int keyRight;

	public Configuration(int keyLayout){
		
		if (keyLayout == 0) {
			keyUp = KeyEvent.VK_UP;
			keyDown = KeyEvent.VK_DOWN;
			keyLeft = KeyEvent.VK_LEFT;
			keyRight = KeyEvent.VK_RIGHT;
			keyBrake = KeyEvent.VK_X;
		}
		else if (keyLayout == 1) {
			keyUp = KeyEvent.VK_W;
			keyDown = KeyEvent.VK_S;
			keyLeft = KeyEvent.VK_A;
			keyRight = KeyEvent.VK_R;
		}
		else if (keyLayout == 2) {
			keyUp = KeyEvent.VK_A;
			keyDown = KeyEvent.VK_S;
			keyLeft = KeyEvent.VK_LEFT;
			keyRight = KeyEvent.VK_RIGHT;
		}
	}

	public int getKeyAccelerate() {
		return keyAccelerate;
	}

	public int getKeyBrake() {
		return keyBrake;
	}

	public int getKeyDown() {
		return keyDown;
	}

	public int getKeyLeft() {
		return keyLeft;
	}

	public int getKeyMenu() {
		return keyMenu;
	}

	public int getKeyRight() {
		return keyRight;
	}
	
	public int getKeyUp() {
		return keyUp;
	}

	public void finalize() throws Throwable {

	}

	public int getKeyHBrake() {
		return keyBrake;
	}

}