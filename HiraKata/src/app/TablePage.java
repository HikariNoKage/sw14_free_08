package app;

import java.util.ArrayList;
import com.example.android.app.R;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.GridView;

public class TablePage extends Activity {
	private GridView gridView;
	private ArrayList<Item> gridArray = new ArrayList<Item>();
	private GridViewAdapter customGridAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_table_page);

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
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		gridView = (GridView) findViewById(R.id.gridView1);
		customGridAdapter = new GridViewAdapter(this, R.layout.row_grid,
				gridArray);
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
}
