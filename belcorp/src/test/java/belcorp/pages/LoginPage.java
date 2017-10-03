/**
 * 
 */
package belcorp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * @author eduardo.araya
 *
 */
public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage (WebDriver ldriver)
	{
		this.driver = ldriver;
		
	}
	
	@FindBy(how= How.XPATH, using =".//*[@id='navbar']/ul[2]/li[3]/div")
	@CacheLookup
	WebElement loginButtonNav;
	
	@FindBy (how = How.ID, using ="j_username")
	@CacheLookup
	WebElement user;
	
	@FindBy (how = How.ID, using ="j_password")
	@CacheLookup
	WebElement pass;
	
	@FindBy(how= How.XPATH, using =".//*[@id='loginForm']/button")
	@CacheLookup
	WebElement loginButton;
	
	@FindBy (how = How.ID, using ="loginFB")
	@CacheLookup
	WebElement faceButton;
	
	public void loginBelcorp (String username, String password)
	{
		try {
			Thread.sleep(500); 
			loginButtonNav.click();
			user.sendKeys(username);
			pass.sendKeys(password);
			loginButton.click();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void loginFacebook(String username, String password)
	{
		try {
			loginButtonNav.click();
			faceButton.click();
			loginButtonNav.click();
			Thread.sleep(2000);
			} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void close()
	{
		driver.close();
	}
}
