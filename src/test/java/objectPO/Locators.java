package objectPO;

import org.openqa.selenium.By;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Locators {

    private static Properties props;

    static {
        props = new Properties();

        InputStream is = Locators.class.getResourceAsStream("/locators.properties");

        try {
            props.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static By getLocator(String elementName) throws Exception {

        String[] locator = props.getProperty(elementName).split("=",2);

        switch(locator[0]) {
            case "name" -> {
                return By.name(locator[1]);
            }
            case "css" -> {
                return By.cssSelector(locator[1]);
            }
            case "xpath" -> {
                return By.xpath(locator[1]);
            }
            default -> throw new Exception("No such locator type");
        }

    }
}
