import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomeWrikePage {
    public static final String URL = "https://www.wrike.com/";
    private WebDriver driver;

    private By startNearLogLocator = By.xpath("(//button[@type='submit'])[3]");

    private By emailInGStLocator = By.cssSelector(".modal-form-trial__input");


    private By createAccInGSLocator = By.cssSelector("button[class=\"wg-btn wg-btn--blue modal-form-trial__submit\"");


    public HomeWrikePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickGetStartNearLog() {
        driver.findElement(startNearLogLocator).click();
    }

    public void setEmailInGetStart(String email) {
        driver.findElement(emailInGStLocator)
                .sendKeys(email);
    }

    public void clickCreateAccInGS() {
        driver.findElement(createAccInGSLocator).click();
    }

}

