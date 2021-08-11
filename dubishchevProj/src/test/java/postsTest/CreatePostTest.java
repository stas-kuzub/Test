package postsTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.Util;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "Dubishchev title " + Util.getDateAndTimeFormatted();

    @Parameters({
            "Общедоступное",
            "Частное сообщение",
            "Сообщение для группы"
    })
    @TestCaseName("Create post test : textToClickInDropDown = {0}")
    @Test
    public void createPost(String textToClick) {
        loginPage
                .loginWithValidCred()
                .checkIsRedirectOnHomePage()
//                .checkIsButtonSignOutVisible() //moved to checkIsRedirectOnHomePage
                .clickOnButtonCreatePost()
                .checkIsRedirectOnCreatePostPage()
                .checkIsTitlePresent()
                .enterTextIntoInputTitle(POST_TITLE)
                .enterTextIntoInputBody("Some body text")
                //.selectTextInDDSelectValue("Частное сообщение")
                //.selectValueInDDSelectValue("One Person")
                .selectTextInDropDownByClick(textToClick)
                .clickOnSaveButton()
                .checkIsRedirectOnPostPage()
                //.checkIsButtonDeletePresent() //moved to checkIsRedirectOnPostPage
                .checkIsSuccessMessagePresent()
                .checkTextInSuccessMessage("New post successfully created.")
                .clickOnProfileButton()
                .checkIsRedirectOnProfilePage()
                .checkIsPostWasAdded(POST_TITLE)
        ;
    }

    @After
    public void postDelete() {
        homePage.openHomePage()
                .checkIsRedirectOnHomePage()
                .clickOnProfileButton()
                .checkIsRedirectOnProfilePage()
                .deletePostWithTitleWhilePresent(POST_TITLE)
        ;
    }


}
