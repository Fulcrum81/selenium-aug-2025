package pageFactoryPO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginResultPage {

    @FindBy(css = ".notice.errors")
    private WebElement errorMessage;

    public boolean errorMessageIsVisible() {
        return errorMessage.isDisplayed();
    }

    public String getErrorMessageText() {
        return errorMessage.getText();
    }
}
