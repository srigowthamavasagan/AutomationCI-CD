package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.abstractcomponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);

	}
	
	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement countryname;
	
	@FindBy(xpath = "//section[contains(@class,'ta-results')]/button[2]")
	WebElement selectcountry;
	
	@FindBy(css = "a[class*='action__submit']")
	WebElement placesubmit;
	
	By countrylist=By.cssSelector("section[class*='ta-results']");
	
			
	
	public void selectCountryName() {
		Actions a = new Actions(driver);
		a.moveToElement(countryname).click().sendKeys("ind").build().perform();
		
		allElementsToBeVisible(countrylist);
		
		selectcountry.click();

	}
	
	
	public ConfirmationPage gotoConfirmPage() {
		
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].click();", placesubmit);
		ConfirmationPage confirmpage=new ConfirmationPage(driver);
		return confirmpage;
	}
	


}
