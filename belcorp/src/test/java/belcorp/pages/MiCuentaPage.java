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

public class MiCuentaPage {

	WebDriver driver;
	
	public MiCuentaPage (WebDriver ldriver)
	{
		this.driver = ldriver;
		
	}
	
	@FindBy(how= How.XPATH, using =".//*[@id='navbar']/ul[2]/li[3]/div/div/div[1]/a")
	@CacheLookup
	WebElement menuDatosPersonales;

	@FindBy(how= How.XPATH, using =".//*[@id='navbar']/ul[2]/li[3]/div/div/div[2]/a")
	@CacheLookup
	WebElement menuCambioContraseña;

	@FindBy(how= How.XPATH, using =".//*[@id='navbar']/ul[2]/li[3]/div/div/div[3]/a")
	@CacheLookup
	WebElement menuMisDirecciones;	
	
	@FindBy(how= How.XPATH, using =".//*[@id='navbar']/ul[2]/li[3]/div/div/div[4]/a")
	@CacheLookup
	WebElement menuCorreoElectronico;
	
	@FindBy(how= How.XPATH, using =".//*[@id='navbar']/ul[2]/li[3]/div/div/div[5]/a")
	@CacheLookup
	WebElement menuMisPedidosRealizados;

	@FindBy(how= How.XPATH, using =".//*[@id='navbar']/ul[2]/li[3]/div/div/a")
	@CacheLookup
	WebElement botonCerrarSesion;
	
	public void CerrarMiCuenta ()
	{
		botonCerrarSesion.click();		
	}
}
