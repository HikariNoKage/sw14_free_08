package gui;


import java.util.Collections;
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
	private HiraKataApplication application;
	boolean start = true;
	private Vector<Integer> allPicRes;

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

		allPicRes = application.getAllPicRes();
		if (application.isOrder() == false) {
			Collections.shuffle(allPicRes);
		}
	}

	@Override
	public void onClick(View view) {

		int actualKana = application.getIndexOfUsedKana();

		if (start) {
			showKana(this.allPicRes.get(actualKana));
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
		}
	};

	public void drawKana() {
		this.dpanel.setDrawAble(true);
		this.dpanel.setColor("BLACK");
		this.dpanel.setStrokeWidth(15);
		this.dpanel.invalidate();
	}

	public void previousKana(int actualKana) {
		actualKana--;
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
		actualKana++;
		if (this.allPicRes.size() > actualKana) {
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practice_page, menu);
		return true;
	}

}
