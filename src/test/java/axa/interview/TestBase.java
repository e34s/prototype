package axa.interview;

import axa.utils.EventListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestBase {

    /*

    FRAGEN

    Erklären des Codes
    Woher kommt der Browser Parameter?
    Wie kann der Browser Parameter optional gemacht werden?
    Welche Konsequenzen hat die @Parameter Annotation?
    Wie kann dieses Problem gelöst werden?
    Ändern des Codes sodass fder Browser sowohl über testng.xml aber auch ohne gesetzt werden kann

    */

    protected static ThreadLocal<EventFiringWebDriver> firingWebDriver = new ThreadLocal<>();
    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();


    @BeforeMethod
    @Parameters(value={"browser"})
    public void setupTest (String browser) throws MalformedURLException {


        DesiredCapabilities capability = new DesiredCapabilities();

        capability.setCapability("browserName", browser);
        capability.setCapability("e34:token", "72aa4d82");
        capability.setCapability("e34:l_testName", "Selenium Test");
        capability.setCapability("video", true);
        capability.setCapability("e34_timeout_per_test_ms", 60000);


        driver.set(new RemoteWebDriver(new URL("https://vm-106.element34.net/wd/hub"), capability));
        driver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        firingWebDriver.set(new EventFiringWebDriver(driver.get()));
        EventListener listener = new EventListener(driver.get());
        firingWebDriver.get().register(listener);

    }

    public WebDriver getDriver() {
        return firingWebDriver.get();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        getDriver().quit();
    }

    @AfterClass
    void terminate () {
        driver.remove();
    }
}
