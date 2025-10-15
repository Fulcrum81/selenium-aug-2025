package table;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TablesPage {
    private By table1Locator = By.id("table1");
    private By table2Locator = By.id("table2");
    private By pageHeader = By.tagName("h3");


    private Table example1Table;
    private Table example2Table;

    private WebDriver driver;

    public TablesPage (WebDriver driver) {
        this.driver = driver;
        example1Table = new Table(driver, table1Locator);
        example2Table = new Table(driver, table2Locator);
    }

    public String getPageHeaderText() {
        return driver.findElement(pageHeader).getText();
    }

    public Table getExample1Table() {
        return example1Table;
    }

    public Table getExample2Table() {
        return example2Table;
    }

}
