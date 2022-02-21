package core;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public abstract class BaseTest {

    @BeforeTest
    public void setUp() {
        Configuration.pageLoadTimeout = 20000;
        Configuration.timeout = 15000;
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
//        Configuration.fastSetValue = true; //оптимизация мeтода setValue(), если перестанет работать, то можно
        // использовать sendKeys()
        Configuration.headless = false; // при true - браузер не будет отображаться при запуске тестов

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        Configuration.browserCapabilities = capabilities;
    }

    @AfterTest
    public void tearDown() {
        closeWebDriver();
    }

}
