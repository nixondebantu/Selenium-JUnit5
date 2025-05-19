import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TextBoxTesting {
    WebDriver driver;
    String url = "https://demoqa.com/text-box";

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.get(url);
    }

    @Test
    @Order(1)
    void onlyName() {
        String username = "Nixon";
        driver.findElement(By.id("userName")).sendKeys(username);
        submit();
        assertTrue(driver.findElement(By.id("name")).getText().contains(username));
    }

    @Test
    @Order(2)
    void onlyMail() {
        String userEmail = "abcd@example.com";
        driver.findElement(By.id("userEmail")).sendKeys(userEmail);
        submit();
        assertTrue(driver.findElement(By.id("email")).getText().contains(userEmail));
    }

    @Test
    @Order(3)
    void invalidEmailTriggersFieldError() {
        String userEmail = "example.com";
        WebElement input = driver.findElement(By.id("userEmail"));
        input.sendKeys(userEmail);
        submit();
        assertTrue(input.getDomAttribute("class").contains("field-error"), "Should show the error");
    }

    void submit() {
        WebElement submitButton = driver.findElement(By.id("submit"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
        submitButton.click();
    }

    void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    void tearDown() {
        sleep(1000);
        driver.quit();
    }
}
