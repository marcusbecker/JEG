package br.com.mvbos.jeg.scene;

import java.awt.Graphics2D;

import br.com.mvbos.jeg.window.IMemory;

/**
 *
 * @author Marcus Becker
 */
public interface IScene {

    public boolean startScene();

    public void updateScene();

    public void drawScene(Graphics2D g);

    public IMemory[] getElements();

    public void setElements(IMemory... memory);

    public String getTitle();

    public void changeSceneEvent();

    public void focusWindow();

    public void lostFocusWindow();

    public void closeWindow();

    public void resizeWindow(int width, int height);

}
