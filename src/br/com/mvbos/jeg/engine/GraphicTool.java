package br.com.mvbos.jeg.engine;

import br.com.mvbos.jeg.element.ElementModel;
import br.com.mvbos.jeg.scene.Click;

public class GraphicTool {

	private static GraphicTool g;

	public static GraphicTool g() {
		if (g == null) {
			g = new GraphicTool();
		}

		return g;
	}

	public void centerWindow(ElementModel el) {
		el.setPx(Engine.getIWindowGame().getWindowWidth() / 2 - el.getWidth()
				/ 2);
		el.setPy(Engine.getIWindowGame().getWindowHeight() / 2 - el.getHeight()
				/ 2);
	}

	/**
	 * Verifica se dois elementos com enabled = true colidem
	 * 
	 * @param e
	 * @param em
	 * @return
	 */
	public ElementModel collide(ElementModel e, ElementModel em) {
		if (e == null || em == null || e == em) {
			return e;
		}
		if (!e.isEnabled() || !em.isEnabled()) {
			return null;
		}
		if (intersecElement(e, em)) {
			return em;
		}

		return null;
	}

	// TODO efetuar testes
	public boolean intersecElement(float ax, float ay, float bx, float by,
			int aw, int ah, int bw, int bh) {
		if (ax + aw >= bx && ax <= bx + bw && ay + ah >= by && ay <= by + bh) {
			return true;
		}

		return false;
	}

	/**
	 * Verifica se dois elementos colidem
	 * 
	 * @param eA
	 * @param eB
	 * @return
	 */

	public boolean intersecElement(ElementModel eA, ElementModel eB) {
		final int pwA = (int) eA.getHitX() + eA.getHitWidth();
		final int pwB = (int) eB.getHitX() + eB.getHitWidth();
		final int phA = (int) eA.getHitY() + eA.getHitHeight();
		final int phB = (int) eB.getHitY() + eB.getHitHeight();

		if (pwA >= eB.getHitX() && eA.getHitX() <= pwB && phA >= eB.getHitY()
				&& eA.getHitY() <= phB) {
			return true;
		}

		return false;
	}

	/**
	 * Verifica se o elemento localiza-se nos pontos passados
	 * 
	 * @param elementAX
	 * @param elementAY
	 * @param e
	 * @return
	 */

	public boolean intersec(int x, int y, ElementModel e) {
		return intersecElement(x, y, e.getHitX(), e.getHitY(), 1, 1,
				e.getHitWidth(), e.getHitHeight());
		/*
		 * if ((elementAX >= e.getHitX() && elementAX <= (e.getHitX() +
		 * e.getHitWidth())) && (elementAY >= e.getHitY() && elementAY <=
		 * (e.getHitY() + e .getHitHeight()))) { return true; }
		 * 
		 * return false;
		 */
	}

	/**
	 * Verifica se o elemento localiza-se nos pontos do click
	 * 
	 * @param elementAX
	 * @param elementAY
	 * @param e
	 * @return
	 */

	public boolean intersec(Click c, ElementModel e) {
		return intersecElement(c.getPx(), c.getPy(), e.getHitX(), e.getHitY(),
				1, 1, e.getHitWidth(), e.getHitHeight());

	}

}
