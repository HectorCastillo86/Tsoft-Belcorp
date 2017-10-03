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

public class MiCuentaMisDireccionesPage {

	WebDriver driver;
	
	public MiCuentaMisDireccionesPage (WebDriver ldriver)
	{
		this.driver = ldriver;
		
	}

	@FindBy(how= How.XPATH, using ="//html/body/main/div[2]/div[4]/div/div[2]/div[1]/div[1]/div/a[1]")
	@CacheLookup
	WebElement agregarNewDireccion;
	
	@FindBy(how= How.CSS, using =".checkbox>label")
	@CacheLookup
	WebElement checkPredeterminado;
	
	@FindBy(how= How.XPATH, using ="html/body/main/div[2]/div[4]/div/div[2]/div[2]/div/div[1]/div[3]/div/a[1]")
	@CacheLookup
	WebElement linkModificar;
	
	@FindBy(how= How.XPATH, using ="html/body/main/div[2]/div[4]/div/div[2]/div[2]/div/div[1]/div[3]/div/a[2]")
	@CacheLookup
	WebElement linkEliminar;
	
	@FindBy(how= How.XPATH, using ="html/body/div[4]/div[1]/div[2]/div[2]/div[1]/div/div/div[2]/div[1]/a")
	@CacheLookup
	WebElement botonEliminar;
	
	@FindBy(how= How.XPATH, using ="html/body/div[4]/div[1]/div[2]/div[2]/div[1]/div/div/div[2]/div[2]/a")
	@CacheLookup
	WebElement botonCancelar;
	
	@FindBy(how= How.ID, using ="cboxClose")
	@CacheLookup
	WebElement botonCerrar;
	
	@FindBy(how= How.XPATH, using =".//*[@id='navbar']/ul[2]/li[3]/div")
	@CacheLookup
	WebElement loginButtonNav;
	
	public void MiDirecionPredeterminada() 
	{
		try {
			loginButtonNav.click();
			Thread.sleep(300);
			driver.navigate().to("https://aws-esika.esika.com:9002/co/my-account/address-book");
			Thread.sleep(1000);
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void EliminarDireccion()
	{
		linkEliminar.click();
		botonEliminar.click();
	}
	
}
