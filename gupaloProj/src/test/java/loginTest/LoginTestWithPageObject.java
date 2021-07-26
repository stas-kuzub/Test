package loginTest;

import baseTest.BaseTest;
import org.junit.Test;


public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn("auto");
        loginPage.enterPasswordInSignIn("123456qwerty");
        loginPage.clickOnButtonSignIn();

        checkExpectedResult("Button sign out is not visible", homePage.isButtonSignOutPresent(), true);
    }

    @Test
    public void invalidLogin() {
        loginPage.openLoginPage();
        loginPage.fillLoginFormAndSubmit("auto", "123");
        loginPage.clickOnButtonSignIn();

        checkExpectedResult("Button sign out is not visible", loginPage.isButtonSignOutPresent(), false);
        checkExpectedResult("Button sign in is not visible", loginPage.isButtonSignInPresent(), true);
        checkExpectedResult("Message  'Invalid username / password' is not visible", loginPage.isAlertIsPresent(), true);

    }

    @Test
    public void checkValidMessagesInRegForm() {
        loginPage.openLoginPage();
        loginPage.fillRegFormAndSubmit("tr", "test.com", "123");
        loginPage.clickOnButtonSignUp();

        checkExpectedResult("Message  'Username must be at least 3 characters.' is not visible", loginPage.isLoginValidMessageInFormPresent(), true);
        checkExpectedResult("Message  'You must provide a valid email address.' is not visible", loginPage.isEmailValidMessageInFormPresent(), true);
        checkExpectedResult("Message  'Password must be at least 12 characters.' is not visible", loginPage.isPasswordValidMessageInFormPresent(), true);
    }
}
