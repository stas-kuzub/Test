package pages;

import libs.TestData;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassWord;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//div[text()='Invalid username / password']")
    private WebElement alertSignIn;

    @FindBy(xpath = ".//input[@id='username-register']")
    private WebElement inputLoginSignUpForm;

    @FindBy(xpath = ".//input[@id='email-register']")
    private WebElement inputEmailSignUpForm;

    @FindBy(xpath = ".//input[@id='password-register']")
    private WebElement inputPasswordSignUpForm;

    @FindBy(xpath = ".//button[text()='Sign up for OurApp']")
    private WebElement buttonSignUp;

    @FindBy(xpath = ".//div[contains(text(), 'Username must be at least 3 characters')]")
    private WebElement usernameSignUpAlert;

    @FindBy(xpath = ".//div[contains(text(), 'You must provide a valid email address')]")
    private WebElement emailSignUpAlert;

    @FindBy(xpath = ".//div[contains(text(), 'Password must be at least 12 characters')]")
    private WebElement passwordSignUpAlert;

    final String signUpErrorsLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> actualListOfErrors;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public void openLoginPage() {
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Can not work with LoginPage" + e);
            Assert.fail("Can not work with LoginPage");
        }
    }

    public void enterLoginInSignIn(String login) {
//        try{
////            WebElement element = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
//            inputLogin.clear();
//            inputLogin.sendKeys(login);
//            logger.info(login + " was entered in SignIn input login");
//        }catch (Exception e){
//            logger.error("Cannot work with element" + e);
//            Assert.fail("Cannot work with element" + e);
//        }
        enterTextToElement(inputLogin, login);
    }

    public void enterPassWordInSignIn(String passWord) {
        enterTextToElement(inputPassWord, passWord);
    }


    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }


    public void fillLoginFormAndSubmit(String login, String passWord) {
        openLoginPage();
        enterLoginInSignIn(login);
        enterPassWordInSignIn(passWord);
        clickOnButtonSignIn();
    }


    public boolean isButtonSignInPresent() {
        return isElementPresent(buttonSignIn);
    }

    public boolean isSignInAlertPresent() {
        return isElementPresent(alertSignIn);
    }

    public void enterLoginInSignUp(String login) {
        enterTextToElement(inputLoginSignUpForm, login);
    }

    public void enterEmailInSignUp(String email) {
        enterTextToElement(inputEmailSignUpForm, email);
    }

    public void enterPassWordInSignUp(String passWord) {
        enterTextToElement(inputPasswordSignUpForm, passWord);
    }

    public void clickOnButtonSignUp() {
        clickOnElement(buttonSignUp);
    }

    public void fillSignUpFormAndSubmit(String login, String email, String passWord) {
        openLoginPage();
        enterLoginInSignUp(login);
        enterEmailInSignUp(email);
        enterPassWordInSignUp(passWord);
        clickOnButtonSignUp();
    }

    public LoginPage enterLoginRegistration (String username){
        enterTextToElement(inputLoginSignUpForm, username);
        return this;
    }

    public LoginPage enterEmailRegistration(String email) {
        enterTextToElement(inputEmailSignUpForm, email);
        return this;
    }

    public LoginPage enterPasswordRegistration(String password) {
        enterTextToElement(inputPasswordSignUpForm, password);
        return this;
    }

    public boolean isUsernameSignUpAlertPresent() {
        return isElementPresent(usernameSignUpAlert);
    }

    public boolean isEmailSignUpAlertPresent() {
        return isElementPresent(emailSignUpAlert);
    }

    public boolean isPasswordSignUpAlertPresent() {
        return isElementPresent(passwordSignUpAlert);
    }


    public HomePage loginWithValidCred() {
        fillLoginFormAndSubmit(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
        return new HomePage(webDriver);
    }


    public void checkErrors(String s) {
        List<WebElement> alertsListActual = webDriver.findElements(By.xpath(signUpErrorsLocator));
        List<String> alertListExpectedText = Arrays.asList(s.split(";"));
        Assert.assertEquals("The actual number of alerts (" + alertsListActual.size() + ")" +
                "is not as expected (" + alertListExpectedText.size() + ")", alertListExpectedText.size(), alertsListActual.size());
        int counterExpectedTextMatchToInputSignUpFieldName = 0;
        int counterExpectedTextNotMatchToActualAlertText = 0;
        ArrayList<String> notMatchedExpectedTextParts = new ArrayList<>();
        for (int i = 0; i < alertListExpectedText.size(); i++) {
            for (int j = 0; j < alertsListActual.size(); j++) {
                String inputSignUpFieldNameRelatedToActualAlert
                        = alertsListActual.get(j).findElement(By.xpath(".//..//input")).getAttribute("name");
                if (alertListExpectedText.get(i).toLowerCase()
                        .contains(inputSignUpFieldNameRelatedToActualAlert.toLowerCase())) {
                    counterExpectedTextMatchToInputSignUpFieldName++;
                    if (alertListExpectedText.get(i).equals(alertsListActual.get(j).getText())) {
                        logger.info("CheckErrors method part " + (i + 1) + " was matched to " +
                                inputSignUpFieldNameRelatedToActualAlert + "ActualAlertText");
                    } else {
                        counterExpectedTextNotMatchToActualAlertText++;
                        notMatchedExpectedTextParts.add("\nExpected: " + alertListExpectedText.get(i) + "\n"
                                + "Actual: " + alertsListActual.get(j).getText() + "\n");
                        logger.error("CheckErrors method part " + (i + 1) + " was not  matched to "
                                + inputSignUpFieldNameRelatedToActualAlert + "ActualAlertText");
                    }
                }
            }
        }
        if (counterExpectedTextMatchToInputSignUpFieldName < 1) {
            Assert.fail("Any part of Expected alert text does not correspond with webElementInputName related to actual Alert on the page");
        } else if (counterExpectedTextNotMatchToActualAlertText > 0) {
            Assert.fail("Some part of Method expected alert text does not correspond with actual alert text on the page. Check log\n" +
                    notMatchedExpectedTextParts);
        }
    }

    public void checkErrorsMessages(String expectedErrors) {
        String[] errorsArray = expectedErrors.split(";");
        webDriverWait10.withMessage("Number of Messages")
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath(signUpErrorsLocator), errorsArray.length));
//        Assert.assertEquals(actualListOfErrors.size(), errorsArray.length);
        SoftAssertions softAssertions = new SoftAssertions();
        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element : actualListOfErrors) {
            actualTextFromErrors.add(element.getText());
        }
        for (int i = 0; i < errorsArray.length; i++) {
            softAssertions.assertThat(errorsArray[i]).isIn(actualTextFromErrors);
        }
        softAssertions.assertAll();

    }
}