package app;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import com.example.android.app.R;
import android.annotation.SuppressLint;
import android.app.Application;

public class HiraKataApplication extends Application {

	private String mode;
	private int numberOfDrawables;
	private int indexOfUsedKana = 0;
	private boolean order = true;
	private Vector<Integer> allPicRes;
	private Map<Integer, String> names;
	private Map<Integer, Integer> picResSmall;
	private Map<Integer, Integer> sounds;

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

	public Map<Integer, Integer> getSounds() {
		return sounds;
	}

	public void setSounds(Map<Integer, Integer> sounds) {
		this.sounds = sounds;
	}

	public void initResources() {
		try {
			getAllDrawableResources();
			fillSounds();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int[] loadSounds() {
		int[] noKana = { 37, 39, 47, 48, 49 };
		int[] sounds = new int[46];

		int soundId = 0;
		try {
			int j = 0;
			for (int i = 1; i <= 51; i++) {
				if (!(contains(noKana, i))) {
					soundId = R.raw.class.getField("kanasound_" + i).getInt(
							null);
					sounds[j] = soundId;
					// Log.w("sounds", "sounds: "+sounds[j]+" j "+j);
					j++;
				}
			}
			return sounds;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressLint("UseSparseArrays")
	public void fillSounds() {
		sounds = new HashMap<Integer, Integer>();
		int[] sound = loadSounds();
		int k = 0;
		if (allPicRes.size() > 0) {
			for (int j = 0; j < allPicRes.size(); j++) {
				// Log.w("int", "j: " + j);
				if (k == 47)
					k = 0;
				sounds.put(allPicRes.elementAt(j), sound[k]);
				// Log.w("out", "sounds: " +
				// sounds.get(allPicRes.elementAt(j)));
				k++;
			}
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
		// Log.w("loop", "mode: " + this.mode);
		try {
			for (int i = resource; i <= numberOfDrawables; i++) {
				// Log.w("loop", "i: " + i + "nD: " + numberOfDrawables);
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
				else
				{
					allPicRes.add(null);
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;
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
