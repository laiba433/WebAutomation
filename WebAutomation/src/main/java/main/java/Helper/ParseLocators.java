package main.java.Helper;

import main.java.Manager.GlobalVariables;
import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ParseLocators 
{
	  public String key;
	  public String locator;

	 public String value;
	 public ParseLocators(String locator, String value)
	 {
	     this.locator=locator;
	     this.value=value;
	 }
	 public static JSONObject GetLocatorObject(String key) 
	 {
		 FileReader fileReader = null;
	       try 
	       {
	           fileReader = new FileReader(System.getProperty("user.dir") + GlobalVariables.locatorFile);
	       }
	       catch (FileNotFoundException e) 
	       {
	           System.out.println(" Key is not found in JSON file");
	           throw new RuntimeException(e);
	       }
	        JSONObject jsonObject= JsonHelper.Instance().ParseJson(fileReader);
	        JSONObject obj = (JSONObject) jsonObject.get(key);
	        try {
				fileReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return obj;
	    }

	    public static ParseLocators GetValues(String Key)
	    {
	        JSONObject jsonObject = null;
	        try
	        {
	            jsonObject = GetLocatorObject(Key);
	        }
	        catch (Exception e)
	        {
	           System.out.println(Key+" Key is not found in JSON file");
	        }

	        ParseLocators KeyObject = null;
	        String locator = jsonObject.get("locator").toString();
	        String value = jsonObject.get("value").toString();
	        KeyObject = new ParseLocators(locator, value);
	        return KeyObject;
	    }
}
