package belcorp.test.MiCuentaOpciones;

import org.testng.annotations.Test;
import belcorp.pages.MiCuentaCambiarContrase�aPage;
import belcorp.test.T01_LoginBelcorp;
import belcorp.utils.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import belcorp.pages.LoginPage;

public class CPA_06_MiCuenta_Cambiar_Contrase�a {

	String usuario=T01_LoginBelcorp.user;
	String clave=T01_LoginBelcorp.pass;
	public static String nuevaContrase�a="Hola123";
	String repetirConrase�a="Hola123";
	
	@Test
	public void CPA_02()
	{

		WebDriver driver = BrowserFactory.startBrowser("firefox","https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
		MiCuentaCambiarContrase�aPage cambiarContrase�a = PageFactory.initElements(driver, MiCuentaCambiarContrase�aPage.class);
		LoginPage login_page = PageFactory.initElements(driver, LoginPage.class);
		login_page.loginBelcorp(usuario, clave);
		cambiarContrase�a.cambiarContrase�a(clave, nuevaContrase�a, repetirConrase�a);
	}


	
}
