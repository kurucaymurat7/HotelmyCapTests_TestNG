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

public class PositiveTest {
    @Test
    public void PositiveTest() throws IOException {

        //1 ) Bir Class olustur : PositiveTest
        //2) Bir test method olustur positiveLoginTest()
        //https://www.hotelmycamp.com/ adresine git
        HotelMyCapPage hotelMyCapPage = new HotelMyCapPage();
        String url = ConfigReader.getProperty("hotelMyCapUrl");
        Driver.getDriver().get(ConfigReader.getProperty("hotelMyCapUrl"));

        //login butonuna bas
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].click();", hotelMyCapPage.loginButton);

        //test data username: manager ,
        //test data password : Manager1!
        //Degerleri girildiginde sayfaya basarili sekilde girilebildigini test et

        Actions actions = new Actions(Driver.getDriver());
        actions
                .click(hotelMyCapPage.userName)
                .sendKeys(ConfigReader.getProperty("fakeUserName"))
                .sendKeys(TAB)
                .sendKeys(ConfigReader.getProperty("fakePassword"))
                .sendKeys(ENTER)
                .perform();

        String actualURL = Driver.getDriver().getCurrentUrl();
        Assert.assertEquals(actualURL, ConfigReader.getProperty("expectedURL"));

        // kanıt amaçlı, admin sayfasında iken screenshot al ve kaydet.
        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMddHHmmss");
        String tarih = date.format(dtf);
        File tumEkranResmi = new File("target/screenshots/adminPage"+tarih+".jpeg");
        File temp = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(temp, tumEkranResmi);
        Driver.closeDriver();
    }
}
