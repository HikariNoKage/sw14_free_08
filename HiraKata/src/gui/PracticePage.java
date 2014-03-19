package gui;

import bl.DrawingPanel;

import com.example.hirakata.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class PracticePage extends Activity implements OnClickListener {

	private ImageButton soundButton, backButton, drawButton, deleteButton,
			nextButton;

	@SuppressWarnings("unused")
	private TextView largeText;
	
	@SuppressWarnings("unused")
	private ImageView iconSmall;
	
	private DrawingPanel dpanel;

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

	}

	@Override
	public void onClick(View view) {

		if (view.getId() == R.id.drawButton) {
			this.dpanel.setDrawAble(true);
			this.dpanel.newDrawing();
			this.dpanel.setColor("BLACK");
			this.dpanel.setStrokeWidth(15);
			this.dpanel.invalidate();

		} else if (view.getId() == R.id.deleteButton) {

		} else if (view.getId() == R.id.nextButton) {

		} else if (view.getId() == R.id.backButton) {

		}

	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practice_page, menu);
		return true;
	}

}
