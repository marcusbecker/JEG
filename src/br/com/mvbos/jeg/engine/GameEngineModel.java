package br.com.mvbos.jeg.engine;

import br.com.mvbos.jeg.window.IWindowGame;

public class GameEngineModel {

	private IWindowGame wg;

	private Thread gameThread;

	private final int id;
	private int fps;
	private int ups;

	private boolean autoAdjust = true;

	public GameEngineModel(int engineId) {
		this.id = engineId;
	}

	public void fill(IWindowGame wgg, final int framePerSeconds, final int updatePerSeconds) {
		this.wg = wgg;
		change(framePerSeconds, updatePerSeconds);
	}

	public void change(int framePerSeconds, int updatePerSeconds) {
		// Engine.log("FPS: ", framePerSeconds);
		// Engine.log("UPS: ", updatePerSeconds);

		this.fps = framePerSeconds;
		this.ups = updatePerSeconds;

	}

	public boolean start() {
		gameThread = new Thread() {

			long nextDraw = 0;
			long nextUpdate = 0;

			@Override
			public void run() {
				Engine.log("enginit");

				// final long init = getMillis(); Chrono.timePlay = getMillis()
				// - init;
				Chrono.startCountTimePlay(getMillis());

				try {
					long maxTime = 0;
					int skipDraw = 0;
					while (Engine.gameLoop) {

						if (nextDraw <= getMillis()) {
							wg.engineNotification(id);

							long beforeTime = getMillis();

							if (nextUpdate <= getMillis()) {
								wg.updateGame();
								nextUpdate = getMillis() + ups;
							}

							if (skipDraw >= fps) {
								Engine.log("Draw skiped: " + skipDraw);
								skipDraw -= fps;

							} else {
								wg.drawGame();
							}

							beforeTime = getMillis() - beforeTime;// res
							Chrono.timePass = beforeTime;

							if (beforeTime <= fps) {
								nextDraw = getMillis() + (fps - beforeTime);
							} else {
								skipDraw += beforeTime - fps;
								maxTime = beforeTime;
								Engine.log("Time to process: " + maxTime);

								nextDraw = getMillis() + fps;

								if (autoAdjust) {
									fps += 2;
									Engine.log("Adjust FPS to: " + fps);
								}
							}
						}

					}

					Engine.log("Max time process: " + maxTime);

				} catch (Exception e) {
					e.printStackTrace();
					Engine.log("Game loop abort " + Engine.gameLoop);
				}
			}

			private long getMillis() {
				return System.currentTimeMillis();
			}

		};
		Engine.gameLoop = true;
		gameThread.start();
		return true;
	}

	public boolean pause() {
		Engine.pauseGame(true);
		return true;
	}

	public boolean resume() {
		Engine.pauseGame(false);
		return true;
	}

	public boolean stop() {
		// gameThread.cancel(true);
		Engine.gameLoop = false;
		gameThread = null;
		return true;
	}

}
