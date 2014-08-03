package br.com.mvbos.jeg.window;

import br.com.mvbos.jeg.element.ElementModel;
import br.com.mvbos.jeg.engine.Engine;

/**
 * @author mbecker
 * 
 */

public class Camera {

	private float cpx;
	private float cpy;

	private int w = 4000;
	private int h = 3970;

	private static Camera c;

	public static Camera c() {
		if (c == null) {
			c = new Camera();
		}

		return c;
	}

	public Camera config(int w, int h) {
		this.w = w;
		this.h = h;
		
		return this;
	}

	/**
	 * Fix position x
	 * 
	 * @param px2
	 * @return
	 */
	public int fx(int px) {
		return (int) (px - this.cpx);
	}

	/**
	 * Fix position y
	 * 
	 * @param py
	 * @return
	 */
	public int fy(int py) {
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

	public void rollX(float x) {
		cpx += x;
		if (cpx < 0) {
			cpx = 0;
		}
	}

	public void rollY(float y) {
		cpy += y;
		// if (cpy > Engine.getIWindowGame().getCanvasHeight()) {
		// cpy = Engine.getIWindowGame().getCanvasHeight();
		// }
	}

	public void move(int nx, int ny) {
		/*
		 * if (lck == LockType.ALL) { return; }
		 */

		if (cpx + nx < 0 || cpx + nx > w) {
			cpx = nx < 0 ? 0 : w;
		} else {
			cpx = nx;
		}

		/*
		 * if (cpy + ny < 0 || cpy + ny > h) { cpy = cpy + ny < 0 ? 0 : h; }
		 * else { cpy = cpy + ny; }
		 */
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

}
