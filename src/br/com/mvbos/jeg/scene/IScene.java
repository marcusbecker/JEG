package br.com.mvbos.jeg.scene;

import java.awt.Color;
import java.awt.Graphics2D;

import br.com.mvbos.jeg.element.ElementModel;
import br.com.mvbos.jeg.element.ElementMovableModel;
import br.com.mvbos.jeg.element.IButtonElement;
import br.com.mvbos.jeg.window.IMemory;

/**
 * 
 * @author Marcus Becker
 */
public interface IScene {

	public void update();

	public void changeSceneEvent();

	public void selectElement(ElementModel e);

	public void focusElement(ElementModel e);

	public void releaseElement(ElementModel element, ElementModel anotherElement);

	public void closeWindow();

	/**
	 * Chamado ao iniciar load
	 */
	public boolean startScene();

	public IMemory getElements();

	public void clickElement(int clickCount);

	public void clickElement(Click m);

	public void selectElement(ElementModel[] arr);

	public void mouseMove(ElementModel e, Click m);
	
	public void keyEvent(char keyChar, int keyCode);
	
	public void keyRelease(char keyChar, int keyCode);

	public void setTitle(String title);

	public String getTitle();

	public void releaseElement(ElementModel element);

	public void drawElements(Graphics2D g2d);

	public void clickButton(IButtonElement button);

	@Deprecated
	public void moveElement(ElementMovableModel selectedMovableElement);

	@Deprecated
	public void reflashElementPosition(ElementMovableModel e);

	/**
	 * Chamado ao finalizar load
	 */
	public void startGame();
	
	public Color getBgColor();
	
	
	public void resizeWindow();


}
