package tqs.lab4;  

//Junit Imports  
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith; 

//Selenium Imports 
import org.openqa.selenium.chrome.ChromeDriver; 
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.By;   
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;  

// Selenium Jupiter
import io.github.bonigarcia.seljup.SeleniumJupiter;
import static io.github.bonigarcia.seljup.Browser.FIREFOX;
import io.github.bonigarcia.seljup.EnabledIfBrowserAvailable;

//Java Imports 
import java.util.*; 


@EnabledIfBrowserAvailable(FIREFOX)
@ExtendWith(SeleniumJupiter.class)
public class Ex_2_C_Test {
 

 

  @Test
  void dummyTesty(FirefoxDriver driver) {
 

    driver.get("https://blazedemo.com/");
    driver.manage().window().setSize(new Dimension(1900, 1020));
    driver.findElement(By.name("fromPort")).click();
    {
      WebElement dropdown = driver.findElement(By.name("fromPort"));
      dropdown.findElement(By.xpath("//option[. = 'Philadelphia']")).click();
    }
    driver.findElement(By.name("toPort")).click();
    {
      WebElement dropdown = driver.findElement(By.name("toPort"));
      dropdown.findElement(By.xpath("//option[. = 'Rome']")).click();
    }
    driver.findElement(By.cssSelector(".btn-primary")).click();
    driver.findElement(By.cssSelector("tr:nth-child(2) .btn")).click();
    driver.findElement(By.id("inputName")).click();
    driver.findElement(By.id("inputName")).click();
    driver.findElement(By.id("inputName")).sendKeys("Teles");
    driver.findElement(By.cssSelector("form")).click();
    driver.findElement(By.cssSelector(".control-group:nth-child(3) > .controls")).click();
    driver.findElement(By.id("address")).click();
    driver.findElement(By.id("address")).sendKeys("Lordran");
    driver.findElement(By.id("city")).click();
    driver.findElement(By.id("city")).sendKeys("Lindel");
    driver.findElement(By.id("state")).click();
    driver.findElement(By.id("state")).sendKeys("Order");
    driver.findElement(By.id("zipCode")).click();
    driver.findElement(By.id("zipCode")).sendKeys("12345");
    driver.findElement(By.id("cardType")).click();
    {
      WebElement dropdown = driver.findElement(By.id("cardType"));
      dropdown.findElement(By.xpath("//option[. = 'American Express']")).click();
    }
    driver.findElement(By.id("creditCardNumber")).click();
    driver.findElement(By.id("creditCardNumber")).sendKeys("123123");
    driver.findElement(By.cssSelector(".control-group:nth-child(9)")).click();
    driver.findElement(By.id("creditCardMonth")).click();
    driver.findElement(By.id("creditCardYear")).click();
    driver.findElement(By.id("nameOnCard")).click();
    driver.findElement(By.cssSelector("body")).click();
    driver.findElement(By.cssSelector("body")).click();
    driver.findElement(By.id("nameOnCard")).click();
    driver.findElement(By.cssSelector("body")).click();
    driver.findElement(By.id("nameOnCard")).sendKeys("Drake");
    driver.findElement(By.cssSelector(".checkbox")).click();
    driver.findElement(By.cssSelector(".btn-primary")).click();
    driver.findElement(By.cssSelector("h1")).click();
    driver.findElement(By.linkText("home")).click();
  }
}
