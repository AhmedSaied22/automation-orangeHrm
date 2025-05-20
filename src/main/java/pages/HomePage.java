package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class HomePage {
    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locators
    @FindBy(css = ".oxd-topbar-header-breadcrumb h6")
    private WebElement dashboardHeader;

    @FindBy(css = ".oxd-userdropdown-name")
    private WebElement userDropdown;

    // Different possible selectors for widgets
    @FindBy(xpath = "//*[contains(@class, 'oxd-sheet') and contains(@class, 'oxd-sheet--rounded') and contains(@class, 'oxd-sheet--white') and contains(@class, 'orangehrm-dashboard-widget')]")
    private List<WebElement> dashboardWidgets;

    @FindBy(css = "[class*='widget']")
    private List<WebElement> alternativeWidgets;

    @FindBy(css = ".oxd-main-menu-item")
    private List<WebElement> menuItems;

    // Validation Methods
    public boolean isUserLoggedIn() {
        try {
            return userDropdown.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDashboardDisplayed() {
        try {
            return dashboardHeader.isDisplayed() &&
                    dashboardHeader.getText().contains("Dashboard");
        } catch (Exception e) {
            return false;
        }
    }

    public int getWidgetCount() {
        // جرب الـ selector الأساسي الأول
        if (!dashboardWidgets.isEmpty()) {
            return dashboardWidgets.size();
        }

        // لو مافيش، جرب البديل
        if (!alternativeWidgets.isEmpty()) {
            return alternativeWidgets.size();
        }

        // لو مافيش أي widgets
        return 0;
    }

    public boolean areSevenWidgetsDisplayed() {
        int widgetCount = getWidgetCount();
        return widgetCount == 7;
    }

    public String getDashboardTitle() {
        try {
            return dashboardHeader.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isOnDashboardPage() {
        return getCurrentUrl().contains("dashboard") && isDashboardDisplayed();
    }

}