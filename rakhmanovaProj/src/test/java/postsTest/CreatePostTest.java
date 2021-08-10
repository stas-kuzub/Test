package postsTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "Tetiana title of post" + Util.getDateAndTimeFormatted();

    @Test
    public void createPost() {
        loginPage
                .loginWithValidCred()
                .checkIsButtonSignOutVisible()
                .clickOnButtonCreatePost()
                .checkIsRedirectOnCreatePostPage()
                .checkIsInputTitlePresent()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("Post text")
                //.selectTextInDropDownSelectValue("Частное сообщение")
                //.selectTextInDropDownByClick("One Person")
                .selectValueInDropDownSelectValue("One Person")
                //.selectCheckBoxState("check")---------------------------------HW - error
                .clickOnSaveButton()
                .checkIsButtonDeletePresent()
        .checkIsSuccessMessagePresent()
        .checkTextInSuccessMessage("New post successfully created.")
        .clickOnButtonProfile()
        .checkIsPostWasAdded(POST_TITLE)
                ;
    }

    @After
    public void deletePost(){
        homePage
                .openHomePage()
              .checkIsButtonSignOutVisible()
              .clickOnButtonProfile()
              .deletePostWithTitleWhilePresent(POST_TITLE)


                ;
    }
}
