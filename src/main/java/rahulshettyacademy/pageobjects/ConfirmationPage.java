package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import rahulshettyacademy.abstractcomponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {
	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".hero-primary")
	WebElement ActualMsg;

	@FindBy(css = "label[class*='ng-star']")
	WebElement Iddetail;

	public String getConfirmMsg() {
		return ActualMsg.getText();
	}

	public String getOrderIds() {
		String OId = Iddetail.getText();
		System.out.println(OId);
		String OrderId = OId.split(" ")[1].trim();
		return OrderId;
	}

}
