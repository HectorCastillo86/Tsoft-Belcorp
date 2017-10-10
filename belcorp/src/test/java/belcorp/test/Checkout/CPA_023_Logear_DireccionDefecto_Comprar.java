package belcorp.test.Checkout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import belcorp.pages.BolsaCompraPage;
import belcorp.pages.CheckOutPage;
import belcorp.pages.LoginPage;
import belcorp.test.RegistroLogin.CPA_01_Login_usuario_existente;
import belcorp.utils.BrowserFactory;

public class CPA_023_Logear_DireccionDefecto_Comprar {
	
	@Test
	public void Logear_DireccionDefecto_Comprar() {
		WebDriver driver = BrowserFactory.startBrowser("firefox","https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
		LoginPage login_page = PageFactory.initElements(driver, LoginPage.class);
		BolsaCompraPage AgregarABolsa = PageFactory.initElements(driver, BolsaCompraPage.class);
		CheckOutPage CkeckOutPagar = PageFactory.initElements(driver, CheckOutPage.class);
		
		AgregarABolsa.agregarUnArticulo();
		AgregarABolsa.BotonIrAPagar();
		login_page.loginCheckOut(CPA_01_Login_usuario_existente.user, CPA_01_Login_usuario_existente.pass);
		
		CkeckOutPagar.ContinuarOpcionesEnvio();
		CkeckOutPagar.ContinuarMetodoPago();
		CkeckOutPagar.MetodoPagoEfectivo();
		
		// CERRAR DRIVER 
		driver.close();
	}

}
