package tqs.lab4;

//Java Imports
import java.util.logging.Logger; 
import java.io.*;

//Junit Imports 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

//BoniGarcia Imports
import io.github.bonigarcia.wdm.WebDriverManager; 

//Selenium Imports
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

//AssertJ
import static org.assertj.core.api.Assertions.assertThat;


class HelloWorldChromeJupiterTest {
 

    private WebDriver driver;  

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setup() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);    
    }

    @Test
    void test() {
        // Exercise
        String sutUrl = "https://bonigarcia.dev/selenium-webdriver-java/";
        driver.get(sutUrl);  
        String title = driver.getTitle();   

        // Verify
        assertThat(title).isEqualTo("Hands-On Selenium WebDriver with Java"); 
    }

    @AfterEach
    void teardown() {
        driver.quit();  
    }

} 