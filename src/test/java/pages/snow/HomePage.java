package pages.snow;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import lib.selenium.SNowWeb;

public class HomePage extends SNowWeb {
	public HomePage(RemoteWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	@FindBy(how=How.ID,using="filter") 
	private WebElement search;

	@FindBy(how=How.LINK_TEXT, using = "Open") 
	private WebElement openLink;

	@FindBy(how=How.XPATH, using = "//span[@id='incident_hide_search']//input[@placeholder='Search']") 
	private WebElement searchbox;
	
	/**
	 * Stores all home page actions (Object's methods )
	 *
	 */

	public HomePage searchIncident(String searchWord ) {
		typeInto(search, searchWord);
		return this;
	}

	public HomePage clickOnOpen() {
		click(driver,openLink);
		return this;
	}
	public HomePage switchToFrame() {
		driver.switchTo().frame("gsft_main");
		return this;
	}
	public HomePage searchIncidentNumber(String incidentNumber) {
		typeInto(searchbox, incidentNumber);
		searchbox.sendKeys(Keys.ENTER);
		return this;
	}



}

