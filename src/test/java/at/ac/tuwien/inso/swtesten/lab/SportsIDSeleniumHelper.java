package at.ac.tuwien.inso.swtesten.lab;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import at.ac.tuwien.inso.swtesten.util.SeleniumWebDriver;
import cucumber.api.java.After;

public class SportsIDSeleniumHelper {

	private WebDriver driver;
	private String baseUrl;
	private WebDriverWait wait;

	public void setUp() {
		driver = SeleniumWebDriver.getDriver();
		baseUrl = "https://sportsid.risedev.at/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		wait = new WebDriverWait( driver, 30 );
	}
	
	public void goToLogin() {
		driver.get( this.baseUrl );
	}
	
	@After
	public void shutDown() {
		SeleniumWebDriver.closeDriver();
	}

	public void enterUserName(String username) {
		driver.findElement( By.id("input-username") ).sendKeys( username );
	}
	
	public void enterPassword( String password ) {
		driver.findElement( By.id("input-password") ).sendKeys( password );
	}

	public void pressLoginButton() {
		driver.findElement( By.id("login-button") ).click();
	}	

	public void assertUnauthorizedMessageShown() {
		WebElement elem = wait.until(
			ExpectedConditions.visibilityOfElementLocated( By.xpath("//span[contains(text(),'Unauthorized')]") )
		);
		Assert.assertNotNull( elem );
	}

	public void assertWelcomeMessageShown() {
		
		WebElement elem = wait.until(
			ExpectedConditions.visibilityOfElementLocated( By.xpath("//h1[contains(text(),'Welcome')]") )	
		);
		Assert.assertNotNull( elem );
	}

	public void selectLanguage(String value) {
		new Select( driver.findElement(By.id("language")) ).selectByValue( value );
	}


}
