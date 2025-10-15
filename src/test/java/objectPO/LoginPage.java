package objectPO;

import org.openqa.selenium.WebDriver;

import static objectPO.Locators.getLocator;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterLogin(String username) throws Exception {
        driver.findElement(getLocator("LoginPage.LoginInput")).sendKeys(username);
    }

    public void enterPassword(String password) throws Exception {
        driver.findElement(getLocator("LoginPage.PasswordInput")).sendKeys(password);
    }

    public void clickLoginButton() throws Exception {
        driver.findElement(getLocator("LoginPage.LoginButton")).click();
    }

    public void login(String username, String password) throws Exception {
        enterLogin(username);
        enterPassword(password);
        clickLoginButton();
    }


}
