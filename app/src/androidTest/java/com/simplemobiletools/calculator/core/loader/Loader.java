package com.simplemobiletools.calculator.core.loader;

import com.google.common.collect.Multimap;
import com.simplemobiletools.calculator.core.javautils.JavaUtils;
import com.simplemobiletools.calculator.core.javautils.Platform;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Loader {
    Identifier identifier = null;
    String key;
    String value;
    Map<String,Multimap<String,String>> fileContent = new HashMap<String, Multimap<String, String>>();

    public Loader(Platform platform, InputStream path) throws Exception {
        fileContent =  JavaUtils.jsonReader(platform,path);
    }

    public Map<String, Multimap<String, String>> getFileContent() {
        return fileContent;
    }


    public Identifier getIdentifierByName(String name){
        Multimap<String, String> objectName = fileContent.get(name);
        for(String k : objectName.keySet())
        {
            key = k;
            value = objectName.get(k).toString();
            int len = value.length();
            value = value.substring(1, len-1);
        }
        return new Identifier(key ,value);

    }
}
