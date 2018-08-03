package com.simplemobiletools.calculator.test.testPages;

import org.junit.Test;

public class SanityTest extends  BaseTest{

    @Test
    public void Sanity_01() throws  Exception{
        reportHelper.label("Sanity_01");
        calcPage.add().minus().Mul();
    }

    @Test
    public void Sanity_02() throws  Exception{
        reportHelper.label("Sanity_02");
        calcPage.add().minus().Mul();
    }

}


