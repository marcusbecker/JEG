package br.com.mvbos.jeg.engine;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class SpriteTool {

	private static SpriteTool s;
	private ImageIcon image;
	private int lines;
	private int coluns;
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

	public SpriteTool matriz(int lines, int coluns) {
		this.lines = lines;
		this.coluns = coluns;
		return this;
	}

	public SpriteTool draw(Graphics2D g2d, int pX, int pY, int mX, int mY) {
		int r = getImage().getIconWidth() / lines;
		int l = getImage().getIconHeight() / coluns;

		int n = r * mX;
		int m = l * mY;

		AffineTransform old = g2d.getTransform();

		if (invert) {
			AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
			tx.translate(-getImage().getIconWidth(), 0);

			g2d.setTransform(tx);

			pX = (-pX) + getImage().getIconWidth();

			g2d.drawImage(getImage().getImage(), pX - r, pY, pX, pY + l, n, m, n + r, m + l, getObserver());

		} else {
			g2d.drawImage(getImage().getImage(), pX, pY, pX + r, pY + l, n, m, n + r, m + l, getObserver());
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
