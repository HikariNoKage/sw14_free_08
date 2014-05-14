package bl;

import android.app.Application;

public class HiraKataApplication extends Application {

	String mode = "fail";		//application mode
	int numberOfDrawables = 3;  //3 for testing should be 100
	int indexOfUsedKana = 0;    //index of the actual kana
	boolean order = true;		//show kana in order		
	boolean all = true;			//show all kana

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
