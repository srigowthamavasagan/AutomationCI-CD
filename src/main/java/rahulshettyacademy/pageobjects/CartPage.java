package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import rahulshettyacademy.abstractcomponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//div[@class='cartSection']/h3")
	List<WebElement> Iteminlist ;
	
	@FindBy(xpath = "//button[text()='Checkout']")
	WebElement checkOutbutton;
	
	public boolean VerifyIteminCartAdded(String Itemname) throws InterruptedException {
		System.out.println(Itemname);
		Thread.sleep(1000);
		boolean flag = Iteminlist.stream().anyMatch(product -> product.getText().contains(Itemname));
		return flag;	
	}
	
	public CheckOutPage gotoCheckOutPage() {
		checkOutbutton.click();
		CheckOutPage checkoutpage=new CheckOutPage(driver);
		return checkoutpage;
	}

	
	
	
}
