package hm1;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.By;   
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;  
import org.openqa.selenium.Keys; 
import static io.github.bonigarcia.seljup.Browser.FIREFOX;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import io.github.bonigarcia.seljup.EnabledIfBrowserAvailable; 
import org.junit.jupiter.api.extension.ExtendWith; 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;

@EnabledIfBrowserAvailable(FIREFOX)
@ExtendWith(SeleniumJupiter.class)
public class FunctionalTest { 
     
    @Test
    public void test_Good_Call(FirefoxDriver driver) throws InterruptedException { 
        
        driver.get("http://localhost:3000/");
        driver.manage().window().setSize(new Dimension(1900, 1020));
        Thread.sleep(1000);

        driver.findElement(By.id("standard-basic")).click();
        driver.findElement(By.id("standard-basic")).sendKeys("Sangalhos");
        driver.findElement(By.cssSelector(".MuiButtonBase-root")).click();

        Thread.sleep(5000);

        String name = driver.findElement(By.id("response-name")).getText(); 

        Assertions.assertEquals(name, "Sangalhos, PT"); 

        driver.close(); 
    }

    @Test
    public void test_Bad_Call(FirefoxDriver driver) throws InterruptedException { 
        
        driver.get("http://localhost:3000/");
        driver.manage().window().setSize(new Dimension(1900, 1020));
        Thread.sleep(1000);

        driver.findElement(By.id("standard-basic")).click();
        driver.findElement(By.id("standard-basic")).sendKeys("");
        driver.findElement(By.cssSelector(".MuiButtonBase-root")).click();

        Thread.sleep(5000);

        String name = driver.findElement(By.id("response-name")).getText(); 

        Assertions.assertEquals(name, "undefined, undefined"); 

        driver.close(); 
    }

    
}
