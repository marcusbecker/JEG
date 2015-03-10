package br.com.mvbos.jeg.engine;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;

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

		AffineTransform old = g2d.getTransform();

		if (invert) {
			AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
			tx.translate(-getImage().getIconWidth(), 0);

			g2d.setTransform(tx);

			pX = (-pX) + getImage().getIconWidth();

			g2d.drawImage(getImage().getImage(), pX - cs, pY, pX, pY + ls, ch, lw, ch + cs, lw + ls, getObserver());

		} else {
			g2d.drawImage(getImage().getImage(), pX, pY, pX + cs, pY + ls, ch, lw, ch + cs, lw + ls, getObserver());
		}

		g2d.setTransform(old);

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
}
