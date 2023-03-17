Feature: Blaze Demo Usage

  Scenario: Successful Usage
    When I navigate to "https://blazedemo.com/"
    And I choose the origin:"Philadelphia" and the destiny:"Rome" 
    And I select one of the fligths
    And I write my name: "Teles" 
    And I write my address:"Lordran"
    And I write my city:"Lindel"
    And I write my State:"Order"
    And I write my Zip:"12345"
    And I select my Card Type:"American Express"
    And I write my Card Number:"123123"
    And I write my Credit Card month:"11"
    And I write my Credit Card Year:"2077"
    And I write the Name on the Card:"Drake"
    And Click to confirm
    Then I should be in the "BlazeDemo Confirmation" page

  
 