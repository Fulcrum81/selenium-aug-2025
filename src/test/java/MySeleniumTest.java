import com.codeborne.selenide.WebDriverRunner;
import com.epam.reportportal.service.ReportPortal;
import com.epam.reportportal.testng.ReportPortalTestNGListener;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import listeners.ScreenshotListener;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Browsers.CHROME;

@Epic("Initial Selenium training tests")
@Feature("Super-duper feature under test")
@Listeners({ ReportPortalTestNGListener.class })
public class MySeleniumTest {

    private WebDriver driver;

    @BeforeMethod
    public void setup() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        String browser = System.getProperty("browser", "chrome");
        String os = System.getProperty("os", "win11");

        switch (browser) {
            case "chrome" -> caps.setBrowserName(Browser.CHROME.browserName());
            case "safari" -> caps.setBrowserName(Browser.SAFARI.browserName());
            case "firefox" -> caps.setBrowserName(Browser.FIREFOX.browserName());
            case "edge" -> caps.setBrowserName(Browser.EDGE.browserName());
        }

        switch (os) {
            case "win11" -> caps.setPlatform(Platform.WIN11);
            case "win" -> caps.setPlatform(Platform.WINDOWS);
            case "mac" -> caps.setPlatform(Platform.MAC);
            case "linux" -> caps.setPlatform(Platform.LINUX);
        }

        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("username", "oauth-vadim.zubovich-c3335");
        sauceOptions.put("accessKey", "019c74f8-9b3c-45a5-9bba-16ffa5113415");
        sauceOptions.put("build", "My stupid build 1.1.1");
        sauceOptions.put("name", "Regression");
        caps.setCapability("sauce:options", sauceOptions);

// start the session
        URL url = new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub");

        driver = new RemoteWebDriver(url, caps);

//        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(5));

        driver.manage().window().maximize();
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Description("This test validates that when I add a duck to cart the quantity in cart preview changes")
    @Test(description = "First selenium test")
    public void firstSeleniumTest() throws InterruptedException {
        driver.get("https://litecart.stqa.ru/en/");

//        List<WebElement> rubberDuckImages = driver.findElements(By.className("image-wrapper"));
//
//        Assert.assertEquals(rubberDuckImages.size(), 6);

        WebElement purpleDuckImage = driver.findElement(By.cssSelector("img[alt='Purple Duck']"));
        purpleDuckImage.click();
        WebElement addToCartButton = driver.findElement(By.name("add_cart_product"));
        addToCartButton.click();

        WebElement cartQuantity = driver.findElement(By.cssSelector("span.quantity"));


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(22));

        wait.until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"), "1"));

        String quantityText = cartQuantity.getText();
        Assert.assertEquals(quantityText, "1");

    }

    @Description("This is a stupid Xpath test that fails all the time")
    @Test(description = "Xpath test")
    public void xpathTest() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(5));

        driver.get("https://belhard.academy/");



        WebElement detailsLinkParent = driver.findElement(By.xpath("//div[contains(@id,'cardbtn1')]"));
        List<WebElement> detailsLinks = detailsLinkParent.findElements(By.xpath(".//span[@class='t-btntext__text']"));

        detailsLinks.get(9).click();
    }

    @Description("Test that utilizes the dblClick functionality of the Actions class")
    @Test(description = "Actions test")
    public void actionsTest() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        driver.get("https://demo.guru99.com/test/simple_context_menu.html");

        WebElement doubleClickButton = driver.findElement(By.cssSelector("button[ondblclick]"));

        Actions actions = new Actions(driver);

        actions.keyDown(Keys.CONTROL).doubleClick(doubleClickButton).keyUp(Keys.CONTROL).perform();
    }


    @Description("Validate that the goal changes color after the ball is dragged into it")
    @Test(description = "Drag&Drop")
    public void dragAndDrop() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        String pageTitle = driver.getTitle();


        driver.get("https://learn.javascript.ru/article/mouse-drag-and-drop/ball4/");

        WebElement ball = driver.findElement(By.id("ball"));
        WebElement goal = driver.findElement(By.id("gate"));

        Actions actions = new Actions(driver);

        actions.clickAndHold(ball).moveByOffset(100, 0).moveByOffset(0, -100)
                .moveByOffset(-100, 0).moveByOffset(0, 100).release().perform();

        String goalBackgroundColor = goal.getCssValue("background-color");

        Assert.assertEquals(goalBackgroundColor, "rgba(255, 192, 203, 1)");

    }

    @Description("Opening a JS alert")
    @Test(description = "JS Alert test")
    public void jsAlertTest() {
        String userInput = "Hello";

        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        WebElement promptButton = driver.findElement(By.cssSelector("[onclick='jsPrompt()']"));

        promptButton.click();

        Alert prompt = driver.switchTo().alert();
        prompt.sendKeys(userInput);
        prompt.accept();

        String resultText = driver.findElement(By.id("result")).getText();

        Assert.assertEquals(resultText, "You entered: " + userInput);
    }

    @Test
    public void dropdownTest() {

        driver.get("https://the-internet.herokuapp.com/dropdown");
        WebElement dropdown = driver.findElement(By.id("dropdown"));

        Select select = new Select(dropdown);

        select.selectByValue("1");


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

//        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("my-id"))));

        wait.until((ExpectedCondition<Boolean>) d -> (Boolean)(((JavascriptExecutor)d)
                .executeScript("return jQuery.active === 0")));

    }

    @Test
    public void multipleTabs() {
        driver.get("https://the-internet.herokuapp.com/windows");

        String initialHandle = driver.getWindowHandle();

        driver.findElement(By.cssSelector("[href='/windows/new']")).click();

        String newWindowHandle = driver.getWindowHandles().toArray()[1].toString();

        driver.switchTo().window(newWindowHandle);

        String pageHeaderText = driver.findElement(By.tagName("h3")).getText();

        driver.close();

        driver.switchTo().window(initialHandle);
        pageHeaderText = driver.findElement(By.tagName("h3")).getText();

    }

    @Test
    public void framesTest() {
        driver.get("https://the-internet.herokuapp.com/nested_frames");

        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-left");

        WebElement left = driver.findElement(By.xpath("//*[contains(text(), 'LEFT')]"));

        driver.switchTo().parentFrame();
        driver.switchTo().frame("frame-middle");

        WebElement middle = driver.findElement(By.xpath("//*[contains(text(), 'MIDDLE')]"));

        driver.switchTo().defaultContent();
        driver.switchTo().frame("frame-bottom");

        WebElement bottom = driver.findElement(By.xpath("//*[contains(text(), 'BOTTOM')]"));

    }

    @Test
    public void shadowRootTest() {
        driver.get("https://canvas.apps.chrome/");

        WebElement drawingAppRoot = driver.findElement(By.id("drawing-app"));
        SearchContext drawingAppContext = drawingAppRoot.getShadowRoot();

        WebElement welcomeDialogRoot = drawingAppContext.findElement(By.cssSelector("[test-state='open']"));
        SearchContext welcomeContext = welcomeDialogRoot.getShadowRoot();

        welcomeContext.findElement(By.cssSelector("#learn-more")).click();
    }
}
