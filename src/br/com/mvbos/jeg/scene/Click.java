/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mvbos.jeg.scene;

import java.awt.event.MouseEvent;

/**
 * 
 * @author mbecker
 */
public class Click {

	private float px;
	private float py;
	private int button;
	private int clickCount;
	private MouseEvent event;

	public void setClick(int px, int py, int button, int clickCount) {
		this.px = px;
		this.py = py;
		this.button = button;
		this.clickCount = clickCount;
	}

	public void setClick(float px, float py, int button, int clickCount) {
		this.px = px;
		this.py = py;
		this.button = button;
		this.clickCount = clickCount;
	}

	public int getButton() {
		return button;
	}

	public void setButton(int button) {
		this.button = button;
	}

	public int getClickCount() {
		return clickCount;
	}

	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
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

	public MouseEvent getEvent() {
		return event;
	}

	public void setEvent(MouseEvent event) {
		this.event = event;

	}

}
