package belcorp.test.MiCuentaOpciones;

import org.testng.annotations.Test;
import belcorp.pages.MiCuentaCambiarContraseñaPage;
import belcorp.test.T01_LoginBelcorp;
import belcorp.utils.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import belcorp.pages.LoginPage;

public class CPA_06_MiCuenta_Cambiar_Contraseña {

	String usuario=T01_LoginBelcorp.user;
	String clave=T01_LoginBelcorp.pass;
	public static String nuevaContraseña="Hola123";
	String repetirConraseña="Hola123";
	
	@Test
	public void CPA_02()
	{

		WebDriver driver = BrowserFactory.startBrowser("firefox","https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
		MiCuentaCambiarContraseñaPage cambiarContraseña = PageFactory.initElements(driver, MiCuentaCambiarContraseñaPage.class);
		LoginPage login_page = PageFactory.initElements(driver, LoginPage.class);
		login_page.loginBelcorp(usuario, clave);
		cambiarContraseña.cambiarContraseña(clave, nuevaContraseña, repetirConraseña);
	}


	
}
