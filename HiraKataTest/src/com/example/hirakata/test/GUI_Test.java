package com.example.hirakata.test;

import gui.StartPage;
import android.test.ActivityInstrumentationTestCase2;
import com.robotium.solo.Solo;

public class GUI_Test extends ActivityInstrumentationTestCase2<StartPage> {

	private Solo gui_solo;

	public GUI_Test() {
		super(StartPage.class);
	}

	public void setUp() throws Exception {
		this.gui_solo = new Solo(getInstrumentation(), getActivity());
	}

	public void testButtonTest() throws Exception {
		this.gui_solo.clickOnButton("All");
		this.gui_solo.goBack();
		this.gui_solo.goBackToActivity("StartPage");
		
		this.gui_solo.clickOnButton("Hiragana");
		this.gui_solo.goBack();
		this.gui_solo.goBackToActivity("StartPage");
		
		this.gui_solo.clickOnButton("Katakana");
		this.gui_solo.goBack();
		this.gui_solo.goBackToActivity("StartPage");
	}

	@Override
	protected void tearDown() throws Exception {
		this.gui_solo.finishOpenedActivities();
	}
}