package lib.selenium;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
public class ReusableFunctions{

	public RemoteWebDriver driver;
	public String URL;
	
	/**
	 * 
	 * Constructor load all global properties
	 * 
	 */

	public ReusableFunctions(){
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("./src/test/resources/global.properties")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		URL = prop.getProperty("URL");

	}

	/**
	 * 
	 * Browser Enum - Current implementation has Chrome, Firefox and Edge
	 */

	public enum Browser{CHROME, FIREFOX, EDGE}

	/**
	 * Opens up given Browser. 
	 * Change the desired browser in the global.properties file 
	 * 
	 * @param browser Enum 
	 * @return browser instance 
	 */

	public WebDriver OpenBrowser(Browser browser) {
		if(browser == browser.CHROME) {
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			//WebDriverManager.chromedriver().version("").setup();
			driver = new ChromeDriver();
		}else if(browser == browser.FIREFOX) {
			WebDriverManager.firefoxdriver().version("76.0.3809.100").setup();
			driver = new FirefoxDriver();
		}else {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;

	}

	/**
	 * Method used to type in given data into text fields
	 * 
	 * @param element
	 * @param data
	 */

	@Step("The element is typed with {data}")
	public void typeInto(WebElement element, String data) {
		try {
			element.clear();
			element.click();
			element.sendKeys(data);
			takeSnap();
		} catch (InvalidElementStateException e) {
			e.printStackTrace();
		}catch(WebDriverException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method used to click given element 
	 * 
	 * @param element
	 */
	
	@Step("The element is clicked")
	public void click(RemoteWebDriver driver, WebElement element){
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			takeSnap();
		} catch (WebDriverException e) {
			e.printStackTrace();
		}


	}
	
	/**
	 * Captures screenshot and attaches to allure reporting
	 * @return
	 */

	@Attachment("Screenshot")
	public byte[] takeSnap() {
		byte[] screenshotBytes =  driver.getScreenshotAs(OutputType.BYTES);
		return screenshotBytes; 
	}
	
	/**
	 * Method that closes browser instance
	 * 
	 */
	
	public void closeBrowser() {
		try {
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method that switches to a frame
	 * 
	 */
	
	@Step("Switched to the frame using {frameElement}")
	public void switchToFrame(WebElement frameElement) {
		try {
			driver.switchTo().frame(frameElement);
			takeSnap();
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not switch to frame using "+frameElement);
			e.printStackTrace();
		}
	}

	//Add more actions 
}



