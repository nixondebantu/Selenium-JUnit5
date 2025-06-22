import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DemoBlaze {
    private WebDriver driver;
    private static String UserName = "lojexejoke";
    private static String Password = "lojexejoke";

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.get("https://www.demoblaze.com");
        delay(1000);
    }

    @Test
    // User Registration
    void signup() {
        driver.findElement(By.id("signin2")).click();
        delay(500);
        driver.findElement(By.id("sign-username")).sendKeys(UserName);
        driver.findElement(By.id("sign-password")).sendKeys(Password);
        driver.findElement(By.xpath("//*[@id=\"signInModal\"]/div/div/div[3]/button[2]")).click();
        delay(2000);
        assertTrue(driver.switchTo().alert().getText().contains("Sign up successful"));
        driver.switchTo().alert().accept();
    }

    @Test
    // Login
    void loginTest() {
        login();
        assertTrue(driver.findElement(By.id("nameofuser")).getText().contains(UserName));
    }

    @Test
    // Add Products to Cart only the first item here
    void addProduct() {
        login();
        driver.findElement(By.xpath("/html/body/div[5]/div/div[1]/div/a[3]")).click();
        delay(1000);
        WebElement product1 = driver.findElement(By.xpath("/html/body/div[5]/div/div[2]/div/div[1]/div/div/h4/a"));
        assertEquals("Sony vaio i5", product1.getText());
        product1.click();
        delay(2000);
        for (int i = 0; i < 3; i++) {
            driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[2]/div/a")).click();
            delay(1500);
            assertTrue(driver.switchTo().alert().getText().contains("Product added"));
            driver.switchTo().alert().accept();
        }
    }

    @Test
    // Cart and Checkout
    void checkOut() {
        login();
        driver.findElement(By.id("cartur")).click();
        delay(2000);
        driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[2]/button")).click();
        delay(500);
        driver.findElement(By.id("name")).sendKeys(UserName);
        driver.findElement(By.id("country")).sendKeys(UserName);
        driver.findElement(By.id("city")).sendKeys(UserName);
        driver.findElement(By.id("card")).sendKeys(UserName);
        driver.findElement(By.id("month")).sendKeys(UserName);
        driver.findElement(By.id("year")).sendKeys(UserName);
        driver.findElement(By.xpath("//*[@id=\"orderModal\"]/div/div/div[3]/button[2]")).click();
        delay(4000);
        assertEquals("Thank you for your purchase!",
                driver.findElement(By.cssSelector(".sweet-alert.visible h2")).getText());
    }

    @Test
    // Send a Contact Message
    void contact() {
        driver.findElement(By.xpath("/html/body/nav/div[1]/ul/li[2]/a")).click();
        delay(1000);
        driver.findElement(By.id("recipient-email")).sendKeys(UserName + "@example.com");
        driver.findElement(By.id("recipient-name")).sendKeys(UserName);
        driver.findElement(By.id("message-text")).sendKeys("Add uid for the buttons please");
        driver.findElement(By.xpath("//*[@id=\"exampleModal\"]/div/div/div[3]/button[2]")).click();
        delay(300);
        assertTrue(driver.switchTo().alert().getText().contains("Thanks for the message"));
    }

    void login() {
        driver.findElement(By.id("login2")).click();
        delay(500);
        driver.findElement(By.id("loginusername")).sendKeys(UserName);
        driver.findElement(By.id("loginpassword")).sendKeys(Password);
        driver.findElement(By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[2]")).click();
        delay(3000);
    }

    void delay(int mili) {
        try {
            Thread.sleep(mili);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    void end() {
        delay(1000);
        driver.quit();
    }
}
