package belcorp.test.Checkout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import belcorp.pages.BolsaCompraPage;
import belcorp.pages.CheckOutPage;
import belcorp.pages.LoginPage;
import belcorp.test.T01_LoginBelcorp;
import belcorp.utils.BrowserFactory;

public class CPA_020_Registrar_AgregarNuevaDireccion_Comprar {

	@Test
	public void CPA_020_Registrar() {
		
		WebDriver driver = BrowserFactory.startBrowser("firefox","https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
		LoginPage login_page = PageFactory.initElements(driver, LoginPage.class);
		BolsaCompraPage AgregarABolsa = PageFactory.initElements(driver, BolsaCompraPage.class);
		CheckOutPage CheckOutPagar = PageFactory.initElements(driver, CheckOutPage.class);
		
		login_page.loginBelcorp(T01_LoginBelcorp.user, T01_LoginBelcorp.pass);
		AgregarABolsa.irABolsaCompra();
		AgregarABolsa.BotonIrAPagar();
		CheckOutPagar.presionarAgregarDireccion();
		
		String ciudad = "BOGOTA";
		String pais = "Colombia";
		String depar = "BOGOTA";
		String direccion = "Las perdices";
		String nrodep = "1604-B";
		String infoAdicional = "Metro Tobalaba";
		String telefono = "091919191";
		
		CheckOutPagar.IngresarDireccionEnvio(pais, depar, ciudad, direccion, nrodep, infoAdicional, telefono);
		CheckOutPagar.ContinuarOpcionesEnvio();
		CheckOutPagar.ContinuarMetodoPago();
	}

}
