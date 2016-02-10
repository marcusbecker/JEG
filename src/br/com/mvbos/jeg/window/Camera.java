package br.com.mvbos.jeg.window;

import java.awt.Graphics2D;

import br.com.mvbos.jeg.element.ElementModel;
import br.com.mvbos.jeg.engine.Engine;
import java.awt.geom.AffineTransform;

/**
 * @author mbecker
 *
 */
public class Camera {

    private float cpx;
    private float cpy;

    private int w = 4000;
    private int h = 3970;

    private short scrw = 1024;
    private short scrh = 768;

    private short xOffset;
    private short yOffset;

    private boolean allowOffset;

    private boolean active = true;

    private static Camera c;

    public static Camera c() {
        if (c == null) {
            c = new Camera();
        }

        return c;
    }

    public static Camera createNew() {
        return new Camera();
    }

    /**
     * The sceneWidth and sceneHeight are the size of the scene and must be
     * larger than the size of screen (screenWidth and screeHeight), to make
     * sense to use the camera.
     *
     * @param sceneWidth
     * @param sceneHeight
     * @param screenWidth
     * @param screeHeight
     * @return
     */
    public Camera config(int sceneWidth, int sceneHeight, int screenWidth, int screeHeight) {

        if (sceneWidth + sceneHeight < screenWidth + screeHeight) {
            throw new IllegalArgumentException("The screen size is more larger than scene.");
        }

        this.w = sceneWidth;
        this.h = sceneHeight;

        this.scrw = (short) screenWidth;
        this.scrh = (short) screeHeight;

        return this;
    }

    /**
     * Fix position x
     *
     * @param px2
     * @return
     */
    public int fx(float px) {
        return (int) (px - this.cpx);
    }

    /**
     * Fix position y
     *
     * @param py
     * @return
     */
    public int fy(float py) {
        return (int) (py - this.cpy);
    }

    public float getCpx() {
        return cpx;
    }

    public void setCpx(int cpx) {
        this.cpx = cpx;
    }

    public float getCpy() {
        return cpy;
    }

    public void setCpy(float cpy) {
        this.cpy = cpy;
    }

    public int getPx() {
        return (int) cpx;
    }

    public int getPy() {
        return (int) cpy;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isAllowOffset() {
        return allowOffset;
    }

    public void setAllowOffset(boolean allowOffset) {
        this.allowOffset = allowOffset;
    }

    public void rollX(float x) {
        cpx += x;

        if (allowOffset) {
            return;
        }

        if (cpx < 0) {
            cpx = 0;
        } else if (cpx > w) {
            cpx = w;
        }
    }

    public void rollY(float y) {
        cpy += y;

        if (allowOffset) {
            return;
        }

        if (cpy < 0) {
            cpy = 0;
        } else if (cpy > h) {
            cpy = h;
        }
    }

    public void move(float nx, float ny) {
        /*
         * if (lck == LockType.ALL) { return; }
         */

        cpx = nx;
        cpy = ny;

        if (allowOffset) {
            return;
        }

        if (cpx < -xOffset) {
            cpx = -xOffset;

        } else if (cpx + xOffset > (w - scrw)) {
            cpx = w - scrw;
        }

        if (cpy < -yOffset) {
            cpy = -yOffset;
        } else if (cpy + yOffset > (h - scrh)) {
            cpy = h - scrh;
        }

    }

    public void draw(Graphics2D g, ElementModel el) {
        if (active) {
            AffineTransform t = g.getTransform();

            g.translate(-cpx, -cpy);
            el.drawMe(g);

            g.setTransform(t);
        } else {
            el.drawMe(g);
        }

    }

    @Override
    public String toString() {
        return "Camera [cpx=" + cpx + ", cpy=" + cpy + "]";
    }

    public void center(ElementModel el) {
        cpx = el.getPx() - (Engine.getIWindowGame().getCanvasWidth() / 2);
        cpy = el.getPy() - (Engine.getIWindowGame().getCanvasHeight() / 2);

        if (cpx < 0) {
            cpx = 0;

        } else if (cpx > (w - Engine.getIWindowGame().getCanvasWidth())) {
            cpx = w - Engine.getIWindowGame().getCanvasWidth();
        }

        if (cpy < 0) {
            cpy = 0;

        } else if (cpy > (h - Engine.getIWindowGame().getCanvasHeight())) {
            cpy = h - Engine.getIWindowGame().getCanvasHeight();
        }
    }

    /**
     * Draw element on cam with fix x,y position
     *
     * @param g
     * @param el
     */
    public void closer(Graphics2D g, ElementModel el) {
        if (el.getImage() != null) {
            g.drawImage(el.getImage().getImage(), fx(el.getPx()), el.getPy(), null);

        } else {
            g.setColor(el.getColor());
            g.drawRect(fx(el.getPx()), fy(el.getPy()), el.getWidth(), el.getHeight());
        }

    }

    public void offSet(int x, int y) {
        xOffset = (short) x;
        yOffset = (short) y;
    }

    public int getSceneWidth() {
        return w;
    }

    public int getSceneHeight() {
        return h;
    }
}
