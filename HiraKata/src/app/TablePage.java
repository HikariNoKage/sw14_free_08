package app;

import java.util.ArrayList;
import java.util.Vector;

import com.example.android.app.R;
import com.example.android.app.R.layout;
import com.example.android.app.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;

public class TablePage extends Activity implements OnClickListener {

	private GridView grid;
	private ArrayList<Bitmap> bit = new ArrayList<Bitmap>();
	private GridViewAdapter gridAdi;
	private HiraKataApplication attri;
	private Vector<Integer> res;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_table_page);

		attri = ((HiraKataApplication) this.getApplicationContext());
		res = attri.getAllPicResTable();
		
		Log.w("Test", res.size()+"");
		for(int i = 0; i< res.size(); i++)
		{
			Bitmap icon = BitmapFactory.decodeResource(this.getResources(),res.elementAt(i));
			bit.add(icon);
		}
		
		grid = (GridView) findViewById(R.id.gridView1);
		gridAdi = new GridViewAdapter(this, R.layout.activity_table_page, bit);
		grid.setAdapter(gridAdi);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.table_page, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
