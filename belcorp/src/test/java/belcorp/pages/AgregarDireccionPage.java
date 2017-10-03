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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author hector.castillo
 *
 */

public class AgregarDireccionPage {
	
	WebDriver driver;
	WebDriverWait wait;
	public String dText;
	
	public AgregarDireccionPage (WebDriver ldriver)
	{
		this.driver = ldriver;
		
	}
	
	@FindBy(how= How.XPATH, using ="html/body/main/div[2]/div[4]/div/div[2]/div[1]/div[1]/div")
	@CacheLookup
	WebElement btnAgregarNewDireccion;
				
	@FindBy(how= How.ID, using ="address.line1")
	@CacheLookup
	WebElement newDireccion;

	@FindBy(how= How.ID, using ="address.line2")
	@CacheLookup
	WebElement depto;
	
	@FindBy(how= How.ID, using ="address.referencia")
	@CacheLookup
	WebElement infoAdicional;

	@FindBy(how= How.ID, using ="address.phone")
	@CacheLookup
	WebElement telefono;
	
	@FindBy(how= How.XPATH, using =".//*[@id='addressform_button_panel']/div/div[1]/button")
	@CacheLookup
	WebElement btnGuardarDireccion;
	
	@FindBy(how= How.XPATH, using =".//*[@id='addressform_button_panel']/div/div[2]/a[1]")
	@CacheLookup
	WebElement btnCancelarDireccion;	
	
	@FindBy(how= How.XPATH, using =".//*[@id='navbar']/ul[2]/li[3]/div")
	@CacheLookup
	WebElement loginButtonNav;	
	
	
	public void AgregarDireccion (String direccion, String deptoDetalle, String infoDireccion, String numTelefono)
	{
		try {
				loginButtonNav.click();
				Thread.sleep(300);
				driver.navigate().to("https://aws-esika.esika.com:9002/co/my-account/address-book");
				Thread.sleep(1000);
				btnAgregarNewDireccion.click();
				driver.findElement(By.id("address.country")).click();
				Thread.sleep(500);
				Select dropdown = new Select(driver.findElement(By.id("address.regionIsoParent1")));
				dropdown.selectByVisibleText("BOLIVAR");
				Thread.sleep(500);
				Select dropdownB = new Select(driver.findElement(By.id("address.regionIso")));
				dropdownB.selectByVisibleText("CALAMAR");
				newDireccion.sendKeys(direccion);
				depto.sendKeys(deptoDetalle);
				infoAdicional.sendKeys(infoDireccion);
				telefono.sendKeys(numTelefono);

				btnGuardarDireccion.click();
		} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}

	}
	
	public void ObtenerDireccionNombre()
	{
		try {
			Thread.sleep(500);
			driver.findElements(By.tagName("Strong"));
			System.out.println(dText);
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
