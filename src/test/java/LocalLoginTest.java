import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class LocalLoginTest {

    private WebDriver driver;
    private String pageUrl;

    @BeforeClass
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        File htmlFile = new File("webpage/login.html");
        pageUrl = htmlFile.toURI().toString();
    }

    @Test(priority = 1)
    public void validLoginTest() {
        driver.get(pageUrl);

        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submitBtn"));

        usernameInput.clear();
        usernameInput.sendKeys("student");
        passwordInput.clear();
        passwordInput.sendKeys("12345");
        submitButton.click();

        WebElement messageElement = driver.findElement(By.id("message"));
        String actualMessage = messageElement.getText();
        Assert.assertEquals(actualMessage, "Login successful!", "Valid login should display 'Login successful!'");
    }

    @Test(priority = 2)
    public void invalidLoginTest() {
        driver.get(pageUrl);

        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.id("submitBtn"));

        usernameInput.clear();
        usernameInput.sendKeys("wrongUser");
        passwordInput.clear();
        passwordInput.sendKeys("wrongPass");
        submitButton.click();

        WebElement messageElement = driver.findElement(By.id("message"));
        String actualMessage = messageElement.getText();
        Assert.assertEquals(actualMessage, "Invalid username or password!", "Invalid login should display error message");
    }

    @Test(priority = 3)
    public void checkBackgroundColor() {
        driver.get(pageUrl);

        WebElement body = driver.findElement(By.tagName("body"));
        String actualColor = body.getCssValue("background-color");

        // Expected background color: lightblue (rgba(173, 216, 230, 1))
        Assert.assertEquals(actualColor, "rgba(173, 216, 230, 1)", "Background color should be lightblue rgba(173, 216, 230, 1)");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
