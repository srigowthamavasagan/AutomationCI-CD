package rahulshettyacademy;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductPage;

public class StandAloneTestwithPOM {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		String Itemname = "ADIDAS ORIGINAL";
		
		LandingPage landingpage=new LandingPage(driver);
		landingpage.gotothis();
		ProductPage productpage=landingpage.loginmethod("12sri@gmail.com","Siva1008");
		
		
		productpage.getProductList();
		productpage.getProductname(Itemname);
		productpage.ProductAddedtoCart(Itemname);
		String prodmsg=productpage.getProductMsg();
		System.out.println(prodmsg);
		

		CartPage cartpage=productpage.gotocartPage();
				
		boolean flag = cartpage.VerifyIteminCartAdded(Itemname);
		Assert.assertTrue(flag);
		CheckOutPage checkoutpage=cartpage.gotoCheckOutPage();
		
		

	
		checkoutpage.selectCountryName();
		ConfirmationPage confirmpage=checkoutpage.gotoConfirmPage();
		String confmsg=confirmpage.getConfirmMsg();
		System.out.println(confmsg);
		String confoid=confirmpage.getOrderIds();
		System.out.println(confoid);
		
		
		OrderPage orderpage=checkoutpage.gotoOrderpage();
		
		orderpage.verifyOrderItPresent(confoid);
		//Assert.assertEquals(OrdId, confoid);
		
		

	}

}
