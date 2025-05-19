import java.util.List;

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

public class PracticeFormTesting {
    WebDriver driver;
    String url = "https://demoqa.com/automation-practice-form";

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.get(url);
    }

    @Test
    @Order(1)
    void submitForm() {
        driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("Nixon");
        driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("Deb");
        driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("nixon@example.com");
        driver.findElement(By.cssSelector("label[for='gender-radio-1']")).click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                driver.findElement(By.id("submit")));
        driver.findElement(By.id("submit")).click();
        sleep(1000);
        List<WebElement> invalidFields = driver.findElements(By.cssSelector("input:invalid"));

        if (!invalidFields.isEmpty()) {
            System.out.println("Form submission failed due to validation errors.");
            for (WebElement field : invalidFields) {
                System.out.println("Invalid field ID: " + field.getAttribute("id"));
            }
        } else {
            System.out.println("Form submitted successfully or all fields valid.");
        }

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
        // driver.quit();
    }
}
