package br.com.mvbos.jeg.engine;

/**
 * Control the ClickMap events
 * 
 * @author mbecker
 * 
 */
public class JoyPad {

	public boolean[] pressed = new boolean[KeysMap.values().length];

	// cima, baixo, esq, dir, Q, E
	private int[] keysCode = {};
	private char[] keysChar = {};

	public JoyPad() {

	}

	public JoyPad(int[] keysCode, char[] keysChar) {
		this.keysChar = keysChar;
		this.keysCode = keysCode;
	}

	public boolean is(KeysMap k) {
		return pressed[k.ordinal()];
	}

	public void consume(KeysMap k) {
		if (k == null)
			return;

		pressed[k.ordinal()] = false;

	}

	public void press(KeysMap direction) {

		if (direction == null)
			return;

		switch (direction) {
		case UP:
			pressed[KeysMap.UP.ordinal()] = true;
			pressed[KeysMap.DOWN.ordinal()] = false;
			break;
		case DOWN:
			pressed[KeysMap.DOWN.ordinal()] = true;
			pressed[KeysMap.UP.ordinal()] = false;

			break;
		case LEFT:
			pressed[KeysMap.LEFT.ordinal()] = true;
			pressed[KeysMap.RIGHT.ordinal()] = false;
			break;
		case RIGHT:
			pressed[KeysMap.RIGHT.ordinal()] = true;
			pressed[KeysMap.LEFT.ordinal()] = false;
			break;
		case B0:
			pressed[KeysMap.B0.ordinal()] = true;
			break;
		case B1:
			pressed[KeysMap.B1.ordinal()] = true;
		default:
			pressed[direction.ordinal()] = true;
		}
	}

	public void release(KeysMap direction) {
		if (direction == null)
			return;

		pressed[direction.ordinal()] = false;

	}

	public void releaseAll() {
		for (KeysMap k : KeysMap.values()) {
			pressed[k.ordinal()] = false;
		}
	}

	public void consumeAll() {
		for (KeysMap k : KeysMap.values()) {
			pressed[k.ordinal()] = false;
		}
	}

	public int[] getKeysCode() {
		return keysCode;
	}

	public void setKeysCode(int[] keysCode) {
		this.keysCode = keysCode;
	}

	public char[] getKeysChar() {
		return keysChar;
	}

	public void setKeysChar(char[] keysChar) {
		this.keysChar = keysChar;
	}

	/**
	 * Return true if the key was pressed and is the first time that read this
	 * value
	 * 
	 * @param k
	 * @return
	 */
	public boolean first(KeysMap k) {
		if (pressed[k.ordinal()]) {
			pressed[k.ordinal()] = false;
			return true;
		}

		return false;
	}

	public boolean isKey(char keyChar, int keyCode, int idKeyMap) {
		if (idKeyMap < 0)
			return false;

		if (idKeyMap < keysChar.length && Character.toLowerCase(keyChar) == Character.toLowerCase(keysChar[idKeyMap]))
			return true;

		if (idKeyMap < keysCode.length && keyCode == keysCode[idKeyMap])
			return true;

		return false;
	}
}
