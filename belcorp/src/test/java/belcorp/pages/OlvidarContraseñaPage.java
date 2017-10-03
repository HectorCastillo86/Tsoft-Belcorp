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

public class OlvidarContraseñaPage {

	WebDriver driver;
	public OlvidarContraseñaPage(WebDriver ldriver)
	{
		this.driver = ldriver;
		
	}
	
	@FindBy(how= How.XPATH, using =".//*[@id='loginForm']/div[6]/a")
	@CacheLookup
	WebElement olvidoContraseña;
	
	@FindBy(how= How.ID, using="forgottenPwd.email")
	@CacheLookup
	WebElement emailRegistro;
	
	@FindBy(how= How.XPATH, using=".//*[@id='forgottenPwdForm']/div[1]/button")
	@CacheLookup
	WebElement btnEnviarPass;
}
