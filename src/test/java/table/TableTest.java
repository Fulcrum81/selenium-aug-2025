package table;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TableTest extends TestBase {

    @Test
    public void loginWithDisabledAccountTest() {
        TablesPage tablesPage = new TablesPage(driver);

        Assert.assertEquals(tablesPage.getExample1Table().getCellTextByIndex(1, 4),
                "$50.00");
    }
}
