    package utils;

    import io.github.bonigarcia.wdm.WebDriverManager;
    import org.junit.After;
    import org.junit.Before;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.chrome.ChromeDriver;
    import pages.LoginPage;

    import java.time.Duration;

    public abstract class BaseTest {
        protected WebDriver driver;
        protected LoginPage loginPage;

        @Before
        public void setupDriver() {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.manage().window().maximize();
            loginPage = new LoginPage(driver);
        }

        @After
        public void quitDriver() {
            if (driver != null) {
                driver.quit();
            }
        }
    }
