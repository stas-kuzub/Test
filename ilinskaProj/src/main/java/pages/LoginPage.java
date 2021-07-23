package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputLogin;
    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;
    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;
    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com");
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Сannot work with LoginPage"+ e);
            Assert.fail("Сannot work with LoginPage");
        }
    }

    public void enterLoginIn(String login) {
//        try{
//           // WebElement element=webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
//          inputLogin.clear();
//          inputLogin.sendKeys(login);
//            logger.info((login+"was inputted in SignIn input login"));
//
//        } catch (Exception e){
//            logger.error("Cannot work with element" +e);
//            Assert.fail("Сannot work with LoginPage"+e);
enterTextToElement(inputLogin,login);

        }

    public void enterPasswwordInSign(String password) {
        enterTextToElement(inputPassword,password);

    }

    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);

    }
}


