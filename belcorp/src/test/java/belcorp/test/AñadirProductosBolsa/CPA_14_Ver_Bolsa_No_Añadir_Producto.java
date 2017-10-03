package belcorp.test.AñadirProductosBolsa;

import org.testng.annotations.Test;
import belcorp.pages.BolsaCompraPage;
import belcorp.utils.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CPA_14_Ver_Bolsa_No_Añadir_Producto {
	
	@Test
	public void CPA_03()
	{
		WebDriver driver = BrowserFactory.startBrowser("firefox","https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
		BolsaCompraPage AgregarABolsa = PageFactory.initElements(driver, BolsaCompraPage.class);
		
		AgregarABolsa.NoAgregarArticulo();
	}


}