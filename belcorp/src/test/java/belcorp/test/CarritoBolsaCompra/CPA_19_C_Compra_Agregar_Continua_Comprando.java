package belcorp.test.CarritoBolsaCompra;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import belcorp.pages.BolsaCompraPage;
import belcorp.utils.BrowserFactory;

public class CPA_19_C_Compra_Agregar_Continua_Comprando {

	@Test
	public void CPA_19()
	{
		WebDriver driver = BrowserFactory.startBrowser("firefox","https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
		BolsaCompraPage AgregarABolsa = PageFactory.initElements(driver, BolsaCompraPage.class);
		
		AgregarABolsa.UbicadoBolsaDeCompra();
		
		
		// CERRAR DRIVER 
		driver.close();
	}


}

