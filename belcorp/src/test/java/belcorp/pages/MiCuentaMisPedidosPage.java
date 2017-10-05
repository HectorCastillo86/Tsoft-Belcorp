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

public class MiCuentaMisPedidosPage {

	WebDriver driver;
	
	public MiCuentaMisPedidosPage (WebDriver ldriver)
	{
		this.driver = ldriver;
		
	}
	
	@FindBy(how= How.XPATH, using =".//*[@id='navbar']/ul[2]/li[3]/div/div/div[5]/a")
	@CacheLookup
	WebElement menuMisPedidosRealizados;
	
	@FindBy(how= How.XPATH, using =".//*[@id='navbar']/ul[2]/li[3]/div")
	@CacheLookup
	WebElement loginButtonNav;
	
	@FindBy(how= How.CLASS_NAME, using ="aLogout")
	@CacheLookup
	WebElement logoutButtonNav;
	
	
	public void MisPedidos()
	{
		try {
			loginButtonNav.click();
			Thread.sleep(300);
			driver.navigate().to("https://aws-esika.esika.com:9002/co/my-account/orders");
			Thread.sleep(1000);
			//logoutButtonNav.click();
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}