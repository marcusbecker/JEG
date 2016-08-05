package br.com.mvbos.jeg.engine;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.swing.Icon;

import javax.swing.ImageIcon;

public class SpriteTool {

    public static final int SORT = -1;

    private static SpriteTool s;

    private ImageIcon image;
    private int columns;
    private int lines;
    private boolean invert;

    public static SpriteTool s(ImageIcon image) {
        if (s == null) {
            s = new SpriteTool();
        }

        s.setImage(image);
        return s;
    }

    public static SpriteTool createNew() {
        return new SpriteTool();
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public SpriteTool matriz(int columns, int lines) {
        this.columns = columns;
        this.lines = lines;
        return this;
    }

    public SpriteTool draw(Graphics2D g2d, int col, int lin) {
        return draw(g2d, 0, 0, col, lin);
    }

    public SpriteTool draw(Graphics2D g2d, int pX, int pY, int mX, int mY) {

        if (mX == SORT) {
            mX = MathTool.r.nextInt(columns);
        }

        if (mY == SORT) {
            mY = MathTool.r.nextInt(lines);
        }

        int cs = getImage().getIconWidth() / columns; // Column start
        int ls = getImage().getIconHeight() / lines; // Line start

        int ch = cs * mX; // Column height
        int lw = ls * mY; // Line width

        if (invert) {
            AffineTransform old = g2d.getTransform();

            //TODO verify if need to move this to local variable
            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
            tx.translate(-getImage().getIconWidth(), 0);

            g2d.setTransform(tx);

            pX = (-pX) + getImage().getIconWidth();

            g2d.drawImage(getImage().getImage(), pX - cs, pY, pX, pY + ls, ch, lw, ch + cs, lw + ls, getObserver());

            g2d.setTransform(old);

        } else {
            g2d.drawImage(getImage().getImage(), pX, pY, pX + cs, pY + ls, ch, lw, ch + cs, lw + ls, getObserver());
        }

        return this;
    }

    private ImageObserver getObserver() {
        // TODO Auto-generated method stub
        return null;
    }

    public SpriteTool invert(boolean invert) {
        this.invert = invert;
        return this;

    }

    public ImageIcon createIcon(int col, int lin) {
        final BufferedImage bf = new BufferedImage(35, 35, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gg = bf.createGraphics();
        draw(gg, 0, 0, col, lin);
        ImageIcon ico = new ImageIcon(bf);
        gg.dispose();

        return ico;
    }
}
