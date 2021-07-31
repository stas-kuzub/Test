package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostPage extends ParentPage {
    @FindBy(xpath = ".//button[@data-original-title='Delete']")
    private WebElement buttonDelete;

    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement successMessageElement;

    @FindBy(xpath = ".//img[@data-original-title='My Profile']")
    private WebElement buttonProfile;

    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }


    public PostPage checkIsButtonDeletePresent() {
        Assert.assertTrue("Button delete is not present", isElementPresent(buttonDelete));
        return this;
    }

    public PostPage checkIsSuccessMessagePresent() {
        Assert.assertTrue("Success message is not present", isElementPresent(successMessageElement));
        return this;
    }

    public PostPage checkTextInSuccessMessage(String expextedText) {
        String actualText = successMessageElement.getText();
        Assert.assertEquals("Text in message not equals to expected", expextedText, actualText);
        return this;
    }

    public ProfilePage clickOnProfileButton() {
        clickOnElement(buttonProfile);
        return new ProfilePage(webDriver);
    }


    public ProfilePage clickOnButtonDelete() {
        clickOnElement(buttonDelete);
        return new ProfilePage(webDriver);
    }
}
