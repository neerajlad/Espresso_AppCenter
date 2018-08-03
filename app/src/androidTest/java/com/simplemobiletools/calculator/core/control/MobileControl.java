package com.simplemobiletools.calculator.core.control;

import android.support.test.espresso.ViewInteraction;
import com.simplemobiletools.calculator.core.pages.IPage;

public class MobileControl extends Control {
    private ViewInteraction element;

    public MobileControl(IPage page, ViewInteraction element){
        super(page, element);
        this.element = element;
    }

}
