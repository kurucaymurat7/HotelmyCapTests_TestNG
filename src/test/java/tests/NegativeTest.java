package tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HotelMyCapPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.openqa.selenium.Keys.ENTER;
import static org.openqa.selenium.Keys.TAB;

public class NegativeTest {
    @Test
    public void negativeTest() throws IOException {
        //1 ) Bir Class olustur : NegativeTest
        //2) Bir test method olustur NegativeLoginTest()
        //https://www.hotelmycamp.com/ adresine git
        HotelMyCapPage hotelMyCapPage = new HotelMyCapPage();
        String url = ConfigReader.getProperty("hotelMyCapUrl");
        Driver.getDriver().get(ConfigReader.getProperty("hotelMyCapUrl"));

        //login butonuna bas
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].click();", hotelMyCapPage.loginButton);

        //test data username: manager1  ,
        //test data password : Manager1!
        //Degerleri girildiginde sayfaya basarili sekilde girilebildigini test et

        Actions actions = new Actions(Driver.getDriver());
        actions
                .click(hotelMyCapPage.userName)
                .sendKeys(ConfigReader.getProperty("incorrectUserName"))
                .sendKeys(TAB)
                .sendKeys(ConfigReader.getProperty("correctPassword"))
                .sendKeys(ENTER)
                .perform();

        String actualURL = Driver.getDriver().getCurrentUrl();
        Assert.assertTrue(hotelMyCapPage.girisYapilamadi.isDisplayed());

        // kanıt amaçlı, admin sayfasında iken screenshot al ve kaydet.
        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMddHHmmss");
        String tarih = date.format(dtf);
        File tumEkranResmi = new File("target/screenshots/tryAgainIncorectCredentials_" + tarih + ".jpeg");
        File temp = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(temp, tumEkranResmi);
        Driver.closeDriver();
    }
}