package app;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import com.example.android.app.R;
import android.annotation.SuppressLint;
import android.app.Application;

public class HiraKataApplication extends Application {

	String mode = "hira_"; // application mode
	int numberOfDrawables = 0; // 3 for testing should be 100
	int indexOfUsedKana = 0; // index of the actual kana
	boolean order = true; // show kana in order
	Vector<Integer> allPicRes;
	Map<Integer, String> names;

	public Vector<Integer> getAllPicRes() {
		return allPicRes;
	}
	
	public Vector<Integer> getAllPicResRand() {
		Vector<Integer> rand = allPicRes;
		Collections.shuffle(allPicRes);
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

	@SuppressLint("UseSparseArrays")
	public boolean getAllDrawableResources() {
		allPicRes = new Vector<Integer>();
		names = new HashMap<Integer, String>();

		int resource = 0;
		int id = 0;
		int[] noKana = { 12, 13, 15, 23, 24, 34, 35, 37, 45 };
		String mode = this.mode;

		if (mode.equals("all_")) {
			resource = 1;
			numberOfDrawables = 110;
		} else if (mode.equals("hira_")) {
			resource = 1;
			numberOfDrawables = 55;
		} else if (mode.equals("kata_")) {
			resource = 56;
			for (int i = 0; i < noKana.length; i++) {
				noKana[i] = noKana[i] + 56;
			}
			numberOfDrawables = 110;
		}
		try {
			for (int i = resource; i <= numberOfDrawables; i++) {
				if (!(contains(noKana, i))) {
					resource = R.drawable.class.getField("kana_" + i).getInt(
							null);
					id = getResources().getIdentifier("kana_" + i, "string",
							getPackageName());
					allPicRes.add(resource);
					names.put(resource,
							(String) getResources().getText(id));
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;
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
