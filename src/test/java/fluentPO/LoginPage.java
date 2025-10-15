package fluentPO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private By loginInput = By.name("email");
    private By passwordInput = By.name("password");
    private By loginButton = By.name("login");

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage enterLogin(String username) {
        driver.findElement(loginInput).sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
        return this;
    }

    public LoginResultPage clickLoginButton() {
        driver.findElement(loginButton).click();
        return new LoginResultPage(driver);
    }

    public LoginResultPage login(String username, String password) {
        enterLogin(username);
        enterPassword(password);
        clickLoginButton();

        return new LoginResultPage(driver);
    }
}
