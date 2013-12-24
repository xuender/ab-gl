package me.xuender.greatlearning;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Handler;
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
	private MediaPlayer mediaPlayer;
	private Handler handler = new Handler();
	private static MediaPlayerControl instance;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mediaController = new MediaController(this);
		mediaPlayer = MediaPlayer.create(this, R.raw.gl);
		mediaPlayer.setOnPreparedListener(this);
		mediaPlayer.setLooping(false);
		instance = this;
		start();
	}

	static MediaPlayerControl getInstance() {
		return instance;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mediaPlayer.stop();
		System.exit(0);
	}

	@Override
	public void start() {
		mediaPlayer.start();
	}

	@Override
	public void pause() {
		mediaPlayer.pause();
	}

	@Override
	public int getDuration() {
		return mediaPlayer.getDuration();
	}

	@Override
	public int getCurrentPosition() {
		return mediaPlayer.getCurrentPosition();
	}

	@Override
	public void seekTo(int pos) {
		mediaPlayer.seekTo(pos);
	}

	@Override
	public boolean isPlaying() {
		return mediaPlayer.isPlaying();
	}

	@Override
	public int getBufferPercentage() {
		return 0;
	}

	@Override
	public boolean canPause() {
		return true;
	}

	@Override
	public boolean canSeekBackward() {
		return true;
	}

	@Override
	public boolean canSeekForward() {
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mediaController.show();
		return false;
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		mediaController.setMediaPlayer(this);
		mediaController.setAnchorView(findViewById(R.id.main_audio_view));
		handler.post(new Runnable() {
			public void run() {
				mediaController.setEnabled(true);
				mediaController.show();
			}
		});
	}

	public int getAudioSessionId() {
		return mediaPlayer.getAudioSessionId();
	}
}