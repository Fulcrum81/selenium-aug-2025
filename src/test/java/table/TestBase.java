package table;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class TestBase {
    protected WebDriver driver;

    @BeforeMethod
    public void setup() {

        String browser = System.getProperty("browser", "chrome");

        driver = switch (browser) {
            case "chrome" -> new ChromeDriver();
            case "safari" -> new SafariDriver();
            case "firefox" -> new FirefoxDriver();
            case "edge" -> new EdgeDriver();
            default -> new ChromeDriver();
        };

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(5));

        driver.manage().window().maximize();

        driver.get("https://the-internet.herokuapp.com/tables");
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
