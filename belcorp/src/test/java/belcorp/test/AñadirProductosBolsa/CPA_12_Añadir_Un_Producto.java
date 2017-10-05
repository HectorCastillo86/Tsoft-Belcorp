package belcorp.test.A�adirProductosBolsa;

import org.testng.annotations.Test;

import belcorp.pages.BolsaCompraPage;
import belcorp.utils.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CPA_12_A�adir_Un_Producto {
	
	@Test
	public void CPA_01()
	{
		WebDriver driver = BrowserFactory.startBrowser("firefox","https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
		BolsaCompraPage AgregarABolsa = PageFactory.initElements(driver, BolsaCompraPage.class);
		
		AgregarABolsa.agregarUnArticulo();
		
		// CERRAR DRIVER 
		driver.close();
	}


}
