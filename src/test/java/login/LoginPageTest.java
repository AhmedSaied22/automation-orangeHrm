package login;

import org.junit.Test;
import pages.HomePage;
import utils.BaseTest;
import utils.ConfigReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginPageTest extends BaseTest {

    @Test
    public void testSuccessfulLogin() {
        loginPage.login(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
        HomePage homePage = new HomePage(driver);
        assertEquals("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index", driver.getCurrentUrl());
        assertTrue("Login should be successful", homePage.isUserLoggedIn());
        assertTrue("Dashboard should be visible", homePage.isDashboardDisplayed());
    }

    @Test
    public void testInvalidUsernameValidPassword() {
        loginPage.login("Invalid", "admin123");
        assertTrue("Error message should be displayed", loginPage.isErrorMessageDisplayed());
        assertEquals("Invalid credentials", loginPage.getErrorMessageText());
    }

    @Test
    public void testValidUsernameInvalidPassword() {
        loginPage.login("Admin", "admi123");
        assertTrue("Error message should be displayed", loginPage.isErrorMessageDisplayed());
        assertEquals("Invalid credentials", loginPage.getErrorMessageText());
    }

    @Test
    public void testEmptyUsernameField() {
        loginPage.login("", "admin123");
        assertTrue("Error text should contain required",
                loginPage.getFieldErrorText().contains("Required"));
        assertTrue("Error message should be displayed", loginPage.isFieldErrorDisplayed());
    }

    @Test
    public void testEmptyPasswordField() {
        loginPage.login("Admin", "");
        assertTrue("Error text should be displayed", loginPage.isFieldErrorDisplayed());
        assertTrue("Error Text should contain required", loginPage.getFieldErrorText().contains("Required"));
    }

    @Test
    public void testBothFieldsEmpty() {
        loginPage.login("", "");
        assertTrue("Error text should be displayed", loginPage.isFieldErrorDisplayed());
        assertTrue("Error Text should contain required", loginPage.getFieldErrorText().contains("Required"));
    }

}
