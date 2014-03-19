package com.example.hirakata.test;

import com.robotium.solo.Solo;
import gui.PracticePage;
import android.test.ActivityInstrumentationTestCase2;


public class PracticePageTest extends ActivityInstrumentationTestCase2<PracticePage> {
	
	private Solo pp_test;
	
	public PracticePageTest()
	{
		super(PracticePage.class);
	}
	
	public void setUp() throws Exception {
		this.pp_test = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testButtonTest() throws Exception
	{
		this.pp_test.clickOnImageButton(0); //soundButton
		this.pp_test.clickOnImageButton(1); //backButton
		this.pp_test.clickOnImageButton(2); //drawButton
		this.pp_test.clickOnImageButton(3); //deleteButton
		this.pp_test.clickOnImageButton(4); //nextButton		
	}
	
	public void testDrawing() throws Exception
	{
		this.pp_test.clickOnImageButton(2);
		this.pp_test.drag(150.0f, 210.0f, 200.0f, 250.0f, 3);
	}
	
	
	
	
	@Override
	protected void tearDown() throws Exception {
		this.pp_test.finishOpenedActivities();
	}
}
