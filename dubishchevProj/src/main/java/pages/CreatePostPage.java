package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {
    //@FindBy(xpath = ".//input[@name='title']")
    @FindBy(name = "title")
    private WebElement inputTitle;
    @FindBy(id = "post-body")
    private WebElement inputBody;
    @FindBy(xpath = ".//button[text()='Save New Post']")
    private WebElement saveButton;

    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CreatePostPage checkIsTitlePresent() {
        Assert.assertTrue("Input title is not present", isElementPresent(inputTitle));
        return this;
    }

    public CreatePostPage enterTextIntoInputTitle(String post_title) {
        enterTextToElement(inputTitle, post_title);
        return this;
    }

    public CreatePostPage enterTextIntoInputBody(String body_text) {
        enterTextToElement(inputBody, body_text);
        return this;
    }

    public PostPage clickOnSaveButton() {
        clickOnElement(saveButton);
        return new PostPage(webDriver);
    }
}
