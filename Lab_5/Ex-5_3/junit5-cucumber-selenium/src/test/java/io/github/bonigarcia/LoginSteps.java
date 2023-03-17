 
package io.github.bonigarcia;

//Java Imports 
import java.util.concurrent.TimeUnit;
import java.util.NoSuchElementException;
import java.time.Duration;

//Selenium Imports 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

// Cucumber Imports 
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;  

//Pages Imports
import io.github.bonigarcia.webpages.ChoosePage;
import io.github.bonigarcia.webpages.ConfirmationPage;
import io.github.bonigarcia.webpages.FormPage;
import io.github.bonigarcia.webpages.HomePage; 

//Hamcrest Imports
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoginSteps {

    private WebDriver driver;
    
    HomePage home;
    ChoosePage choosePage;
    FormPage formPage;

    @When("I navigate to {string}")
    public void iNavigateTo(String url) {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 

        home = new HomePage(driver);  
        choosePage = new ChoosePage(driver); 
        formPage = new FormPage(driver); 
    }

    @When("I choose the origin:{string} and the destiny:{string}")
    public void i_choose_the_origin_and_the_destiny(String string, String string2) {
        home.setOrigin(string); 
        home.setDestiny(string2); 
        home.clickOnFindButton();
    }
    
    @When("I select one of the fligths")
    public void i_select_one_of_the_fligths() {
        choosePage.clickOnFindButton("UA234"); 
    }

    @When("I write my name: {string}")
    public void i_write_my_name(String string) {
        formPage.setName(string);
    }
    @When("I write my address:{string}")
    public void i_write_my_address(String string) {
        formPage.setAddress(string);
    }
    @When("I write my city:{string}")
    public void i_write_my_city(String string) {
        formPage.setCity(string);
    }
    @When("I write my State:{string}")
    public void i_write_my_state(String string) {
        formPage.setState(string);
    }
    @When("I write my Zip:{string}")
    public void i_write_my_zip(String string) {
        formPage.setZip(string);
    }
    @When("I select my Card Type:{string}")
    public void i_select_my_card_type(String string) {
        formPage.setCardType(string);
    }
    @When("I write my Card Number:{string}")
    public void i_write_my_card_number(String string) {
        formPage.setCardNumber(string);
    }
    @When("I write my Credit Card month:{string}")
    public void i_write_my_credit_card_month(String string) {
        formPage.setCardMonth(string);
    }
    @When("I write my Credit Card Year:{string}")
    public void i_write_my_credit_card_year(String string) {
        formPage.setcreditCardYear(string);
    }
    @When("I write the Name on the Card:{string}")
    public void i_write_the_name_on_the_card(String string) {
        formPage.setNameOnCard(string);
    }
    @When("Click to confirm")
    public void click_to_conrim() {
        formPage.clickButton();
    }
    @Then("I should be in the {string} page")
    public void i_should_be_in_the_page(String string) throws java.lang.InterruptedException{ 

        new WebDriverWait(driver,Duration.ofSeconds(1)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {  
                return d.getTitle().equals(string);
            }
        });
        
    }

}
