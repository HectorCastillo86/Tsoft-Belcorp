/**
 * 
 */
package belcorp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * @author hector.castillo
 *
 */
public class RegistroPage {

	WebDriver driver;
	
	public RegistroPage (WebDriver ldriver)
	{
		this.driver = ldriver;
		
	}
	
	@FindBy(how= How.CLASS_NAME, using ="loginButton")
	@CacheLookup
	WebElement loginButtonNav;
	
	@FindBy(how= How.XPATH, using =".//*[@id='btnCreaCuenta']")
	@CacheLookup
	WebElement btnRegistrate;
	
	@FindBy(how= How.ID, using ="register.firstName")
	@CacheLookup
	WebElement firstName;
	
	@FindBy(how= How.ID, using ="register.lastName")
	@CacheLookup
	WebElement lastName;
	
	@FindBy(how= How.ID, using ="register.email")
	@CacheLookup
	WebElement emailR;
	
	@FindBy(how= How.ID, using ="register.checkEmail")
	@CacheLookup
	WebElement checkEmailR;	

	@FindBy(how= How.ID, using ="password")
	@CacheLookup
	WebElement pass;	
	
	@FindBy(how= How.ID, using ="register.checkPwd")
	@CacheLookup
	WebElement checkPass;	
	
	@FindBy(how= How.XPATH, using =".//*[@id='register.docType']/option[2]")
	@CacheLookup
	WebElement tipoDocumento;
		
	@FindBy(how= How.ID, using ="register.docNumber")
	@CacheLookup
	WebElement checkNumeroDocumento;
	
	@FindBy(how= How.ID, using ="registerForm")
	@CacheLookup
	WebElement btnCrearCuenta;
	
	public void RegistroBelcorp (String nombre, String apellido, String email, 
			String pwd, String tipoDoc,
			String checkNumDoc)
	{

		loginButtonNav.click();
		btnRegistrate.click();
		firstName.sendKeys(nombre);
		lastName.sendKeys(apellido);
		emailR.sendKeys(email);
		checkEmailR.sendKeys(email);
		pass.sendKeys(pwd);
		checkPass.sendKeys(pwd);
		driver.findElement(By.id("register.docType")).sendKeys(tipoDoc);
		checkNumeroDocumento.sendKeys(checkNumDoc);
		btnCrearCuenta.submit();
								
	}
	
	public void NuevoRegistro (String nombre, String apellido, String email, 
			String pwd, String tipoDoc,
			String checkNumDoc)
	{
		try {
			firstName.sendKeys(nombre);
			Thread.sleep(300);
			lastName.sendKeys(apellido);
			Thread.sleep(300);
			emailR.sendKeys(email);
			Thread.sleep(300);
			checkEmailR.sendKeys(email);
			Thread.sleep(300);
			pass.sendKeys(pwd);
			Thread.sleep(300);
			checkPass.sendKeys(pwd);
			Thread.sleep(300);
			driver.findElement(By.id("register.docType")).sendKeys(tipoDoc);
			Thread.sleep(300);
			checkNumeroDocumento.sendKeys(checkNumDoc);
			Thread.sleep(300);
			btnCrearCuenta.submit();
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
