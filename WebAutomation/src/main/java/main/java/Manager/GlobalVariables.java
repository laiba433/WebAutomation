package main.java.Manager;

import main.java.Helper.JsonHelper;
import main.java.Logger.MyLogger;
import org.json.simple.JSONObject;
import java.io.FileReader;
import java.io.IOException;

public class GlobalVariables
{

    public static String locatorFile;
    public static String Success;
    public static String Fail;
    private static String GlobalVariablesFile= System.getProperty("user.dir")+"/src/main/resources/GlobalValriables.json";
    public static GlobalVariables Instance= null;



    public static void SetGlobalVariables()
    {
        try
        {
            FileReader reader= JsonHelper.Instance().GetJsonFile(GlobalVariablesFile);
            JSONObject obj= JsonHelper.Instance().ParseJson(reader);
            locatorFile= obj.get("locatorFile").toString();
            Success= obj.get("Success").toString();
            Fail= obj.get("Fail").toString();

            try
            {
                reader.close();
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }

            MyLogger.log.info("Global Variables have been set");

        }

        catch(Exception ex)
        {
            MyLogger.log.error("Unable to set the values of Global Variable");
            MyLogger.log.error(ex);
        }

    }
}
