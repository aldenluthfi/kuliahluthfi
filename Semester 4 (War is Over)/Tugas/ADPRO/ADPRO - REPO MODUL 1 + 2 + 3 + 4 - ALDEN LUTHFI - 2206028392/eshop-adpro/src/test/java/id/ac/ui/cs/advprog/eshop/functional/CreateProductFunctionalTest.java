package id.ac.ui.cs.advprog.eshop.functional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.github.bonigarcia.seljup.SeleniumJupiter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        this.baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void createForm_isDisplayed(ChromeDriver driver) {
        driver.get(baseUrl + "/product/create");
        String pageTitle = driver.getTitle();
        assertEquals("Create New Product", pageTitle);
    }

    @Test
    void createProduct_isSuccessful(ChromeDriver driver) {
        String productName = "Sampo Cap Bambang";
        driver.get(baseUrl + "/product/create");
        driver.findElement(By.id("nameInput")).sendKeys(productName);
        driver.findElement(By.id("quantityInput")).sendKeys("100");
        driver.findElement(By.tagName("button")).click();

        // select the first row of the table and get the first cell
        String itemName = driver.findElement(By.cssSelector("table tbody tr:first-child td:first-child")).getText();
        assertEquals(productName, itemName);
    }
}