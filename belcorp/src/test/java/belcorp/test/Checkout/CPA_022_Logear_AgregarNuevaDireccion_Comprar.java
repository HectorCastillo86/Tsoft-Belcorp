package belcorp.test.Checkout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import belcorp.pages.BolsaCompraPage;
import belcorp.pages.CheckOutPage;
import belcorp.pages.LoginPage;
import belcorp.test.T01_LoginBelcorp;
import belcorp.utils.BrowserFactory;


public class CPA_022_Logear_AgregarNuevaDireccion_Comprar {
	
	@Test
	public void CPA_022() 
	{
		
		WebDriver driver = BrowserFactory.startBrowser("firefox","https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
		LoginPage login_page = PageFactory.initElements(driver, LoginPage.class);
		BolsaCompraPage AgregarABolsa = PageFactory.initElements(driver, BolsaCompraPage.class);
		CheckOutPage CkeckOutPagar = PageFactory.initElements(driver, CheckOutPage.class);
		
		AgregarABolsa.agregarUnArticulo();
		AgregarABolsa.irABolsaCompra();
		AgregarABolsa.BotonIrAPagar();
		
		
	}

}
