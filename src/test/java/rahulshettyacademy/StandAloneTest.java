package rahulshettyacademy;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductPage;
import rahulshettyacademy.testcomponents.BaseTest;

public class StandAloneTest extends BaseTest {

	@Test(groups = { "Purchase" },dataProvider = "getData")
	public void submitOrderTest(HashMap<String,String> input) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		
		// LandingPage landingpage=launchApplication();
		ProductPage productpage = landingpage.loginmethod(input.get("email"),input.get("pwd"));

		productpage.getProductList();
		productpage.getProductname(input.get("item"));
		productpage.ProductAddedtoCart(input.get("item"));
		String prodmsg = productpage.getProductMsg();
		System.out.println(prodmsg);

		CartPage cartpage = productpage.gotocartPage();

		boolean flag = cartpage.VerifyIteminCartAdded(input.get("item"));
		Assert.assertTrue(flag);
		CheckOutPage checkoutpage = cartpage.gotoCheckOutPage();

		checkoutpage.selectCountryName();
		ConfirmationPage confirmpage = checkoutpage.gotoConfirmPage();
		String confmsg = confirmpage.getConfirmMsg();
		System.out.println(confmsg);
		String confoid = confirmpage.getOrderIds();
		System.out.println(confoid);

		OrderPage orderpage = checkoutpage.gotoOrderpage();

		orderpage.verifyOrderItPresent(confoid);
		// Assert.assertEquals(OrdId, confoid);

	}

	@Test(dependsOnMethods = { "submitOrderTest" })
	public void OrderVerification() {
		String Itemname = "ADIDAS ORIGINAL";
		landingpage.loginmethod("12sri@gmail.com", "Siva1008");
		OrderPage orderpage = landingpage.gotoOrderpage();

		boolean flag = orderpage.VerifyOrderisDisplay(Itemname);
		Assert.assertTrue(flag);
		System.out.println(flag);

	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		/*Object[][] data=new Object[2][3];
		data[0][0]="12sri@gmail.com";
		data[0][1]="Siva1008";
		data[0][2]="ADIDAS ORIGINAL";
		data[1][0]="akul9523@gmail.com";
		data[1][1]="Lukajeremic23!";
		data[1][2]="ZARA COAT 3";
			
	return data;*/
		
	//	return new Object[][] {{"12sri@gmail.com","Siva1008","ADIDAS ORIGINAL"},{"akul9523@gmail.com","Lukajeremic23!","ZARA COAT 3"}};
	
	/*	HashMap<String,String> map=new HashMap<String, String>();
		map.put("email", "12sri@gmail.com");
		map.put("pwd", "Siva1008");
		map.put("item", "ADIDAS ORIGINAL");
		HashMap<String,String> map1=new HashMap<String, String>();
		map1.put("email", "akul9523@gmail.com");
		map1.put("pwd", "Lukajeremic23!");
		map1.put("item", "ZARA COAT 3");
	
	return new Object[][] {{map},{map1}};
	*/
		
		List<HashMap<String, String>> data=ConvertJsonDatatoString(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	
	
	}
}
