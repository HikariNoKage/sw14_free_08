package bl;

import android.app.Application;

public class HiraKataApplication extends Application{

	String mode = "fail";

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
	
	
}
