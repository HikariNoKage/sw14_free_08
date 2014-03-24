package bl;

import android.app.Application;

public class HiraKataApplication extends Application {

	String mode = "fail";
	int numberOfDrawables = 4; //4 for testing should be 100
	

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

}
