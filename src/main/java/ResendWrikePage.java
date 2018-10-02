import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class ResendWrikePage {
    public static final String URL = "https://www.wrike.com/resend/";
    private Random random = new Random();
    private WebDriver driver;

    private By waitSuccessLocator = By.cssSelector("div.survey-success");

    private By frameGoogleLocator = By.cssSelector("iframe[ng-non-bindable]");
    private By noFrameGoogleLocator = By.cssSelector("[jsname=\"I5FQ0b\"]");

    //private By firstAnsForFirstLocator = By.cssSelector("body > div.wg-layout.wg-layout--outline > main > div > div > div.section.section-resend-main.section-resend-main-va.section-resend-main--survey > div > div.wg-cell.wg-cell--md-6.wg-cell--lg-7 > div > form > div:nth-child(6) > label:nth-child(1) > button");
    private By firstAnsForFirstLocator = By.xpath("/html/body/div[1]/main/div/div/div[2]/div/div[2]/div/form/div[1]/label[1]/button");
    private By secondAnsForFirstLocator = By.xpath("//label[2]/button");
    private By randomAnsForSecondLocator = By.xpath("//div[2]/label[" + (random.nextInt(5) + 1) + "]/button");
    private By randomAnsForThirdLocator = By.xpath("//div[3]/label[" + (random.nextInt(2) + 1) + "]/button");
    private By submitResultsLocator = By.xpath("(//button[@type='submit'])[4]");


    private By resendEmailLocator = By.xpath("/html/body/div[1]/main/div/div/div[2]/div/div[1]/p[3]/button");
    private By iconTwitterLocator = By.cssSelector("svg.wg-footer__social-icon > use");
    private By TwitterLocator = By.cssSelector("li.wg-footer__social-item:nth-child(1)");

    public ResendWrikePage(WebDriver driver) {
        this.driver = driver;
    }


    public void waitVisibilityFrameGoogle() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(frameGoogleLocator));
    }

    public void clickNoFrameGoogleCustomerReviews() {
        driver.switchTo().frame(driver.findElement(frameGoogleLocator));
        driver.findElement(noFrameGoogleLocator).click();
    }

    // Fill in the Q&A section at the left part of page (like random generated answers)

    public void clickRandomFirstAnswerForTest() {
        if (random.nextInt(1) == 0) driver.findElement(firstAnsForFirstLocator).click();
        else driver.findElement(secondAnsForFirstLocator).click();
    }

    public void clickRandomSecondAnswerForTest() {
        driver.findElement(randomAnsForSecondLocator).click();
    }

    public void clickRandomThirdAnswerForTest() {
        driver.findElement(randomAnsForThirdLocator).click();
    }

    public void clickSubmitResults() {
        driver.findElement(submitResultsLocator).click();
    }

    public void waitVisibilityQASuccess() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(waitSuccessLocator));
    }

    public Boolean isDisplayedSuccessQA() {
        return driver.findElement(waitSuccessLocator).isDisplayed();
    }


    public void clickResendEmail() {
        driver.findElement(resendEmailLocator).click();
    }

    public void waitInvisibilityResendEmail() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.invisibilityOfElementLocated(resendEmailLocator));
    }

    public Boolean isDisplayedResendEmail() {
        return driver.findElement(resendEmailLocator).isDisplayed();
    }

    public void findIconTwitter() {
        driver.findElement(iconTwitterLocator);
    }

    public void clickSwitchToTwitter() {
        driver.findElement(TwitterLocator).click();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }
}

