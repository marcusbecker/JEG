package br.com.mvbos.jeg.engine;

public class Chrono {

	public static long timePass;
	// public static long timePlay;

	private static long timeInit;

	public static void startCountTimePlay(long millis) {
		timeInit = millis;
	}

	public static long getTimePlay() {
		return System.currentTimeMillis() - timeInit;
	}

}
