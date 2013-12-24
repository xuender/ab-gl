package me.xuender.greatlearning;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;

/**
 * 主界面
 * 
 * @author ender
 * 
 */
public class MainActivity extends Activity implements OnPreparedListener,
		MediaPlayerControl {
	private MediaController mediaController;
	private Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mediaController = new MediaController(this);
		Player.play(this);
		Player.getPlayer().setOnPreparedListener(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Player.stop();
		System.exit(0);
	}

	@Override
	public boolean canPause() {
		return true;
	}

	@Override
	public boolean canSeekBackward() {
		return false;
	}

	@Override
	public boolean canSeekForward() {
		return false;
	}

	@Override
	public int getAudioSessionId() {
		return 0;
	}

	@Override
	public int getBufferPercentage() {
		return 0;
	}

	@Override
	public int getCurrentPosition() {
		return Player.getPlayer().getCurrentPosition();
	}

	@Override
	public int getDuration() {
		return Player.getPlayer().getDuration();
	}

	@Override
	public boolean isPlaying() {
		return Player.getPlayer().isPlaying();
	}

	@Override
	public void pause() {
		Log.d("play", "pause");
		Player.pause();
	}

	@Override
	public void seekTo(int arg0) {
		Player.seekTo(arg0);
	}

	@Override
	public void start() {
		Log.d("play", "start");
		Player.start();
	}

	@Override
	protected void onStop() {
		super.onStop();
		mediaController.hide();
		Player.stop();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mediaController.show();
		return false;
	}

	@Override
	public void onPrepared(MediaPlayer arg0) {
		mediaController.setMediaPlayer(this);
		mediaController.setAnchorView(findViewById(R.id.main_audio_view));
		handler.post(new Runnable() {
			public void run() {
				mediaController.setEnabled(true);
				mediaController.show();
			}
		});
	}
}