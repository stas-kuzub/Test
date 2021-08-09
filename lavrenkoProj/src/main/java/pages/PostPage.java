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

    @Override
    String getRelativeUrl() {
        return "/post/";
    }

    public PostPage checkIsButtonDeletePresent() {

        Assert.assertTrue("Button Delete is not present", isElementPresent(buttonDelete));
        return this;
    }

    public PostPage checkIsSuccesMessagePresent() {
        Assert.assertTrue("Success message is not present", isElementPresent(successMessageElement));
        return this;
    }

    public PostPage checkTextInSuccessMessage(String text) {
        String actualText = successMessageElement.getText();
        Assert.assertEquals("Text in message", text, actualText);
        return this;
    }

    public ProfilePage clickOnTheProfileButton() {
        clickOnElement(buttonProfile);
        return new ProfilePage(webDriver);
    }

    public ProfilePage clickOnDeleteBatton() {
        clickOnElement(buttonDelete);
        return new ProfilePage(webDriver);
    }
}
