package rahulshettyacademy.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import rahulshettyacademy.abstractcomponents.AbstractComponent;

public class ProductPage extends AbstractComponent {
	WebDriver driver;
	public ProductPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css = "div[class*='mb-3']")
	List<WebElement> productlist;
	
	@FindBy(id = "toast-container")
	WebElement ProductAddMsg;
	
	
	By addtoCartbutton=By.xpath(".//div[@class='card-body']/button[2]");
	
	By productsby=By.cssSelector("div[class*='mb-3']");
	
	By animating=By.cssSelector(".ng-animating");
	
	By toaster=By.id("toast-container");
	
	public List<WebElement> getProductList() {
		elementToBeVisible(productsby);
		return productlist;
		
	}
	
	
	public WebElement getProductname(String Itemname) {
		WebElement productname= getProductList().stream().filter(product -> product
				.findElement(By.xpath(".//div[@class='card-body']/h5/b")).getText().contains(Itemname)).findFirst()
				.orElse(null);
		return productname;
		
		
	}
	
	public void ProductAddedtoCart(String Itemname) {
		
		WebElement productname=getProductname(Itemname);
		
		productname.findElement(addtoCartbutton).click();
		
		elementToBeVisible(toaster);
		
		elementToBeInvisible(animating);
		
		
		
		
	}
	
	public String getProductMsg() {
		
		return  ProductAddMsg.getText();
		
	}
	
	



}
