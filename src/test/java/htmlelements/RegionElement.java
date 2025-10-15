package htmlelements;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Name("Regional settings")
@FindBy(id="region")
public class RegionElement extends HtmlElement {

    @Name("Change regional settings link")
    @FindBy(css="a.fancybox-region")
    public WebElement changeLink;

    @Name("Country name")
    @FindBy(className = "country")
    public WebElement countryLabel;

    public void clickChangeLink() {
        changeLink.click();
    }

    public String getCountryName() {
        return countryLabel.getText();
    }

}
