package io.cucumber.skeleton;

// Cucumber Imports
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;

public class StepDefinitions {
    
    @Given("I have {int} cukes in my belly")
    public void I_have_cukes_in_my_belly(int cukes) {
   
        Belly belly = new Belly();
        belly.eat(cukes);
    }

    @When("I wait {int} hour")
    public void i_wait_hour(Integer int1) {
    
        System.out.println("[DEBUG]\tWainting 1 hour");
    }

    @Then("my belly should growl")
    public void my_belly_should_growl() { 

        System.out.println("[DEBUG]\t Belly is growling");
    }
}
