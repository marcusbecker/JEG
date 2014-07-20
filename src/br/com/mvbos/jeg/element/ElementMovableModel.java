package br.com.mvbos.jeg.element;

/**
 *
 * @author MBecker
 */
public class ElementMovableModel extends ElementModel {

    private int initPositionX;
    private int initPositionY;
    private int lastPositionX;
    private int lastPositionY;

    public ElementMovableModel(int width, int height, String name) {
        super(width, height, name);
    }

    public int getInitPositionX() {
        return initPositionX;
    }

    public void setInitPositionX(int initPositionX) {
        this.initPositionX = initPositionX;
    }

    public int getInitPositionY() {
        return initPositionY;
    }

    public void setInitPositionY(int initPositionY) {
        this.initPositionY = initPositionY;
    }

    public int getLastPositionX() {
        return lastPositionX;
    }

    public void setLastPositionX(int lastPositionX) {
        this.lastPositionX = lastPositionX;
    }

    public int getLastPositionY() {
        return lastPositionY;
    }

    public void setLastPositionY(int lastPositionY) {
        this.lastPositionY = lastPositionY;
    }
}
