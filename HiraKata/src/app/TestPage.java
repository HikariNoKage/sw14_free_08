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
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TestPage extends Activity implements OnClickListener {

	private ImageButton soundButton;
	private Button bad, ok, good, solve;

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
	private AudioManager am;
	private boolean test_result = false;
	private int bad_count, ok_count, good_count;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_page);

		this.soundButton = (ImageButton) findViewById(R.id.soundButton1);
		this.bad = (Button) findViewById(R.id.bad);
		this.ok = (Button) findViewById(R.id.ok);
		this.good = (Button) findViewById(R.id.good);
		this.solve = (Button) findViewById(R.id.solve);
		
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int with = (int) (displaymetrics.widthPixels / 3);

		this.bad.setWidth(with);
		this.ok.setWidth(with);
		this.good.setWidth(with);

		this.soundButton.setOnClickListener(this);
		this.bad.setOnClickListener(this);
		this.ok.setOnClickListener(this);
		this.good.setOnClickListener(this);
		this.solve.setOnClickListener(this);

		this.largeText = (TextView) findViewById(R.id.lageText1);
		this.iconSmall = (ImageView) findViewById(R.id.iconSmall1);
		this.dpanel = (DrawingPanel) findViewById(R.id.drawing1);

		this.application = ((HiraKataApplication) this.getApplicationContext());
		this.allPicRes = new Vector<Integer>();

		this.allPicRes.clear();
		this.allPicRes = this.application.getAllPicResRand();

		this.names = this.application.getNames();
		this.smallPics = this.application.getPicResSmall();
		this.sounds = this.application.getSounds();
		this.bad_count = 0;
		this.ok_count = 0;
		this.good_count = 0;
		
		startTest();
		
		this.dpanel.setDrawAble(true);
		this.dpanel.setStrokeWidth(45);
		this.dpanel.invalidate();
	}
	
	public void startTest()
	{
		this.largeText.setText(names.get(this.allPicRes.get(application
				.getIndexOfUsedKana())));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View view) {
		int actualKana = application.getIndexOfUsedKana();

		if (view.getId() == R.id.solve) {
			this.solve.setVisibility(View.GONE);
			this.bad.setVisibility(View.VISIBLE);
			this.ok.setVisibility(View.VISIBLE);
			this.good.setVisibility(View.VISIBLE);
			showSolve(actualKana);
			this.dpanel.setDrawAble(false);
		} else if (view.getId() == R.id.bad) {
			this.bad_count++;
			nextKana(actualKana);
			Log.w("Button:", "bad");
		} else if (view.getId() == R.id.ok) {
			this.ok_count++;
			nextKana(actualKana);
			Log.w("Button:", "ok");
		} else if (view.getId() == R.id.good) {
			this.good_count++;
			nextKana(actualKana);
			Log.w("Button:", "good");
		} else if (view.getId() == R.id.soundButton1) {
			play = MediaPlayer.create(getApplicationContext(),
					this.sounds.get(this.allPicRes.get(actualKana)));

			am = (AudioManager) getSystemService(AUDIO_SERVICE);

			int current = am.getStreamVolume(AudioManager.STREAM_MUSIC);

			switch (am.getRingerMode()) {
			case AudioManager.RINGER_MODE_SILENT:
				play.setVolume(0, 0);
				break;
			case AudioManager.RINGER_MODE_VIBRATE:
				play.setVolume(0, 0);
				break;
			case AudioManager.RINGER_MODE_NORMAL:
				play.setVolume(current, current);
				am.setStreamVolume(AudioManager.STREAM_MUSIC, current,
						AudioManager.FLAG_SHOW_UI);

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

	}

	public void showSolve(int actualKana) {
		if (this.allPicRes.size() >= (actualKana)) {
			if (this.allPicRes.get(actualKana) == Integer.MIN_VALUE) {
				while (this.allPicRes.get(actualKana) == Integer.MIN_VALUE) {
					actualKana++;
					if (actualKana >= this.allPicRes.size()) {
						break;
					}
				}
			}

			this.largeText.setText(names.get(this.allPicRes.get(actualKana)));
			this.iconSmall.setImageResource(smallPics.get(this.allPicRes
					.get(actualKana)));

			showKana(this.allPicRes.get(actualKana));
			application.setIndexOfUsedKana(actualKana);
			this.dpanel.invalidate();
		}
	}
	
	public void nextKana(int actualKana) {
		boolean next = false;
		if (this.allPicRes.size() >= (actualKana + 1)) {
			actualKana++;
			next = true;
			if (this.allPicRes.get(actualKana) == Integer.MIN_VALUE) {
				while (this.allPicRes.get(actualKana) == Integer.MIN_VALUE) {
					actualKana++;
					if (actualKana >= this.allPicRes.size()) {
						next = false;
						break;
					}
				}
			}
		}

		if (next) {
			this.largeText.setText(names.get(this.allPicRes.get(actualKana)));
			//this.iconSmall.setImageResource(smallPics.get(this.allPicRes
			//		.get(actualKana)));

			//showKana(this.allPicRes.get(actualKana));
			application.setIndexOfUsedKana(actualKana);
			showKana(R.drawable.background);
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
