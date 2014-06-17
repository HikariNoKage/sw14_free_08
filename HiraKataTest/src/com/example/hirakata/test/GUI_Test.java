package com.example.hirakata.test;

import android.test.ActivityInstrumentationTestCase2;
import app.StartPage;
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

	public void testFirstPageAll() throws Exception {
		this.gui_solo.clickOnButton("All");
		assertTrue(this.gui_solo.searchText("FirstPage"));
		findButtonText();
		this.gui_solo.goBack();
		assertTrue(this.gui_solo.searchText("HiraKata"));
	}

	// Katakana Tests:
	public void testFirstPageKatakana() throws Exception {
		this.gui_solo.clickOnButton("Katakana");
		assertTrue(this.gui_solo.searchText("FirstPage"));
		findButtonText();
		this.gui_solo.goBack();
		assertTrue(this.gui_solo.searchText("HiraKata"));
	}

	public void testFirstPageHiragana() throws Exception {
		this.gui_solo.clickOnButton("Hiragana");
		assertTrue(this.gui_solo.searchText("FirstPage"));
		findButtonText();
		this.gui_solo.goBack();
		assertTrue(this.gui_solo.searchText("HiraKata"));
	}

	public void testKatakanaPracticePageCompleteOrder() {
		this.gui_solo.clickOnButton("Katakana");
		assertTrue(this.gui_solo.searchText("FirstPage"));
		this.gui_solo.clickOnButton("Practice in Order");
		assertTrue(this.gui_solo.searchText("PracticePage"));
		this.gui_solo.clickOnImageButton(1);
		assertTrue(this.gui_solo.searchText("No previous character!"));
		runToEnd(46);
		this.gui_solo.clickOnImageButton(4);
		assertTrue(this.gui_solo.searchText("No next character!"));
	}

	public void testKataganaPracticePageCompleteRandom() {
		this.gui_solo.clickOnButton("Katakana");
		assertTrue(this.gui_solo.searchText("FirstPage"));
		this.gui_solo.clickOnButton("Practice in random Order");
		assertTrue(this.gui_solo.searchText("PracticePage"));
		this.gui_solo.clickOnImageButton(1);
		assertTrue(this.gui_solo.searchText("No previous character!"));
		runToEnd(45);
		this.gui_solo.goBack();
		assertTrue(this.gui_solo.searchText("FirstPage"));
	}

	public void testKatakanaTablePage() {
		this.gui_solo.clickOnButton("Katakana");
		assertTrue(this.gui_solo.searchText("FirstPage"));
		this.gui_solo.clickOnButton("Show Kana Table");
		assertTrue(this.gui_solo.searchText("TablePage"));
		assertTrue(this.gui_solo.searchText("A"));
		assertTrue(this.gui_solo.searchText("A"));
		this.gui_solo.clickOnImage(2);
		assertTrue(this.gui_solo.searchText("PracticePage"));
	}

	public void testKatakanaTestPage() {
		this.gui_solo.clickOnButton("Katakana");
		assertTrue(this.gui_solo.searchText("FirstPage"));
		this.gui_solo.clickOnButton("Make a Test");
		assertTrue(this.gui_solo.searchText("TestPage"));

		for (int i = 0; i < 46; i++) {
			this.gui_solo.drag(150.0f, 210.0f, 200.0f, 250.0f, 3);
			this.gui_solo.clickOnButton("Solve");

			if (i < 15) {
				this.gui_solo.clickOnButton("Bad");
			} else if (i < 30) {
				this.gui_solo.clickOnButton("OK");
			} else {
				this.gui_solo.clickOnButton("Good");
			}
		}
		assertTrue(this.gui_solo.searchText("Your result:"));
		this.gui_solo.clickOnButton("OK");
	}

	// Hiragana Tests:
	public void testHiraganaPracticePageCompleteOrder() {
		this.gui_solo.clickOnButton("Hiragana");
		assertTrue(this.gui_solo.searchText("FirstPage"));
		this.gui_solo.clickOnButton("Practice in Order");
		assertTrue(this.gui_solo.searchText("PracticePage"));
		this.gui_solo.clickOnImageButton(1);
		assertTrue(this.gui_solo.searchText("No previous character!"));
		runToEnd(46);
		this.gui_solo.clickOnImageButton(4);
		assertTrue(this.gui_solo.searchText("No next character!"));
	}

	public void testHiraganaPracticePageCompleteRandom() {
		this.gui_solo.clickOnButton("Hiragana");
		assertTrue(this.gui_solo.searchText("FirstPage"));
		this.gui_solo.clickOnButton("Practice in random Order");
		assertTrue(this.gui_solo.searchText("PracticePage"));
		this.gui_solo.clickOnImageButton(1);
		assertTrue(this.gui_solo.searchText("No previous character!"));
		runToEnd(45);
		this.gui_solo.goBack();
		assertTrue(this.gui_solo.searchText("FirstPage"));
	}

	public void testHiraganaTablePage() {
		this.gui_solo.clickOnButton("Hiragana");
		assertTrue(this.gui_solo.searchText("FirstPage"));
		this.gui_solo.clickOnButton("Show Kana Table");
		assertTrue(this.gui_solo.searchText("TablePage"));
		assertTrue(this.gui_solo.searchText("A"));
		assertTrue(this.gui_solo.searchText("A"));
		this.gui_solo.clickOnImage(2);
		assertTrue(this.gui_solo.searchText("PracticePage"));
	}

	public void testHiraganaTestPage() {
		this.gui_solo.clickOnButton("Hiragana");
		assertTrue(this.gui_solo.searchText("FirstPage"));
		this.gui_solo.clickOnButton("Make a Test");
		assertTrue(this.gui_solo.searchText("TestPage"));

		for (int i = 0; i < 46; i++) {
			this.gui_solo.drag(150.0f, 210.0f, 200.0f, 250.0f, 3);
			this.gui_solo.clickOnButton("Solve");

			if (i < 15) {
				this.gui_solo.clickOnButton("Bad");
			} else if (i < 30) {
				this.gui_solo.clickOnButton("OK");
			} else {
				this.gui_solo.clickOnButton("Good");
			}
		}
		assertTrue(this.gui_solo.searchText("Your result:"));
		this.gui_solo.clickOnButton("OK");
	}

	//All Tests:
	
	public void testAllPracticePageCompleteOrder() {
		this.gui_solo.clickOnButton("All");
		assertTrue(this.gui_solo.searchText("FirstPage"));
		this.gui_solo.clickOnButton("Practice in Order");
		assertTrue(this.gui_solo.searchText("PracticePage"));
		this.gui_solo.clickOnImageButton(1);
		assertTrue(this.gui_solo.searchText("No previous character!"));
		runToEnd(92);
		this.gui_solo.clickOnImageButton(4);
		assertTrue(this.gui_solo.searchText("No next character!"));
	}

	public void testAllPracticePageCompleteRandom() {
		this.gui_solo.clickOnButton("All");
		assertTrue(this.gui_solo.searchText("FirstPage"));
		this.gui_solo.clickOnButton("Practice in random Order");
		assertTrue(this.gui_solo.searchText("PracticePage"));
		this.gui_solo.clickOnImageButton(1);
		assertTrue(this.gui_solo.searchText("No previous character!"));
		runToEnd(91);
		this.gui_solo.goBack();
		assertTrue(this.gui_solo.searchText("FirstPage"));
	}

	public void testAllTablePage() {
		this.gui_solo.clickOnButton("All");
		assertTrue(this.gui_solo.searchText("FirstPage"));
		this.gui_solo.clickOnButton("Show Kana Table");
		assertTrue(this.gui_solo.searchText("TablePage"));
		assertTrue(this.gui_solo.searchText("A"));
		assertTrue(this.gui_solo.searchText("A"));
		this.gui_solo.clickOnImage(2);
		assertTrue(this.gui_solo.searchText("PracticePage"));
	}

	public void testAllTestPage() {
		this.gui_solo.clickOnButton("All");
		assertTrue(this.gui_solo.searchText("FirstPage"));
		this.gui_solo.clickOnButton("Make a Test");
		assertTrue(this.gui_solo.searchText("TestPage"));

		for (int i = 0; i < 92; i++) {
			this.gui_solo.drag(150.0f, 210.0f, 200.0f, 250.0f, 3);
			this.gui_solo.clickOnButton("Solve");

			if (i < 35) {
				this.gui_solo.clickOnButton("Bad");
			} else if (i < 55) {
				this.gui_solo.clickOnButton("OK");
			} else {
				this.gui_solo.clickOnButton("Good");
			}
		}
		assertTrue(this.gui_solo.searchText("Your result:"));
		this.gui_solo.clickOnButton("OK");
	}
	
	//Help Methods:
	
	public void drawAndPlay() {
		this.gui_solo.clickOnImageButton(2);
		this.gui_solo.drag(150.0f, 210.0f, 200.0f, 250.0f, 3);
		this.gui_solo.clickOnImageButton(3);
		this.gui_solo.clickOnImageButton(0);
	}

	public void runToEnd(int end) {
		for (int i = 0; i < end; i++) {
			drawAndPlay();
			this.gui_solo.clickOnImageButton(4);
		}
	}

	public void findButtonText() {
		assertTrue(this.gui_solo.searchText("Practice in Order"));
		assertTrue(this.gui_solo.searchText("Practice in random Order"));
		assertTrue(this.gui_solo.searchText("Make a Test"));
		assertTrue(this.gui_solo.searchText("Show Kana Table"));
	}

	@Override
	protected void tearDown() throws Exception {
		this.gui_solo.finishOpenedActivities();
	}
}