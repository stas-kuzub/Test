package loginTest;

import baseTest.BaseTest;

import org.junit.Test;
import org.openqa.selenium.WebElement;

public class LoginTestWithPageObject extends BaseTest {
    @Test
    public void validLogin(){
        loginPage.openLoginPage();
        loginPage.enterLoginInSignIn("auto");
        loginPage.enterPassWordInSignIn("123456qwerty");
        loginPage.clickOnButtonSignIn();

        checkExpectedResult("Button SignOut is not visible", homePage.isButtonSignOutPresent(),true);
    }
    @Test
    public void invalidLogin(){
        loginPage.fillLoginFormAndSubmit("auto", "123");

        checkExpectedResult("Button SignOut is visible", homePage.isButtonSignOutPresent(),false);
        checkExpectedResult("Button SignIn is not visible", loginPage.isButtonSignInPresent(),true);
        checkExpectedResult("label Invalid Login is not present", loginPage.isLabelMessageInvalidLoginPresent(),true);
    }
//    @Test
//    public void registrationValidationMessages(){
//        loginPage.fillRegistrationFormAndSubmit("tr", "test.com", "123");
//
//        checkExpectedResult("Label short Username is not present", loginPage.isLabelMessageShortUsernamePresent(),true);
//        checkExpectedResult("Label valid email is not present", loginPage.isLabelMessageValidEmailPresent(),true);
//        checkExpectedResult("Label password is not 12 characters", loginPage.isLabelMessagePasswordPresent(),true);
//    }
}
