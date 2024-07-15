package rahulshettyacademy;

import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductPage;
import rahulshettyacademy.testcomponents.BaseTest;
import rahulshettyacademy.testcomponents.Retry;

public class ErrorValidationTest extends BaseTest{
	
	@Test(groups = {"Errorhandling"})
	public void LoginPageErrorcheck() {
		
		landingpage.loginmethod("12sri@gmail.com", "Siva10080");
		System.out.println(landingpage.getErrorMessage());
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
		
	}
	
	@Test(groups = {"Errorhandling"},retryAnalyzer =Retry.class)
	public void LoginPageError() {
		
		landingpage.loginmethod("12sri@gmail.com", "Siva10080");
		System.out.println(landingpage.getErrorMessage());
		Assert.assertEquals("Incorrect email  password.", landingpage.getErrorMessage());
		
	}
	
	@Test
	public void ProductErrorValidationMethod() throws InterruptedException {
		String Itemname="ADIDAS ORIGINAL";
		
		ProductPage productpage = landingpage.loginmethod("12sri@gmail.com", "Siva1008");

		productpage.getProductList();
		productpage.getProductname(Itemname);
		productpage.ProductAddedtoCart(Itemname);
		CartPage cartpage = productpage.gotocartPage();

		boolean flag = cartpage.VerifyIteminCartAdded("ADIDAS ORIGINAL 2");
		Assert.assertFalse(flag);
		
	}
	
	
	
	
	

}
