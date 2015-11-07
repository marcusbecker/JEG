/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mvbos.jeg.scene;

/**
 *
 * @author mbecker
 */
public class Click {

    private float px;
    private float py;
    private short button;
    private short clickCount;

    private boolean newClick;

    public void setClick(float px, float py, int button, int clickCount) {
        this.px = px;
        this.py = py;
        this.button = (short) button;
        this.clickCount = (short) clickCount;
    }

    public int getButton() {
        return button;
    }

    public void setButton(int button) {
        this.button = (short) button;
    }

    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = (short) clickCount;
    }

    public float getPx() {
        return px;
    }

    public int getX() {
        return (int) px;
    }

    public void setPx(float px) {
        this.px = px;
    }

    public float getPy() {
        return py;
    }

    public int getY() {
        return (int) py;
    }

    public void setPy(float py) {
        this.py = py;
    }

    @Override
    public String toString() {
        return "MouseClick{" + "px=" + px + ", py=" + py + ", button=" + button
                + ", clickCount=" + clickCount + '}';
    }

    public void consume() {
        newClick = false;
    }

    public boolean isNewClick() {
        return newClick;
    }

    public void setNewClick(boolean newClick) {
        this.newClick = newClick;
    }

}
