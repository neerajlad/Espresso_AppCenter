package com.simplemobiletools.calculator.core.control;

import java.util.List;

public interface IControl {
    void press();
    void longPress();
    void enterText(String text);
    void goBack();
    void closeKeypad();
    IControl getControl(String name);
}
