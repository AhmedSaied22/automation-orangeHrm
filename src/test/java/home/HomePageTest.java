package home;

import org.junit.Test;
import pages.HomePage;
import utils.BaseTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HomePageTest extends BaseTest {
    private void performSuccessfulLogin() {
        loginPage.login("Admin", "admin123");

        // تأكد من نجاح Login
        HomePage homePage = new HomePage(driver);
        assertTrue("Should be logged in", homePage.isUserLoggedIn());
    }

    @Test
    public void testDashboardWidgetsVisibility() {
        System.out.println("Starting testDashboardWidgetsVisibility");
        performSuccessfulLogin();
        HomePage homePage = new HomePage(driver);
        assertTrue("Should be on dashboard page", homePage.isOnDashboardPage());
        assertEquals("Dashboard should have exactly 7 widgets", 7, homePage.getWidgetCount());
        assertTrue("All 7 widgets should be visible", homePage.areSevenWidgetsDisplayed());
    }
    }
