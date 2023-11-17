package main.java.Helper;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonHelper {

    public static JsonHelper Instance= null;

    public static JsonHelper Instance()
    {
        {
            if(Instance== null)
            {
                Instance = new JsonHelper();

            }
            return Instance;
        }
    }
    public FileReader GetJsonFile(String FileName){
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(FileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return fileReader;
    }
    public JSONArray GetJsonArray(JSONObject obj, String Key)
    {
        JSONArray ja = (JSONArray) obj.get(Key);
        return ja;
    }

public JSONObject ParseJson(FileReader reader)
{
    JSONParser jsonParser = new JSONParser();
    JSONObject obj = null;

    try
    {
        obj = (JSONObject) jsonParser.parse(reader);
    }

    catch (Exception e)
    {
        System.out.println("issue");      
       System.out.println(e);
       System.out.println("issue");      
    }


    return obj;
}

}

