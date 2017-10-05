/**
 * 
 */
package belcorp.pages;

import java.util.Iterator;
import java.util.Set;

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
	
	@FindBy(how= How.XPATH, using =".//*[@id='loginForm']/div[3]/button")
	@CacheLookup
	WebElement loginButtonCheckOut;
	
	@FindBy (how = How.ID, using ="email")
	@CacheLookup
	WebElement facemail;
	
	@FindBy (how = How.ID, using ="pass")
	@CacheLookup
	WebElement facepass;
	
	@FindBy (how = How.ID, using ="u_0_0")
	@CacheLookup
	WebElement faceloginbtn;
	
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
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void loginCheckOut (String username, String password)
	{
		try {
			Thread.sleep(500); 
			user.sendKeys(username);
			pass.sendKeys(password);
			loginButtonCheckOut.click();
			
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
			String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
			String subWindowHandler = null;

			Set<String> handles = driver.getWindowHandles(); // get all window handles
			Iterator<String> iterator = handles.iterator();
			while (iterator.hasNext()){
			    subWindowHandler = iterator.next();
			}
			driver.switchTo().window(subWindowHandler); // switch to popup window
			// perform operations on popup
			facemail.sendKeys(username);
			facepass.sendKeys(password);
			faceloginbtn.click();
			Thread.sleep(2000);
			
			driver.switchTo().window(parentWindowHandler);  // switch back to parent window
			
			//loginButtonNav.click();
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
