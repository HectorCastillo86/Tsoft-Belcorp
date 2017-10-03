package belcorp.test.CarritoBolsaCompra;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import belcorp.pages.LoginPage;
import belcorp.test.T01_LoginBelcorp;

import org.testng.annotations.Test;

import belcorp.pages.BolsaCompraPage;
import belcorp.utils.BrowserFactory;

public class CPA_18_Eliminar_Prod_Pagar_CheckOut {
	
	String usuario=T01_LoginBelcorp.user;
	String clave=T01_LoginBelcorp.pass;

	@Test
	public void CPA_18()
	{
		WebDriver driver = BrowserFactory.startBrowser("firefox","https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
		BolsaCompraPage AgregarABolsa = PageFactory.initElements(driver, BolsaCompraPage.class);
		LoginPage login_page = PageFactory.initElements(driver, LoginPage.class);
		
		login_page.loginBelcorp(usuario, clave);
		AgregarABolsa.agregarUnArticulo();
		AgregarABolsa.EliminaProdContComprando();
		
		
	}


}