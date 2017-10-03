package belcorp.test.MiCuentaOpciones;

import org.testng.annotations.Test;
import belcorp.pages.AgregarDireccionPage;
import belcorp.pages.LoginPage;
import belcorp.utils.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CPA_07_MiCuenta_Agregar_Direccion {

	@Test
	public void CPA_03()
	{
		
		WebDriver driver = BrowserFactory.startBrowser("firefox","https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
		AgregarDireccionPage newDireccion = PageFactory.initElements(driver, AgregarDireccionPage.class);
		LoginPage login_page = PageFactory.initElements(driver, LoginPage.class);
		login_page.loginBelcorp("tsoft@yopmail.com","Tsoft123");
		
		
		String direccion = "Las Condes Calle N°1";
		String deptoDetalle = "Block 4-B";
		String infoDireccion = "Cercano al metro Tobalaba";
		String numTelefono = "012345678";
		
		newDireccion.AgregarDireccion(direccion, deptoDetalle, infoDireccion, numTelefono);
	}	

}

