package gui;

import bl.HiraKataApplication;

import com.example.hirakata.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class FirstPage extends Activity implements OnClickListener {

	private HiraKataApplication hk_att;
	private Button bt_practice, bt_test, bt_table;
	private RadioButton rb_order, rb_random, rb_all, rb_red;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_page);

		this.bt_practice = (Button) findViewById(R.id.practice);
		this.bt_test = (Button) findViewById(R.id.test);
		this.bt_table = (Button) findViewById(R.id.table);

		this.rb_all = (RadioButton) findViewById(R.id.rb_all);
		this.rb_order = (RadioButton) findViewById(R.id.rb_order);
		this.rb_random = (RadioButton) findViewById(R.id.rb_rand);
		this.rb_red = (RadioButton) findViewById(R.id.rb_red);

		this.rb_order.setChecked(true);
		this.rb_all.setChecked(true);

		this.bt_practice.setOnClickListener(this);
		this.bt_table.setOnClickListener(this);
		this.bt_test.setOnClickListener(this);
		hk_att = ((HiraKataApplication) this.getApplicationContext());
	}

	@Override
	public void onClick(View view) {
		if (view.getId() == R.id.practice) {
			if (this.rb_order.isChecked()) {
				this.hk_att.setOrder(true);
			} else if (this.rb_random.isChecked()) {
				this.hk_att.setOrder(false);
			}
			Intent intent = new Intent(FirstPage.this, PracticePage.class);
			FirstPage.this.startActivity(intent);
		} else if (view.getId() == R.id.test) {
			if (this.rb_all.isChecked()) {
				this.hk_att.setAll(true);
			} else if (this.rb_red.isChecked()) {
				this.hk_att.setAll(false);
			}
			// call test page!
			Toast.makeText(this, "TEST!!!!!", Toast.LENGTH_LONG).show();
		} else if (view.getId() == R.id.table) {
			// call table page!
			Toast.makeText(this, "Table!!!!!", Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.first_page, menu);
		return true;
	}
}
