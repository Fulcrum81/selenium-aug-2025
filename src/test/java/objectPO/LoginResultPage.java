package objectPO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginResultPage {

    private By errorMessage = By.cssSelector(".notice.errors");

    private WebDriver driver;

    public LoginResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean errorMessageIsVisible() {
        return driver.findElement(errorMessage).isDisplayed();
    }

    public String getErrorMessageText() {
        return driver.findElement(errorMessage).getText();
    }
}
