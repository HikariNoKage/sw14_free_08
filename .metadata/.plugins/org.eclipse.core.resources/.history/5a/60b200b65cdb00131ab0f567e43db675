package gui;

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
	private HiraKataApplication drawableNumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practice_page);

		allPicRes = new Vector<Integer>();

		if (getAllDrawableResources()) {

			// Toast.makeText(this, "TEST: " + this.allPicRes.size(),
			// Toast.LENGTH_LONG).show();

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

			showKana(this.allPicRes.get(0));

		} else {
			Toast.makeText(this, "Error: Could not load drawable images!!",
					Toast.LENGTH_LONG).show();
		}

	}

	@Override
	public void onClick(View view) {

		if (view.getId() == R.id.drawButton) {
			this.dpanel.setDrawAble(true);
			this.dpanel.setColor("BLACK");
			this.dpanel.setStrokeWidth(15);
			this.dpanel.invalidate();

		} else if (view.getId() == R.id.deleteButton) {
			showKana(this.allPicRes.get(0));
		} else if (view.getId() == R.id.nextButton) {

		} else if (view.getId() == R.id.backButton) {

		}
	};

	public void showKana(int id) {
		Bitmap drawable = BitmapFactory.decodeResource(getResources(), id);
		this.dpanel.newDrawing(drawable);
		this.dpanel.invalidate();
	}

	public boolean getAllDrawableResources() {
		drawableNumber = ((HiraKataApplication) this.getApplicationContext());
		int maxDrawable = drawableNumber.getNumberOfDrawables();
		int resource = 0;

		try {
			for (int i = 0; i < maxDrawable; i++) {
				resource = R.drawable.class.getField("hira_" + i).getInt(null);
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
