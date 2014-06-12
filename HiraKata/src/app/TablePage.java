package app;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import com.example.android.app.R;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class TablePage extends Activity implements OnItemClickListener {
	private GridView gridView;
	private ArrayList<Item> gridArray = new ArrayList<Item>();
	private GridViewAdapter customGridAdapter;
	private HiraKataApplication app;

	// private int[] id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_table_page);

		// int[] id = new int[110];
		int smallResource = 0;
		int[] noKana = { 37, 39, 47, 48, 49, 52, 53, 54, 55, 92, 94, 102, 103,
				104, 107, 108, 109, 110 };
		String name = "";

		try {
			for (int j = 1; j <= 110; j++) {

				smallResource = R.drawable.class.getField("kana_small_" + j)
						.getInt(null);
				Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
						smallResource);
				if (contains(noKana, j)) {
					name = "";
				} else {

					name = (String) getResources().getText(
							getResources().getIdentifier("kana_" + j, "string",
									getPackageName()));
					String[] names = name.split(":\n ");
					name = names[1];
				}
				gridArray.add(new Item(icon, "   " + name));
				// id[j - 1] = smallResource;
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

	public static boolean contains(final int[] array, final int key) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == key) {
				return true;
			}
		}
		return false;
	}

	public static Integer getKeyByValue(Map<Integer, Integer> map, int value) {
		for (Entry<Integer, Integer> entry : map.entrySet()) {
			if (value == (entry.getValue())) {
				return entry.getKey();
			}
		}
		return null;
	}

	// @Override
	// public void onClick(View v) {
	//
	// //Log.w("id", id[0]+"");
	// Log.w("click", v.getId()+"");
	//
	// if (contains(id, v.getId())) {
	// app = ((HiraKataApplication) this.getApplicationContext());
	// Map<Integer, Integer> picResSmall = app.getPicResSmall();
	// int key = getKeyByValue(picResSmall, v.getId());
	// app.setIndexOfUsedKana(key);
	// Intent intent = new Intent(TablePage.this, PracticePage.class);
	// TablePage.this.startActivity(intent);
	// }

	@Override
	public void onItemClick(AdapterView<?> parent, View v,
            int position, long id) {
		// TODO Auto-generated method stub
		Log.w("click", id + "");
	}
}
