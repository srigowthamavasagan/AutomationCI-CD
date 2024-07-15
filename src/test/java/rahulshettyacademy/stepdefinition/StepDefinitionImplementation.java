package rahulshettyacademy.stepdefinition;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.ProductPage;
import rahulshettyacademy.testcomponents.BaseTest;

public class StepDefinitionImplementation extends BaseTest {
	public ProductPage productpage;
	public CartPage cartpage;
	public CheckOutPage checkoutpage;
	public ConfirmationPage confirmpage;
	@Given("I landed on ecommerce login page")
	public void  landed_ecommerce_login_page() throws IOException {
		landingpage=launchApplication();
		
	}
	
    @Given("^I want to login with (.+) and (.+) in login page$")
    public void Want_to_login_with_username_password_login_page(String username,String password) {
    	 productpage = landingpage.loginmethod(username,password);

    	
    }
    @When("^I want to select (.+) from cart$") 
    public void want_select_productname_from_cart(String productname) {
    	productpage.getProductList();
		productpage.getProductname(productname);
		productpage.ProductAddedtoCart(productname);
		String prodmsg = productpage.getProductMsg();
		System.out.println(prodmsg);

    	
    	
    }
    
    @And("^I Check the cartpage for (.+) and Checkout$")
    public void  Check_cartpage_prodcutname_and_checkout(String productname) throws InterruptedException {
    	cartpage = productpage.gotocartPage();

	
		 checkoutpage = cartpage.gotoCheckOutPage();

    }
    
    @And ("I fill the particulars and submit the order")
    public void fill_the_particulars_submit_the_order() {
    	checkoutpage.selectCountryName();
		 confirmpage = checkoutpage.gotoConfirmPage();
	
    	
    }
    @Then("I verify the confirm message {string} is displayed")
    public void verify_the_confirm_message_is_displayed(String string) {
    	String confmsg = confirmpage.getConfirmMsg();
		System.out.println(confmsg);
    	
    }
    @Then("I verify the error message {string} is displayed")
    public void verify_the_error_message_is_displayed(String string) {
    	System.out.println(landingpage.getErrorMessage());
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
		
    }
   

}
