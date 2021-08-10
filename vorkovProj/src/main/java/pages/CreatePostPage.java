package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {

    @FindBy(xpath = ".//input[@name='title']")
//    or
//    @FindBy(name = "title")
    private WebElement inputTitle;

    @FindBy(xpath = ".//textarea[@id='post-body']")
    private WebElement inputBody;

    @FindBy(xpath = ".//button[text()='Save New Post']")
    private WebElement buttonSaveNewPost;

    @FindBy(xpath = ".//div[text()='New post successfully created.']")
    private WebElement alertPostSuccess;

    @FindBy(xpath = ".//button[@data-original-title='Delete']")
    private WebElement deletePostButton;

    @FindBy(xpath = ".//*[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy(xpath = ".//select[@id='select1']")
    private WebElement dropDownSelectValue;

    @FindBy(id = "”UniquePost”")
    private WebElement checkboxUniquePost;

    // --------------------------------------------------------------------------------------------------
    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/create-post";
    }

    public CreatePostPage checkIsInputTitleIsPresent() {
        Assert.assertTrue("Input title isn't present", isElementPresent(inputTitle));
        return this;
    }

    public CreatePostPage enterTextIntoInputTitle(String POST_TITLE) {
        enterTextToElement(inputTitle, POST_TITLE);
        return this;
    }

    public CreatePostPage enterTextIntoInputBody(String POST_BODY) {
        enterTextToElement(inputBody, POST_BODY);
        return this;
    }

    public CreatePostPage clickOnSaveNewPost() {
        clickOnElement(buttonSaveNewPost);
        return this;
    }

    public ProfilePage checkIsDeletePostButtonPresent() {
        Assert.assertTrue("Delete button isn't present", isElementPresent(deletePostButton));
        return new ProfilePage(webDriver);
    }

    public CreatePostPage checkIsSuccessMessagePresent() {
        Assert.assertTrue("Success message isn't present", isElementPresent(successMessage));
        return this;
    }


    public CreatePostPage checkTextInSuccessMessage(String text) {
        String actualText = successMessage.getText();
        Assert.assertEquals("Text isn't equals", text, actualText);
        return this;
    }

    public ProfilePage clickOnDeletePostButton() {
        clickOnElement(deletePostButton);
        return new ProfilePage(webDriver);
    }

    public CreatePostPage selectTextInDDSelectValue(String text) {
        selectTextInDD(dropDownSelectValue, text);
        return this;
    }

    public CreatePostPage selectValueInDDSelectValue(String value) {
        selectValueInDD(dropDownSelectValue, value);
        return this;
    }

    public CreatePostPage checkIsRedirectOnCreatePostPage() {
        Assert.assertEquals("Invalid page", baseURL + getRelativeURL(), webDriver.getCurrentUrl());
        return this;
    }

    public CreatePostPage uniquePostCheckbox(String status) {
        checkCheckBox(checkboxUniquePost, status);
        return this;
    }

    public CreatePostPage selectTextInDDByClick (String textInDD) {
        selectTextInDropdown(dropDownSelectValue, textInDD);
        return this;
    }
}
