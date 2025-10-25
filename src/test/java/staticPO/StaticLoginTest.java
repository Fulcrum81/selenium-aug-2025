package staticPO;

import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.testng.SoftAsserts;
import com.epam.reportportal.service.ReportPortal;
import com.epam.reportportal.testng.ReportPortalTestNGListener;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import listeners.ScreenshotListener;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Calendar;

import static com.codeborne.selenide.Browsers.CHROME;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

@Listeners({ SoftAsserts.class, ScreenshotListener.class, ReportPortalTestNGListener.class })
public class StaticLoginTest {

    @BeforeMethod
    public void setup() {

        Configuration.browser = CHROME;
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;

        open("https://litecart.stqa.ru/en/");
    }

    @AfterMethod
    public void teardown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            File screenshot = Selenide.screenshot(OutputType.FILE);
            try {
                Allure.addAttachment(result.getMethod().getMethodName() + " screenshot",
                        new FileInputStream(screenshot));
                ReportPortal.emitLog(result.getMethod().getMethodName() + " screenshot", "ERROR",
                        Calendar.getInstance().getTime(), screenshot);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        closeWebDriver();
    }

    @Description("Attempt to login with disabled account and validate that error message appears and contains expected text")
    @Test(description = "Login with disabled account")
    public void loginWithDisabledAccountTest() {
        LoginPage.login("vadim.zubovich@gmail.com", "Test1234!");

        Configuration.assertionMode = AssertionMode.SOFT;
        LoginResultPage.getErrorMessage().shouldBe(visible)
                .shouldHave(text("Wrong password or the account is disabled, or does not exist111"));
    }

}
