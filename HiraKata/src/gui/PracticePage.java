package gui;

import java.util.Random;
import java.util.Vector;
import bl.DrawingPanel;
import bl.HiraKataApplication;
import com.example.hirakata.R;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

	@SuppressWarnings("unused")
	private TextView largeText;
	@SuppressWarnings("unused")
	private ImageView iconSmall;
	private DrawingPanel dpanel;
	private Vector<Integer> allPicRes;
	private Vector<Integer> shownKana;
	private HiraKataApplication application;
	boolean start = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practice_page);

		allPicRes = new Vector<Integer>();
		shownKana = new Vector<Integer>();

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

		// getAllDrawableResources();
		// showKana(this.allPicRes.get(0));
	}

	@Override
	public void onClick(View view) {

		int actualKana = application.getIndexOfUsedKana();

		if (start) {
			getAllDrawableResources();
			showKana(this.allPicRes.get(actualKana));
			start = false;
		}

		if (view.getId() == R.id.drawButton) {
			drawKana();
		} else if (view.getId() == R.id.deleteButton) {
			showKana(this.allPicRes.get(actualKana));
		} else if (view.getId() == R.id.nextButton) {
			this.shownKana.add(actualKana);
			nextKana(actualKana);
		} else if (view.getId() == R.id.backButton) {
			previousKana(actualKana);
		}
	};

	public void drawKana() {
		this.dpanel.setDrawAble(true);
		this.dpanel.setColor("BLACK");
		this.dpanel.setStrokeWidth(15);
		this.dpanel.invalidate();
	}

	public void previousKana(int actualKana) {
		if (application.isOrder()) {
			actualKana--;
		} else {
			if ((this.shownKana.size() > 0)
					&& (this.shownKana.indexOf(actualKana) > 0)) {
				actualKana = this.shownKana.elementAt(this.shownKana
						.indexOf(actualKana) - 1);
			} else {
				Toast.makeText(this, this.getString(R.string.prev_toast),
						Toast.LENGTH_LONG).show();
			}
		}
		if (actualKana >= 0) {
			showKana(this.allPicRes.get(actualKana));
			application.setIndexOfUsedKana(actualKana);
			this.dpanel.invalidate();
		} else {
			Toast.makeText(this, this.getString(R.string.prev_toast),
					Toast.LENGTH_LONG).show();
		}
	}

	public void nextKana(int actualKana) {
		if (application.isOrder()) {
			actualKana++;
			if (application.getNumberOfDrawables() > actualKana) {
				showKana(this.allPicRes.get(actualKana));
				application.setIndexOfUsedKana(actualKana);
				this.dpanel.invalidate();
			} else {
				Toast.makeText(this, this.getString(R.string.next_toast),
						Toast.LENGTH_LONG).show();
			}
		}
		if (!application.isOrder()) {
			Random rand = new Random();
			actualKana = rand.nextInt(this.shownKana.size());
			int count = 0;
			while (this.shownKana.contains(actualKana)
					&& (count < this.allPicRes.size())) {
				actualKana = rand.nextInt(this.shownKana.size() + 1);
				count++;
			}
			this.shownKana.add(actualKana);

			if (application.getNumberOfDrawables() > actualKana) {
				showKana(this.allPicRes.get(actualKana));
				application.setIndexOfUsedKana(actualKana);
				this.dpanel.invalidate();
			} else {
				Toast.makeText(this, this.getString(R.string.next_toast),
						Toast.LENGTH_LONG).show();
			}
		}
	}

	public void showKana(int id) {
		Bitmap drawable = BitmapFactory.decodeResource(getResources(), id);
		this.dpanel.newDrawing(drawable);
		this.dpanel.invalidate();
	}

	public boolean getAllDrawableResources() {
		this.allPicRes.clear();
		application = ((HiraKataApplication) this.getApplicationContext());
		int maxDrawable = application.getNumberOfDrawables();
		int resource = 0;
		String mode = application.getMode();

		if (mode.equals("all_")) {
			mode = "hira_";
			try {
				for (int i = 0; i < maxDrawable; i++) {
					resource = R.drawable.class.getField(mode + i).getInt(null);
					this.allPicRes.add(resource);
					mode = "kata_";
				}
			} catch (Exception e) {
				return false;
			}
			mode = "kata_";
		}

		try {
			for (int i = 0; i < maxDrawable; i++) {
				resource = R.drawable.class.getField(mode + i).getInt(null);
				this.allPicRes.add(resource);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practice_page, menu);
		return true;
	}

}
