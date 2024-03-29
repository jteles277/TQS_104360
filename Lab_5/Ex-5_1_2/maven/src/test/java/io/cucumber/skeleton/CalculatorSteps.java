package io.cucumber.skeleton;

//Hamcrest Imports
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

// Cucumber Imports
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

// Model Imports
import io.cucumber.skeleton.Calculator;

public class CalculatorSteps {
    private Calculator calc;

    @Given("a calculator I just turned on")
    public void setup() {
        calc = new Calculator();
    }

    @When("I add {int} and {int}")
    public void add(int arg1, int arg2) {
        calc.push(arg1);
        calc.push(arg2);
        calc.push("+");
    }

    @When("I substract {int} to {int}")
    public void substract(int arg1, int arg2) {
        calc.push(arg1);
        calc.push(arg2);
        calc.push("-");
    }

    @Then("the result is {int}")
    public void the_result_is(double expected) {
        assertThat(expected, is(calc.value())); 
    }

}
