package table;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Table {
    private WebElement table;
    private WebDriver driver;

    public Table(WebDriver driver, By tableLocator) {
        this.driver = driver;

        table = driver.findElement(tableLocator);
    }

    public Table(WebDriver driver, WebElement table) {
        this.driver = driver;
        this.table = table;
    }

    public int getNumberOfRows() {
        List<WebElement> allRows = table.findElements(By.tagName("tr"));
        return allRows.size();
    }

    public int getNumberOfColumns() {
        List<WebElement> allColumns = table.findElements(By.tagName("th"));
        return allColumns.size();
    }

    public String getCellTextByIndex(int row, int column) {
        List<WebElement> allRows = table.findElements(By.tagName("tr"));

        List<WebElement> allColumns = ((WebElement)(allRows.toArray()[row])).findElements(By.tagName("td"));
        return allColumns.toArray(new WebElement[0])[column - 1].getText();
    }

}
