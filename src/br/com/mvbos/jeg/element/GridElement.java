package br.com.mvbos.jeg.element;

import java.awt.Color;
import java.awt.Graphics2D;

public class GridElement extends ElementModel {

	@Override
	public void drawMe(Graphics2D g2d) {
		g2d.setColor(Color.GRAY);
		for (int i = 10; i < getHeight(); i += 25) {
			g2d.drawLine(0, i, getWidth(), i);
		}
		for (int i = 10; i < getWidth(); i += 25) {
			g2d.drawLine(i, 0, i, getHeight());
		}

		g2d.setColor(Color.BLACK);
		final int mw = Math.round(((float) getWidth()) / 2);
		final int my = Math.round(((float) getHeight()) / 2);
		g2d.drawLine(0, my, getWidth(), my);
		g2d.drawLine(mw, 0, mw, getHeight());

	}
}
