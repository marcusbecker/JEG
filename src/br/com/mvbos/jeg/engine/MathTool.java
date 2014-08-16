package br.com.mvbos.jeg.engine;

import java.util.Random;

public class MathTool {

	private static MathTool m;
	
	public static final Random r = new Random();

	public static MathTool m() {
		if (m == null) {
			m = new MathTool();
		}

		return m;
	}
}
