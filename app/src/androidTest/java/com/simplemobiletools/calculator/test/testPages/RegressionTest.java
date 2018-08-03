package com.simplemobiletools.calculator.test.testPages;

import org.junit.Rule;
import org.junit.Test;
import com.microsoft.appcenter.espresso.Factory;
import com.microsoft.appcenter.espresso.ReportHelper;

public class RegressionTest extends  BaseTest{

    @Test
    public void Test_01() throws  Exception{
        reportHelper.label("TC_01");
        calcPage.add().minus().Mul();
    }

    @Test
    public void Test_02() throws  Exception{
        reportHelper.label("TC_02");
        calcPage.add().minus().Mul();
    }

}


