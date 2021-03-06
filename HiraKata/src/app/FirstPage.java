package app;

import com.example.android.app.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class FirstPage extends Activity implements OnClickListener {

	private HiraKataApplication hk_att;
	private Button bt_practice, bt_test, bt_table, bt_random;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_page);

		this.bt_practice = (Button) findViewById(R.id.practice);
		this.bt_test = (Button) findViewById(R.id.test);
		this.bt_table = (Button) findViewById(R.id.table);
		this.bt_random = (Button) findViewById(R.id.practice_random);

		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int height = (int) (displaymetrics.heightPixels / 4.8);

		this.bt_practice.setHeight(height);
		this.bt_test.setHeight(height);
		this.bt_table.setHeight(height);
		this.bt_random.setHeight(height);

		this.bt_practice.setOnClickListener(this);
		this.bt_table.setOnClickListener(this);
		this.bt_test.setOnClickListener(this);
		this.bt_random.setOnClickListener(this);
		hk_att = ((HiraKataApplication) this.getApplicationContext());
	}

	@Override
	public void onClick(View view) {
		if (view.getId() == R.id.practice) {
			this.hk_att.setOrder(true);
			Intent intent = new Intent(FirstPage.this, PracticePage.class);
			FirstPage.this.startActivity(intent);
		} else if (view.getId() == R.id.practice_random) {
			this.hk_att.setOrder(false);
			Intent intent = new Intent(FirstPage.this, PracticePage.class);
			FirstPage.this.startActivity(intent);
		} else if (view.getId() == R.id.test) {
			Intent intent = new Intent(FirstPage.this, TestPage.class);
			FirstPage.this.startActivity(intent);
		} else if (view.getId() == R.id.table) {
			Intent intent = new Intent(FirstPage.this, TablePage.class);
			FirstPage.this.startActivity(intent);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}
}
