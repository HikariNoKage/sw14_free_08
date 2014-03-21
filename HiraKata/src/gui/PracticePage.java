package gui;

import java.io.File;
import java.util.Vector;

import bl.DrawingPanel;

import com.example.hirakata.R;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Picture;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practice_page);

		allPicRes = new Vector<Integer>();

		// getAllDrawableResources();

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
			this.dpanel.setColor("BLACK");
			this.dpanel.setStrokeWidth(15);
			this.dpanel.invalidate();

		} else if (view.getId() == R.id.deleteButton) {

			int id = 0;

			String name = "hira_1";
			try {
				id = R.drawable.class.getField(name).getInt(null);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			showKana(id);

		} else if (view.getId() == R.id.nextButton) {

			// ContextWrapper c = new ContextWrapper(this);
			Toast.makeText(this, this.getFilesDir().getPath(),
					Toast.LENGTH_LONG).show();
		} else if (view.getId() == R.id.backButton) {

		}
	};

	public void showKana(int id) {
		Bitmap drawable = BitmapFactory.decodeResource(getResources(), id);
		this.dpanel.newDrawing(drawable);
		this.dpanel.invalidate();
	}

	/*
	 * public void getAllDrawableResources() {
	 * 
	 * 
	 * File file = new File("com.example.hirakata/res/drawable-hdpi"); File[]
	 * files = file.listFiles(); int res = 0;
	 * 
	 * 
	 * if (files != null) { for (int i = 0; i <= files.length; i++) { res =
	 * this.getResources().getIdentifier("hira_" + i, "drawable-hdpi",
	 * this.getPackageName()); allPicRes.add(res); } } else { int i =
	 * R.drawable.ic_launcher; allPicRes.add(i); } }
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practice_page, menu);
		return true;
	}

}
