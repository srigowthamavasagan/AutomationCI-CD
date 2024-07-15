
@tag
Feature: Title of your feature
  I want to use this template for my feature file

    @ErrorValidation
  Scenario: Login page error check
      Given I landed on ecommerce login page
      When I want to login with <username> and <password> in login page
     Then I verify the error message "Incorrect email or password." is displayed
    

    Examples: 
    |username						|password						|productname		|
       |akul9523@gmail.com	|Lukajeremic2u3!			|ZARA COAT 3		|