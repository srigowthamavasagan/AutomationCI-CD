package rahulshettyacademy;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.Iterator;
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

public class StandAloneDummyTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client/");
		String Itemname = "ADIDAS ORIGINAL";
		driver.findElement(By.id("userEmail")).sendKeys("anshika@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Iamking@00");
		driver.findElement(By.cssSelector("input[type='submit']")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));

		System.out.println(driver.findElement(By.id("toast-container")).getText());

		driver.findElement(By.id("userEmail")).clear();
		driver.findElement(By.id("userPassword")).clear();

		driver.findElement(By.id("userEmail")).sendKeys("anshika@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Iamking@000");
		driver.findElement(By.cssSelector("input[type='submit']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class*='mb-3']")));

		// List<WebElement>
		// productlist=driver.findElements(By.cssSelector("div[class*='mb-3']"));
		/*
		 * List<WebElement>
		 * productlist=driver.findElements(By.xpath("//div[@class='card-body']/h5/b"));
		 * 
		 * for(int i=0;i<productlist.size();i++) { String
		 * produtname=productlist.get(i).getText();
		 * if(produtname.equalsIgnoreCase(Itemname)) { System.out.println(produtname);
		 * driver.findElements(By.xpath("//div[@class='card-body']/button[2]")).get(i).
		 * click(); break; } }
		 * 
		 */

		List<WebElement> productlist = driver.findElements(By.cssSelector("div[class*='mb-3']"));

		WebElement productname = productlist.stream().filter(product -> product
				.findElement(By.xpath(".//div[@class='card-body']/h5/b")).getText().contains(Itemname)).findFirst()
				.orElse(null);
		productname.findElement(By.xpath(".//div[@class='card-body']/button[2]")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));

		String ProductAddMsg = driver.findElement(By.id("toast-container")).getText();
		System.out.println(ProductAddMsg);

		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

		List<WebElement> Iteminlist = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		boolean flag = Iteminlist.stream().anyMatch(product -> product.getText().contains(Itemname));
		Assert.assertTrue(flag);

		driver.findElement(By.xpath("//button[text()='Checkout']")).click();

		WebElement countryname = driver.findElement(By.xpath("//input[@placeholder='Select Country']"));
		Actions a = new Actions(driver);
		a.moveToElement(countryname).click().sendKeys("ind").build().perform();

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("section[class*='ta-results']")));

		driver.findElement(By.xpath("//section[contains(@class,'ta-results')]/button[2]")).click();

		WebElement submit = driver.findElement(By.cssSelector("a[class*='action__submit']"));
		JavascriptExecutor je = (JavascriptExecutor) driver;
		je.executeScript("arguments[0].click();", submit);

		String ActualMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		System.out.println(ActualMsg);
		Assert.assertEquals(ActualMsg, "THANKYOU FOR THE ORDER.");
		String OId = driver.findElement(By.cssSelector("label[class*='ng-star']")).getText();
		System.out.println(OId);
		String OrderId = OId.split(" ")[1].trim();
		System.out.println(OrderId);

		driver.findElement(By.xpath("//button[@routerlink='/dashboard/myorders']")).click();

		List<WebElement> Oids = driver.findElements(By.xpath("//tbody/tr/th"));
		for (int i = 0; i < Oids.size(); i++) {
			String OrdId = Oids.get(i).getText();
			if (OrdId.contains(OrderId)) {
				System.out.println("Id is" + OrdId);

				Assert.assertEquals(OrdId, OrderId);
			}

		}

	}

}
