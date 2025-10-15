package htmlelements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class RegionSelectorTest {
    WebDriver driver;

    @Test
    public void changeRegionSettingsTest() {

        driver = new ChromeDriver();

        LoginPage loginPage = new LoginPage(driver);
        LoginResultPage loginResultPage = new LoginResultPage(driver);

        loginPage.regionElement.clickChangeLink();
        loginResultPage.regionElement.getCountryName();

        RegionElement regionElement = new RegionElement();

        regionElement.clickChangeLink();


    }
}
