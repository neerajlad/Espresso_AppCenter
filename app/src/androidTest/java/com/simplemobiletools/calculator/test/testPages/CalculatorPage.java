package com.simplemobiletools.calculator.test.testPages;

import android.support.test.InstrumentationRegistry;

import com.simplemobiletools.calculator.core.pages.Page;
import com.simplemobiletools.calculator.helpers.Calculator;

import java.net.CacheRequest;
import java.util.Map;

public class CalculatorPage extends Page {

    public CalculatorPage() throws Exception {
        super();
    }

    public CalculatorPage add() throws Exception {
        getControl("btn2").press();
        getControl("btnPlus").press();
        getControl("btn4").press();
        getControl("btnEqual").press();
        return new CalculatorPage();
    }

    public CalculatorPage minus() throws Exception {
        getControl("btn5").press();
        getControl("btnMinus").press();
        getControl("btn2").press();
        getControl("btnEqual").press();
        return new CalculatorPage();
    }

    public CalculatorPage Mul() throws Exception {
        getControl("btn5").press();
        getControl("btnMultiply").press();
        getControl("btn7").press();
        getControl("btnEqual").press();
        return new CalculatorPage();
    }
}
