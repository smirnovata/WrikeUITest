import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.Random;

public class WrikeTest {
    public WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver2.42");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }


    @Test
    public void wrikeTest() {
        //1. Open url: wrike.com;
        HomeWrikePage homeWrikePage = new HomeWrikePage(driver);
        driver.get(HomeWrikePage.URL);
        Assert.assertEquals("Your online project management software - Wrike", driver.getTitle());

        //2. Click "Get started for free" button near "Login" button;
        homeWrikePage.clickGetStartNearLog();
        //3. Fill in the email field with random generated value of email with mask
        // “<random_text>+wpt@wriketask.qaa” (e.g. “abcdef+wpt@wriketask.qaa”);
        homeWrikePage.setEmailInGetStart("test" + new Random().nextInt(124) + "+wpt@wriketask.qaa");
        //4. Click on "Create my Wrike account" button + check with assertion that you are moved to the next page;
        homeWrikePage.clickCreateAccInGS();
        ResendWrikePage resendWrikePage = new ResendWrikePage(driver);
        resendWrikePage.waitVisibilityFrameGoogle();

        Assert.assertEquals(ResendWrikePage.URL, driver.getCurrentUrl());
        resendWrikePage.clickNoFrameGoogleCustomerReviews();
        //5. Fill in the Q&A section at the left part of page (like random generated answers)
        // + check with assertion that your answers are submitted;
        resendWrikePage.clickRandomFirstAnswerForTest();
        resendWrikePage.clickRandomSecondAnswerForTest();
        resendWrikePage.clickRandomThirdAnswerForTest();
        resendWrikePage.clickSubmitResults();
        resendWrikePage.waitVisibilityQASuccess();
        Assert.assertTrue(resendWrikePage.isDisplayedSuccessQA());
        //6. Click on "Resend email" + check it with assertion;
        resendWrikePage.clickResendEmail();
        resendWrikePage.waitInvisibilityResendEmail();
        Assert.assertFalse(resendWrikePage.isDisplayedResendEmail());
        //7. Check that section "Follow us" at the site footer contains the "Twitter" button
        // that leads to the correct url and has the correct icon.
        resendWrikePage.findIconTwitter();
        resendWrikePage.clickSwitchToTwitter();
        Assert.assertEquals("https://twitter.com/wrike", driver.getCurrentUrl());
    }

    @After
    public void close() {
        driver.quit();
    }
}

