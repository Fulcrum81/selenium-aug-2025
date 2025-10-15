package htmlelements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

public class LoginResultPage {

    RegionElement regionElement;

    public LoginResultPage(WebDriver driver) {
        HtmlElementLoader.populate(this, driver);
    }

    @FindBy(css = ".notice.errors")
    private WebElement errorMessage;

    public boolean errorMessageIsVisible() {
        return errorMessage.isDisplayed();
    }

    public String getErrorMessageText() {
        return errorMessage.getText();
    }
}
