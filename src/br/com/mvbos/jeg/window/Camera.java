package br.com.mvbos.jeg.window;

import br.com.mvbos.jeg.engine.Engine;

/**
 * @author mbecker
 * 
 */

public class Camera {

	private float cpx;
	private float cpy;

	private static Camera c;

	public static Camera c() {
		if (c == null) {
			c = new Camera();
		}

		return c;
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
		if (cpy > Engine.getIWindowGame().getCanvasHeight()) {
			cpy = Engine.getIWindowGame().getCanvasHeight();
		}
	}

	@Override
	public String toString() {
		return "Camera [cpx=" + cpx + ", cpy=" + cpy + "]";
	}

}
