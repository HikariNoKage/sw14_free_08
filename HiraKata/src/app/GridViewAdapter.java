package app;

import java.util.ArrayList;
import java.util.List;

import com.example.android.app.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class GridViewAdapter extends ArrayAdapter<Bitmap> {
	private Context con;
	private int resourceId;
	private ArrayList<Bitmap> pics = new ArrayList<Bitmap>();

	public GridViewAdapter(Context context, int resource, List<Bitmap> objects) {
		super(context, resource, objects);
		this.con = context;
		this.resourceId = resource;
		this.pics = (ArrayList<Bitmap>) objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;

		if (row == null) {
			LayoutInflater inflat = ((Activity) con).getLayoutInflater();
			row = inflat.inflate(this.resourceId, parent, false);
			row.setTag((ImageView) row.findViewById(R.id.iconSmall));
			return row;
		} else {
			return (View) row.getTag();
		}
	}

}
