package rahulshettyacademy.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class BaseTest {
	
	
	public WebDriver driver;
	public LandingPage landingpage;
	
	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\rahulshettyacademy\\dataresources\\GlobalData.properties");
		;
		prop.load(fis);
		
		String browsername=System.getProperty("browser") !=null ?
		System.getProperty("browser") : prop.getProperty("browser");
		
	
			
		
		if (browsername.contains("chrome")) {
			ChromeOptions options=new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			
			
			if(browsername.contains("headless")) {
				options.addArguments("headless");
				
			}
		
			 driver = new ChromeDriver(options);
			 driver.manage().window().setSize(new Dimension(1490,900));
		}

		else if (browsername.contains("edge")) {

			System.setProperty("webdriver.edge.driver", "C:\\Users\\User\\Documents\\msedgedriver.exe");
			driver = new EdgeDriver();

		}

		else if(browsername.contains("firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\User\\Documents\\firefoxdriver.exe");
			 driver = new FirefoxDriver();
			
			

		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;

	}
	
public List<HashMap<String, String>> ConvertJsonDatatoString(String filepath) throws IOException {
		
		
		
		File file=new File(filepath);
		String jsoncontent=FileUtils.readFileToString(file, StandardCharsets.UTF_8);
		
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsoncontent, new  TypeReference<List<HashMap<String,String>>>() {});
	   return data;
	
	
	}

public String getScreenshot(String testcasename,WebDriver driver) throws IOException {
	TakesScreenshot ts=(TakesScreenshot)driver;
	File src=ts.getScreenshotAs(OutputType.FILE);
	File dest=new File(System.getProperty("user.dir")+"\\reports\\"+testcasename+".png");
	FileUtils.copyFile(src, dest);
	return System.getProperty("user.dir")+"\\reports\\"+testcasename+".png";
	
}
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		
		driver=initializeDriver(); 
		landingpage=new LandingPage(driver);
		landingpage.gotothis();
		return landingpage;
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
	
	

}
