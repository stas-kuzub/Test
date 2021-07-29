package postsTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "Liuda title of Post" + Util.getDateAndTimeFormated();
    @Test
    public void createPost(){
        loginPage
                .loginWithValidCred()
                .checkIsButtonSignOutVisible()
                .clickOnButtonCreatePost()
                .checkIsInputTitlePresent()
                .enterTextInputTitle(POST_TITLE)
                .enterTextIntoInputBody("Body text")
                .clickOnSaveButton()
                ;

    }

}
