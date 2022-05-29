package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class HotelMyCapPage {
    public HotelMyCapPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy (xpath = "//*[text()='Log in']")
    public WebElement loginButton;

    @FindBy (id = "UserName")
    public WebElement userName;

    @FindBy (xpath = "//*[text()='Username or password is incorrect, please correct them and try again']")
    public WebElement girisYapilamadi;
}
