package br.com.mvbos.jeg.window;

import br.com.mvbos.jeg.engine.Engine;

@Deprecated
/**
 * use Engine.getIWindowGame()
 * @author mbecker
 *
 */
public class GameConfig {

	private static GameConfig config;

	public static void init(IWindowGame wGame, int windowWidth, int windowHeight) {
		config = new GameConfig();
		config.setWindowGame(wGame);
		config.setWindowWidth(windowWidth);
		config.setWindowHeight(windowHeight);
	}

	public static GameConfig getConfig() {
		return config;
	}

	private IWindowGame windowGame;
	private int windowWidth;
	private int windowHeight;

	public GameConfig() {

	}

	public IWindowGame getWindowGame() {
		return windowGame;
	}

	public void setWindowGame(IWindowGame windowGame) {
		this.windowGame = windowGame;
	}

	public int getWindowWidth() {
		return windowWidth;
	}

	public void setWindowWidth(int windowWidth) {
		this.windowWidth = windowWidth;
	}

	public int getWindowHeight() {
		return windowHeight;
	}

	public void setWindowHeight(int windowHeight) {
		this.windowHeight = windowHeight;
	}

}
