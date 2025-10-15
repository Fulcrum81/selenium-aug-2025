package htmlelements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

public class LoginPage {

    RegionElement regionElement;

    @FindBy(name = "email")
    private WebElement loginInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(name = "login")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        HtmlElementLoader.populate(this, driver);
    }

    public void enterLogin(String username) {
        loginInput.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void login(String username, String password) {
        enterLogin(username);
        enterPassword(password);
        clickLoginButton();
    }


}
