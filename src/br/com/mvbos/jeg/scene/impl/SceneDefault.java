package br.com.mvbos.jeg.scene.impl;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import br.com.mvbos.jeg.element.ElementModel;
import br.com.mvbos.jeg.element.ElementMovableModel;
import br.com.mvbos.jeg.element.IButtonElement;
import br.com.mvbos.jeg.engine.Engine;
import br.com.mvbos.jeg.scene.Click;
import br.com.mvbos.jeg.scene.IScene;
import br.com.mvbos.jeg.window.IMemory;
import br.com.mvbos.jeg.window.impl.MemoryImpl;

public class SceneDefault implements IScene {

	protected IMemory memo;
	private Random random = new Random();

	@Override
	public void update() {
		if (memo == null) {
			Engine.log("Update Error: Scene don't started.");
			return;
		}

		for (int i = 0; i < memo.getElementCount(); i++) {
			int x = random.nextInt(Engine.getIWindowGame().getWindowWidth());
			int y = random.nextInt(Engine.getIWindowGame().getWindowHeight());

			memo.getByElement(i).update();
			memo.getByElement(i).setPxy(x, y);
		}

	}

	@Override
	public void changeSceneEvent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void selectElement(ElementModel e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusElement(ElementModel e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void releaseElement(ElementModel element, ElementModel anotherElement) {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeWindow() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean startScene() {
		memo = new MemoryImpl(30);

		for (int i = 0; i < memo.getCapacity(); i++) {
			int x = random.nextInt(Engine.getIWindowGame().getWindowWidth());
			int y = random.nextInt(Engine.getIWindowGame().getWindowHeight());
			ElementModel e = new ElementModel(x, y, 10, 11, "" + i);

			memo.registerElement(e);
			e.loadElement();
		}

		return true;
	}

	@Override
	public void startGame() {
		// TODO Auto-generated method stub
	}

	@Override
	public IMemory getElements() {
		return memo;
	}

	@Override
	public void clickElement(int clickCount) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clickElement(Click m) {
		clickElement(0);
	}

	@Override
	public void selectElement(ElementModel[] arr) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMove(ElementModel e, Click m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTitle(String title) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void releaseElement(ElementModel element) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawElements(Graphics2D g2d) {
		if (memo == null) {
			Engine.log("Draw Error: Scene don't started.");
			return;
		}

		for (int i = 0; i < memo.getElementCount(); i++) {
			if (memo.getByElement(i).isVisible()) {
				memo.getByElement(i).drawMe(g2d);
			}
		}
	}

	@Override
	public void clickButton(IButtonElement button) {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveElement(ElementMovableModel selectedMovableElement) {
		// TODO Auto-generated method stub

	}

	@Override
	public void reflashElementPosition(ElementMovableModel e) {
		// TODO Auto-generated method stub

	}

	@Override
	public Color getBgColor() {
		return Color.BLACK;
	}

	@Override
	public void keyEvent(char keyChar, int keyCode) {
		for (int i = 0; i < memo.getElementCount(); i++) {
			if (memo.getByElement(i).getDefaultColor() == Color.BLUE) {
				memo.getByElement(i).setDefaultColor(Color.GREEN);
			} else {
				memo.getByElement(i).setDefaultColor(Color.BLUE);
			}
		}
	}

	@Override
	public void keyRelease(char keyChar, int keyCode) {
		for (int i = 0; i < memo.getElementCount(); i++) {
			memo.getByElement(i).setDefaultColor(Color.YELLOW);

		}
	}

	@Override
	public void resizeWindow() {
	}

}
