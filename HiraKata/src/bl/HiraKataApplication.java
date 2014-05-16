package bl;

import java.util.Vector;

import com.example.hirakata.R;

import android.app.Application;

public class HiraKataApplication extends Application {

	String mode = "hira_"; // application mode
	int numberOfDrawables = 0; // 3 for testing should be 100
	int indexOfUsedKana = 0; // index of the actual kana
	boolean order = true; // show kana in order
	boolean all = true; // show all kana

	public Vector<Integer> getAllDrawableResources() {

		Vector<Integer> allPicRes = new Vector<Integer>();

		int resource = 0;
		int[] noKana = { 12, 13, 15, 23, 24, 34, 35, 37, 45 }; // +46 Katakana
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
				noKana[i] = noKana[i] + 46;
			}
			numberOfDrawables = 110;
		}
		try {
			for (int i = resource; i <= numberOfDrawables; i++) {
				if (!(contains(noKana, i))) {
					resource = R.drawable.class.getField("kana_" + i).getInt(
							null);
					allPicRes.add(resource);
				}
			}
		} catch (Exception e) {
			return null;
		}
		return allPicRes;
	}

	public boolean contains(final int[] array, final int key) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == key) {
				return true;
			}
		}
		return false;
	}
	
	public Vector<Integer> antiSort() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isOrder() {
		return order;
	}

	public void setOrder(boolean order) {
		this.order = order;
	}

	public boolean isAll() {
		return all;
	}

	public void setAll(boolean all) {
		this.all = all;
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
