package belcorp.test.CarritoBolsaCompra;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import belcorp.pages.LoginPage;
import belcorp.test.RegistroLogin.CPA_01_Login_usuario_existente;

import org.testng.annotations.Test;

import belcorp.pages.BolsaCompraPage;
import belcorp.utils.BrowserFactory;

public class CPA_18_Eliminar_Prod_Pagar_CheckOut {
	
	String usuario=CPA_01_Login_usuario_existente.user;
	String clave=CPA_01_Login_usuario_existente.pass;

	@Test
	public void CPA_18()
	{
		WebDriver driver = BrowserFactory.startBrowser("firefox","https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
		BolsaCompraPage AgregarABolsa = PageFactory.initElements(driver, BolsaCompraPage.class);
		LoginPage login_page = PageFactory.initElements(driver, LoginPage.class);
		
		login_page.loginBelcorp(usuario, clave);
		AgregarABolsa.agregarUnArticulo();
		AgregarABolsa.EliminaProdContComprando();
		
		// CERRAR DRIVER 
		driver.close();
	}


}