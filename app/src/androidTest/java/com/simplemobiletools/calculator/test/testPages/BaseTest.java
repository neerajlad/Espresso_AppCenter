package com.simplemobiletools.calculator.test.testPages;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.microsoft.appcenter.espresso.Factory;
import com.microsoft.appcenter.espresso.ReportHelper;
import com.simplemobiletools.calculator.activities.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class BaseTest {
    @Rule
    public ReportHelper reportHelper = Factory.getReportHelper();

    @Rule
    public final ActivityTestRule<MainActivity> activity = new ActivityTestRule<>(MainActivity.class);

    public CalculatorPage calcPage;
    @Before
    public void setUp() throws Exception {
        reportHelper.label("Start App");
        calcPage = new CalculatorPage();
    }

    @After
    public void TearDown(){
        reportHelper.label("Stopping App");
    }
}
