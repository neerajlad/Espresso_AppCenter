package com.simplemobiletools.calculator.core.javautils;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Profile.Section;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class JavaUtils {
	// make static all methods
	public static String getPropertyOf(String key) {
		String propValue = null;
		try {
			FileInputStream input = new FileInputStream("./config.properties");
			Properties prop = new Properties();
			prop.load(input);
			propValue = prop.getProperty(key);
		} catch (IOException e) {
			throw new NullPointerException("Unable to get the property of : " + key);
		}
		return propValue;
	}

	public static String datetime(String dateFormat) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(cal.getTime());
	}

	public static void setConfigValueByKeyAndTitle(String title, String Key, String parameterName) throws IOException {
		Ini ini = new Ini(new File(System.getProperty("user.dir") + "//Configuration//Config.ini"));
		ini.put(title, Key, parameterName);
		ini.store();
	}

	public static String getConfigValueByKey(String getTitle, String getKey)
			throws InvalidFileFormatException, IOException {
		Ini ini = new Ini(new File(System.getProperty("user.dir") + "//Configuration//Config.ini"));
		String value = ini.get(getTitle, getKey);
		if (value == null) {
			System.err.println("Enter Proper Title or Key!");
		}
		return value;
	}

	public static Map<String, Multimap<String, String>> readIni(Platform platform, String path)
			throws InvalidFileFormatException, IOException {
		String[] temp;
		Map<String, Multimap<String, String>> fileContent = new HashMap<String, Multimap<String, String>>();
		String strPlatform = platform.toString();
		Multimap<String, String> locators = null;
		Ini iniFile = new Ini(new File(path));
		for (String name : iniFile.keySet()) {
			Section section = iniFile.get(name);
			for (String key : section.keySet()) {
				locators = ArrayListMultimap.create();
				temp = key.split("~");
				if (temp[0].equalsIgnoreCase(strPlatform)) {
					locators.put(temp[1], section.get(key));
					fileContent.put(name, locators);
				}
			}
		}
		return fileContent;
	}

	public static Map<String, Multimap<String, String>> jsonReader(Platform Platform, InputStream Path) throws JSONException {
		String jsonFile = readFile(Path);
		JSONObject jsonData = new JSONObject(jsonFile);
		Map<String, Multimap<String, String>> fileContent = new HashMap<String, Multimap<String, String>>();
		String strPlatform = Platform.toString();
		String[] value;
		Multimap<String, String> locators = null;
		JSONArray keys = jsonData.names ();

		for (int i = 0; i < keys.length (); ++i) {

			String key = keys.getString (i); // Here's your key
			locators = ArrayListMultimap.create();
			JSONObject section = jsonData.getJSONObject(key);
			String string = section.getString(strPlatform);
			if (string.isEmpty()) {
			} else {
				value = string.split("=", 2);
				locators.put(value[0].trim(), value[1].trim());
				fileContent.put(key.trim(), locators);
			}
		}


//		for (String name : jsonData.keySet()) {
//			locators = ArrayListMultimap.create();
//			JSONObject section = jsonData.getJSONObject(name);
//			String string = section.getString(strPlatform);
//			if (string.isEmpty()) {
//			} else {
//				value = string.split("=", 2);
//				locators.put(value[0].trim(), value[1].trim());
//				fileContent.put(name.trim(), locators);
//			}
//		}

		return fileContent;
	}

    public static File getFileFromPath(Object obj, String fileName) {
        ClassLoader classLoader = obj.getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        return new File(resource.getPath());
    }

	public static String readFile(InputStream filename) {
		String result = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(filename));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				line = br.readLine();
			}
			result = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
//
//	private static String read(InputStream inputStream) throws IOException
//	{
//		StringWriter writer = new StringWriter();
//		IOUtils.copy(inputStream, writer, "UTF-8");
//		return writer.toString();
//	}
	public static Map<String, String> readConfig() throws Exception {
		Map<String, String> configParamters = new HashMap<String, String>();
		Ini iniFile = new Ini(new File(System.getProperty("user.dir") + "/configuration/Config.ini"));
		for (String name : iniFile.keySet()) {
			Section section = iniFile.get(name);
			for (String key : section.keySet()) {
				configParamters.put(key, section.get(key));
			}
		}
		return configParamters;
	}

	public static Map<String, Multimap<String, String>> readTestData(String Path)
			throws InvalidFileFormatException, IOException {
		Map<String, Multimap<String, String>> testDataContent = new HashMap<String, Multimap<String, String>>();
		Multimap<String, String> locators = null;
		Ini iniFile = new Ini(new File(Path));
		for (String name : iniFile.keySet()) {
			locators = ArrayListMultimap.create();
			Section section = iniFile.get(name);
			for (String key : section.keySet()) {
				String value = section.get(key);
				locators.put(key, value);
			}
			testDataContent.put(name, locators);
		}
		return testDataContent;
	}

}
