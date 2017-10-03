package belcorp.test.MiCuentaOpciones;

import org.testng.annotations.Test;

import belcorp.pages.LoginPage;
import belcorp.pages.MiCuentaMisDireccionesPage;
import belcorp.utils.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CPA_08_MiCuenta_Direccion_Predeterminada {
	
	@Test
	public void CPA_04()
	{
		WebDriver driver = BrowserFactory.startBrowser("firefox","https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
		MiCuentaMisDireccionesPage direccionPredeterminada = PageFactory.initElements(driver, MiCuentaMisDireccionesPage.class);
		LoginPage login_page = PageFactory.initElements(driver, LoginPage.class);
		login_page.loginBelcorp("tsoft@yopmail.com","Tsoft123");
		
		direccionPredeterminada.MiDirecionPredeterminada();
		
		
	}
	

}
