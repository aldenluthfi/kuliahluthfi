package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(
        webEnvironment = WebEnvironment.RANDOM_PORT
)
@ExtendWith({SeleniumJupiter.class})
class HomePageFunctionalTest {
    @LocalServerPort
    private int serverPort;
    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;
    private String baseUrl;

    HomePageFunctionalTest() {
    }

    @BeforeEach
    void setupTest() {
        this.baseUrl = String.format("%s:%d", this.testBaseUrl, this.serverPort);
    }

    @Test
    void pageTitle_isCorrect(ChromeDriver driver) throws Exception {
        driver.get(this.baseUrl);
        String pageTitle = driver.getTitle();
        Assertions.assertEquals("ADV Shop", pageTitle);
    }

    @Test
    void welcomeMessage_homePage_isCorrect(ChromeDriver driver) throws Exception {
        driver.get(this.baseUrl);
        String welcomeMessage = driver.findElement(By.tagName("h1")).getText();
        Assertions.assertEquals("Welcome", welcomeMessage);
    }
}
