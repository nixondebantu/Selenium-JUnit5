import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class AutomationExercise {

    WebDriver driver;
    private final String username = "nixon6";
    private final String password = "12345";

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.get("https://automationexercise.com/");
    }

    @Test
    // Register User
    void register() {
        driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[4]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/input[2]")).sendKeys(username);
        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/input[3]"))
                .sendKeys(username + "@example.com");
        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/button")).click();
        driver.findElement(By.id("uniform-id_gender1")).click();
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("days")).sendKeys("1");
        driver.findElement(By.id("months")).sendKeys("1");
        driver.findElement(By.id("years")).sendKeys("2000");
        driver.findElement(By.id("newsletter")).click();
        driver.findElement(By.id("optin")).click();
        driver.findElement(By.id("first_name")).sendKeys("Mr.");
        driver.findElement(By.id("last_name")).sendKeys(username);
        driver.findElement(By.id("company")).sendKeys("SWE");
        driver.findElement(By.id("address1")).sendKeys("IICT Building, SUST");
        driver.findElement(By.id("address2")).sendKeys("Sylhet");
        driver.findElement(By.id("country")).sendKeys("Singapore");
        driver.findElement(By.id("state")).sendKeys("Sylhet");
        driver.findElement(By.id("city")).sendKeys("Sylhet");
        driver.findElement(By.id("zipcode")).sendKeys("3100");
        driver.findElement(By.id("mobile_number")).sendKeys("01711223344");
        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/div/form/button")).click();
        delay(4000);
        assertEquals("ACCOUNT CREATED!", driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/h2")).getText());
        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/div/a")).click();
        delay(2000);
        assertTrue(driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[10]/a")).getText()
                .contains(username));
        driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[4]/a")).click();
        delay(2000);
        assertEquals("https://automationexercise.com/login", driver.getCurrentUrl());
    }

    @Test
    // Search Products and Verify Cart After Login
    void searchProduct() {
        driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[2]/a")).click();
        delay(2000);
        assertEquals("https://automationexercise.com/products", driver.getCurrentUrl());
        driver.findElement(By.id("search_product")).sendKeys("Soft Stretch Jeans");
        driver.findElement(By.id("submit_search")).click();
        delay(2000);
        driver.findElement(By.xpath("/html/body/section[2]/div/div/div[2]/div/div[2]/div/div[1]/div[1]/a")).click();
        delay(2000);
        assertTrue(driver.findElement(By.className("modal-title")).getText().contains("Added!"));
        driver.findElement(By.className("btn-success")).click();
        delay(500);
        login();
        driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[3]/a")).click();
        delay(2000);
        assertEquals("https://automationexercise.com/view_cart", driver.getCurrentUrl());
        assertTrue(driver.findElement(By.className("cart_description")).getText().contains("Soft Stretch Jeans"));
    }

    @Test
    // Add review on product
    void addReview() {
        login();
        driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[2]/a")).click();
        delay(2000);
        assertEquals("https://automationexercise.com/products", driver.getCurrentUrl());
        driver.findElement(By.id("search_product")).sendKeys("Soft Stretch Jeans");
        driver.findElement(By.id("submit_search")).click();
        delay(2000);
        driver.findElement(By.xpath("/html/body/section[2]/div/div/div[2]/div/div[2]/div/div[2]/ul/li/a")).click();
        delay(1000);
        driver.findElement(By.id("name")).sendKeys(username);
        driver.findElement(By.id("email")).sendKeys(username + "@example.com");
        driver.findElement(By.id("review")).sendKeys("Nice Jeans, but overpriced!!");
        driver.findElement(By.id("button-review")).click();
        assertTrue(driver.findElement(By.id("review-section")).isDisplayed());
    }

    void login() {
        driver.get("https://automationexercise.com/login");
        // delay(2000);
        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/input[2]"))
                .sendKeys(username + "@example.com");
        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/input[3]")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/button")).click();
        delay(2000);
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
        delay(2000);
        driver.quit();
    }
}
