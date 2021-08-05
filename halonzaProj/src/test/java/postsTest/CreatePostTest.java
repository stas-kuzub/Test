package postsTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "Tango title of Post" + Util.getDateAndTimeFormatted();
    String postVisibility = "Частное сообщение";
    String postVisibilityValue = "One Person";

    @Test
    public void createPost() {
        loginPage
                .loginWithValidCred()
                .checkIsButtonSignOutVisible()
                .clickOnButtonCreatePost()
                .checkIsInputTitlePresent()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("Body text")
                .makeUniquePostCheckboxStatusToBe("check")
                .selectTextInDropDownByClick(postVisibility)
//                .selectTextInDropDownSelectValue("Частное сообщение")
//                .selectValueInDropDownSelectValue("One Person")
                .clickOnSaveButton()
                .checkCreatedPostVisibility(postVisibilityValue)
                .checkIsButtonDeletePresent()
                .checkIsSuccessMessagePresent()
                .checkTextInSuccessMessage("New post successfully created.")
                .clickOnButtonProfile()
                .checkIsPostWasAdded(POST_TITLE)
        ;
    }

    @After
    public void deletePost() {
        homePage
                .openHomePage()
                .checkIsButtonSignOutVisible()
                .clickOnButtonProfile()
                .deletePostWithTitleWhilePresent(POST_TITLE)
        ;
    }
}
