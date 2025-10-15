package staticPO;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginResultPage {

    private static By errorMessage = By.cssSelector(".notice.errors");

    public static boolean errorMessageIsVisible() {
        return $(errorMessage).isDisplayed();
    }

    public static String getErrorMessageText() {
        return $(errorMessage).getText();
    }

    @Step("Get error message contents")
    public static SelenideElement getErrorMessage() {
        return $(errorMessage);
    }
}
