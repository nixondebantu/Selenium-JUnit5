import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Testing {
    WebDriver driver;
    String url = "https://practicetestautomation.com/practice-test-login/";

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
    }

    @Test
    @Order(1)
    void positiveLogIn() throws InterruptedException {
        login("student", "Password123");
        Thread.sleep(1000);
        assertAll(
                () -> assertEquals("https://practicetestautomation.com/logged-in-successfully/",
                        driver.getCurrentUrl()),
                () -> assertTrue(driver.findElement(By.className("has-text-align-center")).getText()
                        .contains("successfully logged in")),
                () -> assertTrue(
                        driver.findElement(By.className("wp-block-button__link")).getText().contains("Log out")));

    }

    @Test
    @Order(2)
    void negativeUsername() {
        login("incorrectUser", "Password123");
        assertTrue(driver.findElement(By.id("error")).getText().contains("Your username is invalid!"));
    }

    @Test
    @Order(3)
    void negativePassword() {
        login("student", "incorrectPassword");
        assertTrue(driver.findElement(By.id("error")).getText().contains("Your password is invalid!"));
    }

    void login(String username, String password) {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("submit")).click();
    }

    @AfterEach
    void tearDown() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
