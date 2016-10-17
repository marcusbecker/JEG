package br.com.mvbos.jeg.element;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author mbecker
 */
public class SelectorElement extends ElementModel {

    private int borderSpace = 5;

    public SelectorElement(String name) {
        this(Color.BLUE, name);
    }

    public SelectorElement(Color color, String name) {
        super.setName(name);
        super.setColor(color);
        super.setEnabled(false);
    }

    @Override
    public void drawMe(Graphics2D g) {

        if (!isEnabled()) {
            return;
        }

        g.setColor(getColor());

        boolean revertX = getPx() < getWidth();
        boolean revertY = getPy() < getHeight();

        //Simple way
        //g.drawRect(getPx(), getPy(), getWidth() - getPx(), getHeight() - getPy());
        //reverse way
        g.drawRect(revertX ? getPx() : getWidth(), revertY ? getPy() : getHeight(),
                revertX ? getWidth() - getPx() : getPx() - getWidth(),
                revertY ? getHeight() - getPy() : getPy() - getHeight());
    }

    @Override
    public boolean isEnabled() {
        return super.isEnabled() && getWidth() > 0;
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        super.setVisible(enabled);

        if (!enabled) {
            setPxy(0, 0);
            setSize(0, 0);
        }

    }

    public void adjustInvertSelection() {
        int temp;

        if (getPx() < getWidth()) {
            setWidth(getWidth() - getPx());

        } else {
            temp = getWidth();
            setWidth(getPx() - getWidth());
            setPx(temp);
        }

        if (getPy() < getHeight()) {
            setHeight(getHeight() - getPy());

        } else {
            temp = getHeight();
            setHeight(getPy() - getHeight());
            setPy(temp);
        }
    }

    public int getBorderSpace() {
        return borderSpace;
    }

    public void setBorderSpace(int borderSpace) {
        this.borderSpace = borderSpace;
    }

}
