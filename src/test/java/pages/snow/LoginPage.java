package pages.snow;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import lib.selenium.SNowWeb;


public class LoginPage extends SNowWeb {
	public LoginPage(RemoteWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		driver.switchTo().frame("gsft_main");
	}

	@FindBy(how=How.ID,using="user_name") 
	private WebElement Username;

	@FindBy(how=How.ID, using = "user_password") 
	private WebElement Password;

	@FindBy(how=How.ID, using = "sysverb_login") 
	private WebElement Login;


	/**
	 * Stores all login page actions (Object's methods )
	 * 
	 * @param Username, password
	 * @return 
	 * @return to dashboard page
	 */

	public LoginPage enterUsername(String username) {
		typeInto(Username, username);
		return this;
	}

	public LoginPage enterPassword(String password) {
		typeInto(Password, password);
		return this;
	}


	public HomePage clickLoginBtn() {
		click(driver,Login);
		return new HomePage(driver);
	}


}

