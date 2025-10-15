package fluentPO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginResultPage {

    private By errorMessage = By.cssSelector(".notice.errors");

    private WebDriver driver;

    public LoginResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginResultPage validateErrorMessageIsVisible() {
        Assert.assertTrue(driver.findElement(errorMessage).isDisplayed());
        return this;
    }

    public LoginResultPage validateErrorMessageText(String expectedText) {
        Assert.assertEquals(driver.findElement(errorMessage).getText(), expectedText);
        return this;
    }
}
