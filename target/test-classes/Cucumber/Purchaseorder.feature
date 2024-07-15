
@tag
Feature: Submit the order
  I want to use this template for my feature file
  
  Background:
  Given I landed on ecommerce login page


  @PurchaseOrder
  Scenario: Product select and submit
      Given I want to login with <username> and <password> in login page
    When I want to select <productname> from cart 
    And I Check the cartpage for <prodcutname> and Checkout
    And I fill the particulars and submit the order
    Then I verify the confirm message "THANKYOU FOR THE ORDER." is displayed
    

    Examples: 
    |username						|password						|productname		|
    |12sri@gmail.com		|Siva1008						|ADIDAS ORIGINAL|
    |akul9523@gmail.com	|Lukajeremic23!			|ZARA COAT 3		|
