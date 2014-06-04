package app;

import java.util.ArrayList;
import java.util.Vector;

import com.example.android.app.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.GridView;

public class TablePage extends Activity {
	private GridView gridView;
	private ArrayList<Item> gridArray = new ArrayList<Item>();
	private GridViewAdapter customGridAdapter;
	private HiraKataApplication application;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_table_page);

		application = ((HiraKataApplication) this.getApplicationContext());
		Vector<Integer> pics = loadSmallPicsForTable();

		// Bitmap homeIcon = BitmapFactory.decodeResource(this.getResources(),
		// R.drawable.ic_launcher);
		// gridArray.add(new Item(homeIcon,"Home"));

		Log.w("size", "size " + pics.size());
		for (int i = 0; i < pics.size(); i++) {
			Log.w("Pics", "id: " + pics.elementAt(i));
			Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
					pics.elementAt(i));
			gridArray.add(new Item(icon, "testi"));
		}

		gridView = (GridView) findViewById(R.id.gridView1);
		customGridAdapter = new GridViewAdapter(this, R.layout.row_grid,
				gridArray);
		gridView.setAdapter(customGridAdapter);
	}

	public Vector<Integer> loadSmallPicsForTable() {
		int smallResource = 0;
		Vector<Integer> allPicResTable = new Vector<Integer>();
		try {
			for (int j = 1; j <= 110; j++) {
				smallResource = R.drawable.class.getField("kana_small_" + j)
						.getInt(null);
				Log.w("loop", "j: " + j + "id: " + smallResource);
				allPicResTable.add(smallResource);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allPicResTable;
	}

}
