/**
 * 
 */
package me.xuender.greatlearning;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * 播放器
 * 
 * @author ender
 * 
 */
public class Player {
	private static MediaPlayer player;
	private static boolean play;

	static boolean isPlay() {
		return play;
	}
	/**
	 * 初始化播放
	 * 
	 * @param context
	 */
	static void play(Context context) {
		if (player == null) {
			player = MediaPlayer.create(context, R.raw.gl);
		}
		start();
	}

	static void seekTo(int i) {
		if (player != null) {
			player.seekTo(i);
		}
	}

	/**
	 * 播放
	 */
	static void start() {
		if (player != null) {
			player.start();
			play = true;
		}
	}

	/**
	 * 暂停
	 */
	static void pause() {
		pause(false);
	}

	/**
	 * 暂停
	 */
	static void pause(boolean p) {
		if (player != null) {
			player.pause();
		}
		play = p;
	}

	/**
	 * 停止
	 */
	static void stop() {
		if (player != null) {
			player.stop();
		}
		play = false;
	}

	static MediaPlayer getPlayer() {
		return player;
	}
}
