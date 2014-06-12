package app;

import java.util.ArrayList;
import com.example.android.app.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class TestPage extends Activity implements OnItemClickListener {
	private GridView gridView;
	private ArrayList<Item> gridArray = new ArrayList<Item>();
	private GridViewAdapter customGridAdapter;
	private HiraKataApplication app;
	private HiraKataApplication hk_mode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_table_page);

		int smallResource = 0;
		int[] noKana = { 37, 39, 47, 48, 49, 52, 53, 54, 55, 92, 94, 102, 103,
				104, 107, 108, 109, 110 };
		String name = "";
		hk_mode = ((HiraKataApplication) this.getApplicationContext());
		int numberOfDrawables = hk_mode.getNumberOfDrawables();
		int j = 0;
		try {

			if (hk_mode.getMode().equals("all_")) {
				j = 1;
				numberOfDrawables = 110;
			} else if (hk_mode.getMode().equals("hira_")) {
				j = 1;
				numberOfDrawables = 55;
			} else if (hk_mode.getMode().equals("kata_")) {
				j = 56;
				numberOfDrawables = 110;
			}

			for (; j < numberOfDrawables; j++) {

				smallResource = R.drawable.class.getField("kana_small_" + j)
						.getInt(null);
				Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
						smallResource);
				if (hk_mode.contains(noKana, j)) {
					name = "";
				} else {

					name = (String) getResources().getText(
							getResources().getIdentifier("kana_" + j, "string",
									getPackageName()));
					String[] names = name.split(":\n ");
					name = names[1];
				}
				gridArray.add(new Item(icon, "   " + name));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		gridView = (GridView) findViewById(R.id.gridView1);
		customGridAdapter = new GridViewAdapter(this, R.layout.row_grid,
				gridArray);
		gridView.setOnItemClickListener((OnItemClickListener) this);
		gridView.setAdapter(customGridAdapter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget
	 * .AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		// TODO Auto-generated method stub
		// Log.w("click", id + "");
		int[] noKana = { 36, 38, 46, 47, 48, 51, 52, 53, 54, 91, 93, 101, 102,
				103, 106, 107, 108, 109 };
		
		app = ((HiraKataApplication) this.getApplicationContext());

		Log.w("index", (int)id+"");
		
		if (!app.contains(noKana, (int) id)) {
			Log.w("index", (int)id+"");
			app.setIndexOfUsedKana((int) id);
			Intent intent = new Intent(TestPage.this, PracticePage.class);
			TestPage.this.startActivity(intent);
		}
	}
}
