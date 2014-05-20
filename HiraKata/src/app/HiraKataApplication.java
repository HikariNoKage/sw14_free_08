package app;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import com.example.android.app.R;
import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

public class HiraKataApplication extends Application {

	String mode;
	int numberOfDrawables;
	int indexOfUsedKana = 0;
	boolean order;
	Vector<Integer> allPicRes;
	Vector<Integer> allPicResTable;
	Map<Integer, String> names;
	Map<Integer, Integer> picResSmall;

	public Vector<Integer> getAllPicRes() {
		return allPicRes;
	}

	public Vector<Integer> getAllPicResRand() {
		@SuppressWarnings("unchecked")
		Vector<Integer> rand = (Vector<Integer>) allPicRes.clone();
		Collections.shuffle(rand);
		return rand;
	}

	public Map<Integer, String> getNames() {
		return names;
	}

	public void setNames(Map<Integer, String> names) {
		this.names = names;
	}

	public void setAllPicRes(Vector<Integer> allPicRes) {
		this.allPicRes = allPicRes;
	}

	public void loadSmallPicsForTable() {
		int smallResource = 0;
		allPicResTable = new Vector<Integer>();
		try {
			for (int j = 1; j <= 110; j++)
				smallResource = R.drawable.class.getField("kana_small_" + j)
						.getInt(null);
			allPicResTable.add(smallResource);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressLint("UseSparseArrays")
	public boolean getAllDrawableResources() {
		allPicRes = new Vector<Integer>();
		names = new HashMap<Integer, String>();
		picResSmall = new HashMap<Integer, Integer>();

		allPicRes.clear();
		names.clear();
		picResSmall.clear();
		
		int resource = 0;
		int smallResource = 0;
		int id = 0;
		int[] noKana = { 37, 39, 47, 48, 49, 52, 53, 54, 55, 92, 94, 102, 103,
				104, 107, 108, 109, 110 };

		if (this.mode.equals("all_")) {
			resource = 1;
			numberOfDrawables = 110;
		} else if (this.mode.equals("hira_")) {
			resource = 1;
			numberOfDrawables = 55;
		} else if (this.mode.equals("kata_")) {
			resource = 56;
			numberOfDrawables = 110;
		}
		Log.w("loop", "mode: " + this.mode);
		try {
			for (int i = resource; i <= numberOfDrawables; i++) {
				Log.w("loop", "i: " + i + "nD: " + numberOfDrawables);
				if (!(contains(noKana, i))) {
					resource = R.drawable.class.getField("kana_" + i).getInt(
							null);
					smallResource = R.drawable.class
							.getField("kana_small_" + i).getInt(null);
					id = getResources().getIdentifier("kana_" + i, "string",
							getPackageName());
					picResSmall.put(resource, smallResource);
					allPicRes.add(resource);
					names.put(resource, (String) getResources().getText(id));
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public Vector<Integer> getAllPicResTable() {
		return allPicResTable;
	}

	public void setAllPicResTable(Vector<Integer> allPicResTable) {
		this.allPicResTable = allPicResTable;
	}

	public Map<Integer, Integer> getPicResSmall() {
		return picResSmall;
	}

	public void setPicResSmall(Map<Integer, Integer> picResSmall) {
		this.picResSmall = picResSmall;
	}

	public boolean contains(final int[] array, final int key) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == key) {
				return true;
			}
		}
		return false;
	}

	public boolean isOrder() {
		return order;
	}

	public void setOrder(boolean order) {
		this.order = order;
	}

	public int getNumberOfDrawables() {
		return this.numberOfDrawables;
	}

	public void setNumberOfDrawables(int number) {
		this.numberOfDrawables = number;
	}

	public String getMode() {
		return this.mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public int getIndexOfUsedKana() {
		return indexOfUsedKana;
	}

	public void setIndexOfUsedKana(int indexOfUsedKana) {
		this.indexOfUsedKana = indexOfUsedKana;
	}

}
