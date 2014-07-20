package br.com.mvbos.jeg.window;

import br.com.mvbos.jeg.element.ElementMovableModel;
import br.com.mvbos.jeg.scene.IScene;

public interface IWindowGame {

	public void changeScene(IScene scene);

	public void freeze(boolean b, int option);

	public void startConfig();

	public void startGame();

	public void resumeGame();

	public void updateGame();

	public void drawGame();

	public void engineNotification(int id);

	public int getWindowWidth();

	public int getWindowHeight();

	@Deprecated
	public void selectMovableElement(ElementMovableModel e);

	@Deprecated
	public ElementMovableModel getMovableElement();

}
