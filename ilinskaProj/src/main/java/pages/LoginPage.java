package pages;

import libs.TestData;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@placeholder='Username']")
    private TextInput inputLogin;
    @FindBy(xpath = ".//input[@placeholder='Password']")
    private TextInput inputPassword;
    @FindBy(xpath = ".//button[text()='Sign In']")
    private Button buttonSignIn;
    @FindBy(xpath = ".//div[@class='alert alert-danger text-center' and text()='Invalid username / password']")
    private WebElement errorSignIN;
    @FindBy(xpath = ".//input[@placeholder='Pick a username']")
    private WebElement inputLOgin;
    @FindBy(xpath = ".//input[@placeholder='you@example.com']")
    private WebElement inputEmail;
    @FindBy(xpath = ".//input[@placeholder='Create a password']")
    private WebElement inputPass;
    @FindBy(xpath = ".//button[text()='Sign up for OurApp']")
    private Button OurApp;

    @FindBy(xpath = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' and text()='Username must be at least 3 characters.']")
    private WebElement errorMessage;
    @FindBy(xpath = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' and text()='You must provide a valid email address.']")
    private WebElement errorEmailMessage;
    @FindBy(xpath = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' and text()='Password must be at least 12 characters.']")
    private WebElement errorPassword;
    @FindBy(id = "username-register")
    private WebElement inputLoginRegistration;
    @FindBy(id = "email-register")
    private WebElement inputEmailRegistration;
    @FindBy(id = "password-register")
    private WebElement inputPasswordRegistration;
    @FindBy(xpath = ".//button[text()='Sign up for OurApp']")
    private WebElement buttonSignUp;
    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> actuallistofErrors;
    final String listErrorLocators = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return null;
    }

    public void openLoginPage() {
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com");
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Сannot work with LoginPage" + e);
            Assert.fail("Сannot work with LoginPage");
        }
    }

    public void enterLoginIn(String login) {

        enterTextToElement(inputLogin, login);

    }

    public boolean isSignINPresent() {
        return isElementPresent(buttonSignIn);
    }


    public void enterLoginSignUp(String login) {
        enterTextToElement(inputLOgin, login);
    }

    public void enterEmail(String email) {
        enterTextToElement(inputEmail, email);

    }

    public void enterPasswwordInSign(String password) {
        enterTextToElement(inputPassword, password);


    }

    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);

    }

    public void fillinLoginFormandSubmit(String login, String password) {
        openLoginPage();
        enterLoginIn(login);
        enterPasswwordInSign(password);
        clickOnButtonSignIn();
    }

    public HomePage loginWithValidCred() {
        fillinLoginFormandSubmit(TestData.VALIG_LOGin, TestData.VALID_PASSWORd);
        return new HomePage(webDriver);
    }


    public void clickOnOurApp() {
        clickOnElement(OurApp);
    }

    public boolean isPopupDisplay() {
        return isElementPresent(buttonSignIn);

    }

    public void enterPassSignUp(String password) {
        enterTextToElement(inputPass, password);
    }

    public boolean isErrorPopUpUserNamePresent() {
        return isElementPresent(errorMessage);

    }

    public boolean isErrorPopupEmailPresent() {
        return isElementPresent(errorEmailMessage);

    }

    public boolean isErrorPopupPassPresent() {

        return isElementPresent(errorPassword);
    }

    public void fillinandsubmit(String login, String password) {
        openLoginPage();
        enterLoginIn("auto");
        enterPasswwordInSign("123");
        clickOnButtonSignIn();

    }

    public LoginPage enterLoginRegistration(String login) {
        enterTextToElement(inputLoginRegistration, login);
        return this;
    }

    public LoginPage enterEmailRegistration(String email) {
        enterTextToElement(inputEmailRegistration, email);
        return this;
    }

    public LoginPage enterPasswordRegistration(String pasword) {
        enterTextToElement(inputPasswordRegistration, pasword);
        return this;
    }
    public void checkErrorsMessages(String expectedErrors) {
        String[] errorsArray = expectedErrors.split(";");
        webDriverWait10.withMessage("Number Of Messages ")
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath(listErrorLocators), errorsArray.length ));
//        Assert.assertEquals(actualListOfErrors.size(), errorsArray.length);

        SoftAssertions softAssertions = new SoftAssertions();
        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element: actuallistofErrors) {
            actualTextFromErrors.add(element.getText());
        }
        for (int i = 0; i < errorsArray.length; i++) {
            softAssertions.assertThat(errorsArray[i]).isIn(actualTextFromErrors);
        }

        softAssertions.assertAll();

    }
}
