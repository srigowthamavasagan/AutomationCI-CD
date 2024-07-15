package rahulshettyacademy.pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.abstractcomponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	



	@FindBy(id="userEmail")
	WebElement uEmail;
	
	@FindBy(id="userPassword")
	WebElement uPassword;
	
	@FindBy(css = "input[type='submit']")
	WebElement submit;
	
	@FindBy(id = "toast-container")
	WebElement errorMsg;
	
	public ProductPage loginmethod(String email,String password) {
		uEmail.sendKeys(email);
		uPassword.sendKeys(password);
		submit.click();
		ProductPage productpage=new ProductPage(driver);
		return productpage;
		
	}
	
	public String getErrorMessage() {
		webElementvisible(errorMsg);
		return errorMsg.getText();
	}
	
	public void gotothis() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	
	

}
