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
 * @author hector.castillo
 *
 */

public class MiCuentaCambiarContrase�aPage {

	public MiCuentaCambiarContrase�aPage(WebDriver ldriver) 
	{
		this.driver = ldriver;
	}

	WebDriver driver;
	
	
	@FindBy(how= How.ID, using ="currentPassword")
	@CacheLookup
	WebElement passActual;
	
	@FindBy(how= How.ID, using ="newPassword")
	@CacheLookup
	WebElement newPass;
	
	@FindBy(how= How.ID, using ="checkNewPassword")
	@CacheLookup
	WebElement checkNewPass;	
	
	@FindBy(how= How.XPATH, using =".//*[@id='updatePasswordForm']/div[4]/div[1]/button")
	@CacheLookup
	WebElement btnUpdatePass;
	
	@FindBy(how= How.XPATH, using =".//*[@id='updatePasswordForm']/div[4]/div[2]/button")
	@CacheLookup
	WebElement btnCancelarUpdatePass;
	
	@FindBy(how= How.XPATH, using =".//*[@id='navbar']/ul[2]/li[3]/div")
	@CacheLookup
	WebElement loginButtonNav;
	
	public void cambiarContrase�a(String contrase�aActual, String nuevaContrase�a, String repetirConrase�a)
	{
		try {
			loginButtonNav.click();
			Thread.sleep(300);
			driver.navigate().to("https://aws-esika.esika.com:9002/co/my-account/update-password");
			passActual.sendKeys(contrase�aActual);
			newPass.sendKeys(nuevaContrase�a);
			checkNewPass.sendKeys(repetirConrase�a);
			Thread.sleep(500);
			btnUpdatePass.click();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}