package staticPO;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private static By loginInput = By.name("email");
    private static By passwordInput = By.name("password");
    private static By loginButton = By.name("login213");

    @Step("Enter login")
    public static void enterLogin(String username) {
        $(loginInput).sendKeys(username);
    }

    @Step("Enter password")
    public static void enterPassword(String password) {
        $(passwordInput).sendKeys(password);
    }

    @Step("Click login button")
    public static void clickLoginButton() {
        $(loginButton).click();
    }

    @Step("Login")
    public static void login(String username, String password) {
        enterLogin(username);
        enterPassword(password);
        clickLoginButton();
    }


}
