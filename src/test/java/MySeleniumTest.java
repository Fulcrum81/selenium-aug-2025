import com.epam.reportportal.service.ReportPortal;
import com.epam.reportportal.testng.ReportPortalTestNGListener;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import listeners.ScreenshotListener;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

@Epic("Initial Selenium training tests")
@Feature("Super-duper feature under test")
@Listeners({ ReportPortalTestNGListener.class })
public class MySeleniumTest {

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
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
