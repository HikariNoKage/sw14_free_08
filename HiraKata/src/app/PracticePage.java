package app;

import java.util.Map;
import java.util.Vector;
import com.example.android.app.R;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PracticePage extends Activity implements OnClickListener {

	private ImageButton soundButton, backButton, drawButton, deleteButton,
			nextButton;

	private TextView largeText;
	private ImageView iconSmall;
	private DrawingPanel dpanel;
	private HiraKataApplication application;
	boolean start = true;
	private Vector<Integer> allPicRes;
	private Map<Integer, String> names;
	private Map<Integer, Integer> smallPics;
	private Map<Integer, Integer> sounds;
	private MediaPlayer play;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practice_page);

		this.soundButton = (ImageButton) findViewById(R.id.soundButton);
		this.backButton = (ImageButton) findViewById(R.id.backButton);
		this.drawButton = (ImageButton) findViewById(R.id.drawButton);
		this.deleteButton = (ImageButton) findViewById(R.id.deleteButton);
		this.nextButton = (ImageButton) findViewById(R.id.nextButton);

		this.soundButton.setOnClickListener(this);
		this.backButton.setOnClickListener(this);
		this.drawButton.setOnClickListener(this);
		this.deleteButton.setOnClickListener(this);
		this.nextButton.setOnClickListener(this);

		this.largeText = (TextView) findViewById(R.id.lageText);
		this.iconSmall = (ImageView) findViewById(R.id.iconSmall);
		this.dpanel = (DrawingPanel) findViewById(R.id.drawing);

		application = ((HiraKataApplication) this.getApplicationContext());
		allPicRes = new Vector<Integer>();

		if (application.isOrder()) {
			this.allPicRes.clear();
			this.allPicRes = application.getAllPicRes();
			/*
			 * Log.w("order", "order true: " + application.isOrder() + " size:"
			 * + allPicRes.size());
			 */
		} else {
			this.allPicRes.clear();
			this.allPicRes = application.getAllPicResRand();
			/*
			 * Log.w("order", "order false: " + application.isOrder() + " size:"
			 * + allPicRes.size());
			 */
		}
		names = application.getNames();
		smallPics = application.getPicResSmall();
		sounds = application.getSounds();
		// Log.w("Sounds", "sounds: "+ sounds.size());
	}

	@Override
	public void onClick(View view) {

		int actualKana = application.getIndexOfUsedKana();

		if (start) {
			showKana(this.allPicRes.get(actualKana));
			this.largeText.setText(names.get(this.allPicRes.get(actualKana)));
			this.iconSmall.setImageResource(smallPics.get(this.allPicRes
					.get(actualKana)));
			start = false;
		}

		if (view.getId() == R.id.drawButton) {
			drawKana();
		} else if (view.getId() == R.id.deleteButton) {
			showKana(this.allPicRes.get(actualKana));
		} else if (view.getId() == R.id.nextButton) {
			nextKana(actualKana);
		} else if (view.getId() == R.id.backButton) {
			previousKana(actualKana);
		} else if (view.getId() == R.id.soundButton) {
			play = MediaPlayer.create(getApplicationContext(),
					this.sounds.get(this.allPicRes.get(actualKana)));

			AudioManager am = (AudioManager) getSystemService(AUDIO_SERVICE);
			// int volume_level= am.getStreamVolume(AudioManager.STREAM_MUSIC);

			switch (am.getRingerMode()) {
			case AudioManager.RINGER_MODE_SILENT:
				play.setVolume(0, 0);
				break;
			case AudioManager.RINGER_MODE_VIBRATE:
				play.setVolume(0, 0);
				break;
			case AudioManager.RINGER_MODE_NORMAL:
				play.setVolume(1, 1);
				break;
			}

			this.soundButton.setEnabled(false);

			play.setOnCompletionListener(new OnCompletionListener() {

				@Override
				public void onCompletion(MediaPlayer mpalmost) {
					soundButton.setEnabled(true);
					mpalmost.release();
				}
			});
			play.start();
		}
	};

	public void drawKana() {
		this.dpanel.setDrawAble(true);
		// this.dpanel.setColor("BLACK");
		this.dpanel.setStrokeWidth(45);
		this.dpanel.invalidate();
	}

	public void previousKana(int actualKana) {
		Log.w("prev", "prev: " + actualKana);
		if (actualKana > 0) {
			actualKana--;
			this.largeText.setText(names.get(this.allPicRes.get(actualKana)));
			this.iconSmall.setImageResource(smallPics.get(this.allPicRes
					.get(actualKana)));
			showKana(this.allPicRes.get(actualKana));
			application.setIndexOfUsedKana(actualKana);
			this.dpanel.invalidate();
		} else {
			Toast.makeText(this, this.getString(R.string.prev_toast),
					Toast.LENGTH_LONG).show();
		}
	}

	public void nextKana(int actualKana) {
		Log.w("next", "next: " + actualKana);
		if (this.allPicRes.size() > (actualKana + 1)) {
			actualKana++;
			this.largeText.setText(names.get(this.allPicRes.get(actualKana)));
			this.iconSmall.setImageResource(smallPics.get(this.allPicRes
					.get(actualKana)));
			showKana(this.allPicRes.get(actualKana));
			application.setIndexOfUsedKana(actualKana);
			this.dpanel.invalidate();
		} else {
			Toast.makeText(this, this.getString(R.string.next_toast),
					Toast.LENGTH_LONG).show();
		}
	}

	public void showKana(int id) {
		Bitmap drawable = BitmapFactory.decodeResource(getResources(), id);
		this.dpanel.newDrawing(drawable);
		this.dpanel.invalidate();
	}

	@Override
	public void onBackPressed() {
		application.setIndexOfUsedKana(0);
		this.finish();
		return;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practice_page, menu);
		return true;
	}

}