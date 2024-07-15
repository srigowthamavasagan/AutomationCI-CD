package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import rahulshettyacademy.abstractcomponents.AbstractComponent;

public class OrderPage  extends AbstractComponent {
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//tbody/tr/th")
	List<WebElement> Oids;
	
	@FindBy(xpath="//tbody/tr/td[2]")
	List<WebElement> productsincart;
	
	public void verifyOrderItPresent(String OrderId) {
		
		
		for (int i = 0; i < Oids.size(); i++) {
			String OrdId = Oids.get(i).getText();
			if (OrdId.contains(OrderId)) {
				System.out.println("Id is" + OrdId);

			
			}

		}
		
	}
	
	public Boolean VerifyOrderisDisplay(String productname) {
		Boolean flag= productsincart.stream().anyMatch(cart -> cart.getText().contains(productname));
		return flag;
	}
	
	
	
	
}
