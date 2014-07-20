package br.com.mvbos.jeg.os;

public class GameSound {

	public void load() {
	}

	public void playSound(int soundId) {
		/*
		 * Updated: The next 4 lines calculate the current volume in a scale of
		 * 0.0 to 1.0
		 */
		/* Play the sound with the correct volume */
		// AudioManager mgr = (AudioManager)
		// context.getSystemService(Context.AUDIO_SERVICE);

		// float streamVolumeCurrent =
		// mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
		// float streamVolumeMax =
		// mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		// float volume = streamVolumeCurrent / streamVolumeMax;
		// soundPool.play(sounds[soundId], volume, volume, 1, 0, 1f);

		playSound(soundId, 1f, 1f, 1, -1, 1f);
	}

	public void playSound(int soundId, float volume, int loop, float rate) {
		// soundPool.play(sounds[soundId], volume, volume, 1, loop, rate);
	}

	public void playSound(int soundId, float leftVolume, float rightVolume,
			int priority, int loop, float rate) {
		// soundPool.play(sounds[soundId], leftVolume, rightVolume, priority,
		// loop, rate);
	}

	public void pauseSound(int soundId) {
		// soundPool.pause(sounds[soundId]);
	}

	public void stopSound(int soundId) {
		// soundPool.stop(sounds[soundId]);
	}

	public void setRate(int soundId, float rate) {
		// soundPool.setRate(sounds[soundId], rate);
	}

	public void setVolume(int soundId, float volume) {
		// soundPool.setVolume(sounds[soundId], volume, volume);
	}

	public void stopAll() {
		// soundPool.release();
	}

}
