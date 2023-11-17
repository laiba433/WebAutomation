package StepDefinitions.GeneralControls;

import io.cucumber.java.en.And;
import main.java.Factory.factory;
import main.java.Logger.MyLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import main.java.Helper.ElementFinder;

import java.util.List;

public class Grid {
    @And("Get header column: {string} index of table: {string}")
    public int GetHeaderColumnIndex(String ColumnName,String TableKey) {

        int index = 0;
        WebElement table= ElementFinder.Instance().FindElement(TableKey,factory.driver);
        List<WebElement> rows =table.findElements(By.tagName("tr"));
        if (table == null)
        {
            MyLogger.log.error("Header element not found");
        }


        List<WebElement> columns = rows.get(0).findElements(By.tagName("th"));
        for (WebElement col : columns)
        {
            Actions action = new Actions(factory.driver);
            action.moveToElement(col);
            action.perform();
            if (col.getText().equals(ColumnName)) {
                MyLogger.log.info("Column header name "+col.getText()+" found");
                return index;
            }
            index++;
        }
        System.out.println(index);
        return -1;
    }

    @And("Get row values of table: {string} at index: {int}")
    public  static void GetRowValues(String tableKey, int index)
    {
        WebElement table= ElementFinder.Instance().FindElement(tableKey,factory.driver);
        List<WebElement> rows =table.findElements(By.tagName("tr"));
       for(WebElement e: rows)
        {
             List<WebElement> cells =table.findElements(By.tagName("td"));
             for(WebElement cell: cells)
              {
                 System.out.println(cell.getText());
              }

        }
    }



}
