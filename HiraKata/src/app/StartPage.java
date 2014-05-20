package app;

import com.example.android.app.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class StartPage extends Activity implements OnClickListener {

	private Button bt_all, bt_hira, bt_kata;
	private HiraKataApplication hk_mode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_page);

		this.bt_all = (Button) findViewById(R.id.All);
		this.bt_hira = (Button) findViewById(R.id.Hiragana);
		this.bt_kata = (Button) findViewById(R.id.Katakana);

		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int height = (int) (displaymetrics.heightPixels / 3.5);

		bt_all.setHeight(height);
		bt_hira.setHeight(height);
		bt_kata.setHeight(height);

		this.bt_all.setOnClickListener(this);
		this.bt_hira.setOnClickListener(this);
		this.bt_kata.setOnClickListener(this);

	}

	@Override
	public void onClick(View view) {

		hk_mode = ((HiraKataApplication) this.getApplicationContext());

		if (view.getId() == R.id.All) {
			hk_mode.setMode("all_");
			hk_mode.getAllDrawableResources();
			//Intent intent = new Intent(StartPage.this, FirstPage.class);
			//StartPage.this.startActivity(intent);
			Toast.makeText(this, "NO test Kana!!!!!", Toast.LENGTH_LONG).show();
		} else if (view.getId() == R.id.Hiragana) {
			hk_mode.setMode("hira_");
			hk_mode.getAllDrawableResources();
			Intent intent = new Intent(StartPage.this, FirstPage.class);
			StartPage.this.startActivity(intent);
		} else if (view.getId() == R.id.Katakana) {
			hk_mode.setMode("kata_");
			hk_mode.getAllDrawableResources();
			//Intent intent = new Intent(StartPage.this, FirstPage.class);
			//StartPage.this.startActivity(intent);
			Toast.makeText(this, "NO test Katakana!!!!!", Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start_page, menu);
		return true;
	}
}