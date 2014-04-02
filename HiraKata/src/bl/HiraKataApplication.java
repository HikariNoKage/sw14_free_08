package bl;

import android.app.Application;

public class HiraKataApplication extends Application {

	String mode = "fail";
	int numberOfDrawables = 3; //3 for testing should be 100
	int indexOfUsedKana = 0;

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
