package fluentPO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class LoginTest {

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(5));

        driver.manage().window().maximize();

        driver.get("https://litecart.stqa.ru/en/");
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Test
    public void loginWithDisabledAccountTest() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage
                .enterLogin("Vadim")
                .enterPassword("sdkjfbskjhf")
                .clickLoginButton()
                .validateErrorMessageIsVisible()
                .validateErrorMessageText("sdlfhwdf");

    }

}
