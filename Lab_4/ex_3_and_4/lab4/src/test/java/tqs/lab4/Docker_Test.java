package tqs.lab4;

//Junit Imports  
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

//Selenium Imports 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

// Selenium Jupiter
import io.github.bonigarcia.seljup.SeleniumJupiter;
import io.github.bonigarcia.seljup.BrowserType;
import io.github.bonigarcia.seljup.DockerBrowser;  

//Java Imports 
import java.util.concurrent.TimeUnit;

//Hamcrest Imports
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

//Pages Imports
import tqs.lab4.webpages.ChoosePage;
import tqs.lab4.webpages.ConfirmationPage;
import tqs.lab4.webpages.FormPage;
import tqs.lab4.webpages.HomePage;  

@ExtendWith(SeleniumJupiter.class)
public class Docker_Test { 

    @Test
    public void docker(@DockerBrowser(type = BrowserType.FIREFOX) WebDriver driver) throws InterruptedException { 

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        HomePage home = new HomePage(driver);  
        ChoosePage choosePage = new ChoosePage(driver); 
        FormPage formPage = new FormPage(driver); 
      
        home.setOrigin("Philadelphia"); 
        home.setDestiny("Rome"); 
        home.clickOnFindButton(); 

        choosePage.clickOnFindButton("UA234"); 

        formPage.setName("Teles");
        formPage.setAddress("Lordran");
        formPage.setCity("Lindel");
        formPage.setState("Order");
        formPage.setZip("12345");
        formPage.setCardType("American Express");
        formPage.setCardNumber("123123");
        formPage.setCardMonth("11");
        formPage.setcreditCardYear("2077");
        formPage.setNameOnCard("Drake");
        formPage.clickButton();

        ConfirmationPage confirmationPage = new ConfirmationPage(driver); 
        Thread.sleep(2000);   
        assertThat(confirmationPage.getTitle(), is("BlazeDemo Confirmation"));
        driver.close();
        return;
    }

    
}