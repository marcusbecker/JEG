package br.com.mvbos.jeg.scene.impl;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import br.com.mvbos.jeg.element.ElementModel;
import br.com.mvbos.jeg.engine.Engine;
import br.com.mvbos.jeg.scene.IScene;
import br.com.mvbos.jeg.window.IMemory;
import br.com.mvbos.jeg.window.impl.MemoryImpl;

public class SceneDefault implements IScene {

    protected IMemory memo;
    private final Random random = new Random();

    @Override
    public void updateScene() {
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
    public IMemory[] getElements() {
        return new IMemory[]{memo};
    }

    @Override
    public void drawScene(Graphics2D g2d) {
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

    public void keyEvent(char keyChar, int keyCode) {
        for (int i = 0; i < memo.getElementCount(); i++) {
            if (memo.getByElement(i).getColor() == Color.BLUE) {
                memo.getByElement(i).setColor(Color.GREEN);
            } else {
                memo.getByElement(i).setColor(Color.BLUE);
            }
        }
    }

    public void keyRelease(char keyChar, int keyCode) {
        for (int i = 0; i < memo.getElementCount(); i++) {
            memo.getByElement(i).setColor(Color.YELLOW);

        }
    }

    @Override
    public void resizeWindow(int w, int h) {
    }

    @Override
    public void setElements(IMemory[] memory) {
    }

    @Override
    public String getTitle() {
        return "Default Scene";
    }

    @Override
    public void focusWindow() {
    }

    @Override
    public void lostFocusWindow() {

    }

}
