package com.example.hirakata.test;


import com.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;
import app.FirstPage;

public class FirstPageTest extends ActivityInstrumentationTestCase2<FirstPage> {

	private Solo fp_test;
	
	public FirstPageTest() {
		super(FirstPage.class);
	}

	public void setUp() throws Exception {
		this.fp_test = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testButtonTest() throws Exception
	{
		this.fp_test.clickOnButton(0);
		this.fp_test.clickOnButton(1);
		this.fp_test.clickOnButton(2);
	}
	
	public void testRadioAll() throws Exception
	{
		this.fp_test.clickOnRadioButton(0);
		this.fp_test.clickOnRadioButton(1);
		this.fp_test.clickOnRadioButton(2);
		this.fp_test.clickOnRadioButton(3);
	}
	
	public void testRadioPractice()throws Exception
	{
		this.fp_test.clickOnRadioButton(0);
		assertTrue(this.fp_test.isRadioButtonChecked(0));
		assertFalse(this.fp_test.isRadioButtonChecked(1));
		
		this.fp_test.clickOnRadioButton(1);
		assertTrue(this.fp_test.isRadioButtonChecked(1));
		assertFalse(this.fp_test.isRadioButtonChecked(0));
	}
	
	public void testRadioTest()throws Exception
	{
		this.fp_test.clickOnRadioButton(2);
		assertTrue(this.fp_test.isRadioButtonChecked(2));
		assertFalse(this.fp_test.isRadioButtonChecked(3));
		
		this.fp_test.clickOnRadioButton(3);
		assertTrue(this.fp_test.isRadioButtonChecked(3));
		assertFalse(this.fp_test.isRadioButtonChecked(2));
	}
	
	  @Override
	  public void tearDown() throws Exception {
	    this.fp_test.finishOpenedActivities();
	  }
}
