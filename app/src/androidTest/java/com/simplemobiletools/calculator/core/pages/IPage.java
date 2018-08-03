package com.simplemobiletools.calculator.core.pages;

import com.simplemobiletools.calculator.core.control.IControl;
import com.simplemobiletools.calculator.core.loader.Identifier;
import com.simplemobiletools.calculator.core.javautils.Platform;

public interface IPage {
    IControl getControl(String name) throws Exception;
    Platform getPlatform();
    Identifier getIdentifier(String name);
}
