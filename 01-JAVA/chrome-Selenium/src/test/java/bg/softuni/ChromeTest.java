package bg.softuni;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.net.URL;

public class ChromeTest {
    private static final String CHROME_DRIVER_FILE_NAME = "chromedriver.exe";

    private ChromeDriver chromeDriver;

    @BeforeEach
    void setUp() {
        File file = new File(findFile());

        ChromeDriverService service = new ChromeDriverService.Builder().
                usingDriverExecutable(file).
                build();

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
//        chromeOptions.addArguments("--start-maximized");

        chromeDriver = new ChromeDriver(service, chromeOptions);
    }

    @AfterEach
    void tearDown() {
        chromeDriver.quit();
    }

    @Test
    void testLogin() {
        //отвори страницата на вече работещия server localhost:8080
        chromeDriver.get("http://localhost:8080/users/login");

        //намери тези 2 html елемента
        WebElement userNameInput = chromeDriver.findElement(By.name("username"));
        WebElement passwordInput = chromeDriver.findElement(By.name("password"));

        //пише по браузъра в тези html елементи с тези данни
        userNameInput.sendKeys("lachezar.balev@gmail.com");
        passwordInput.sendKeys("topsecret");

        //from the element part of the form, then submit the whole form
        passwordInput.submit();

        WebElement h5greeting = chromeDriver.findElement(By.tagName("h5"));
        String h5Text = h5greeting.getText();

        Assertions.assertTrue(h5Text.contains("Welcome"));
        Assertions.assertTrue(h5Text.contains("Lucho Balev"));
    }

    private static String findFile() {
        ClassLoader classLoader = ChromeTest.class.getClassLoader();
        URL url = classLoader.getResource(CHROME_DRIVER_FILE_NAME);
        if (url == null) {
            Assertions.fail("Unable to locate chrome driver");
        }
        return url.getFile();
    }

}
