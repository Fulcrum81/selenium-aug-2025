package table;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SomethingElseTest extends TestBase {

    @Test
    public void stupidTest() {
        TablesPage tablesPage = new TablesPage(driver);

        Assert.assertEquals(tablesPage.getPageHeaderText(), "Data Tables");
    }
}
