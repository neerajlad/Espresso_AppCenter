package com.simplemobiletools.calculator.core.pages;

import android.support.test.InstrumentationRegistry;

import com.simplemobiletools.calculator.core.control.Control;
import com.simplemobiletools.calculator.core.control.IControl;
import com.simplemobiletools.calculator.core.loader.Identifier;
import com.simplemobiletools.calculator.core.loader.Loader;
import com.simplemobiletools.calculator.core.javautils.Platform;

import org.xml.sax.Parser;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

public class Page implements IPage{

    Loader loader = null;
    InputStream path = null;
    Platform platform = null;


    public Page() throws Exception {
        InputStream path = null;
//        path = this.getClass().getClassLoader().getResourceAsStream(this.getClass().getSimpleName() + ".json");
//        //path = this.getClass().getClassLoader().getResourceAsStream(this.getClass().getSimpleName() + ".json");
//        //path = "/src/androidTest/java/com/simplemobiletools/calculator/test/objectRepository/" + this.getClass().getSimpleName() + ".json";
//            URL x = getClass().getResource(this.getClass().getSimpleName() + ".json");
        //path = getClass().getClassLoader().getResourceAsStream(this.getClass().getSimpleName() + ".json");
        path = InstrumentationRegistry.getContext().getAssets().open(this.getClass().getSimpleName() + ".json");
        Platform platform = Platform.ANDROID;
        this.path = path;
        this.platform = platform;
        loader = new Loader(platform, path);
    }

    @Override
    public IControl getControl(String name) throws Exception {
        return Control.createControl(this, name, getIdentifier(name));
    }


    @Override
    public Platform getPlatform() {
        return platform;
    }

    @Override
    public Identifier getIdentifier(String name) {
        return this.loader.getIdentifierByName(name);
    }
}
