package belcorp.test;

import org.testng.annotations.Test;
import belcorp.pages.LoginPage;
import belcorp.test.MiCuentaOpciones.CPA_06_MiCuenta_Cambiar_Contraseña;
import belcorp.utils.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class T01_LoginBelcorp {
  
  public static final String user = "tsoft@yopmail.com";
  public static final String pass = CPA_06_MiCuenta_Cambiar_Contraseña.nuevaContraseña;

@Test
  public void Login_01() 
  {
	  WebDriver driver = BrowserFactory.startBrowser("firefox","https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
	  LoginPage login_page = PageFactory.initElements(driver, LoginPage.class);
	  
	  login_page.loginBelcorp(user ,pass);	  
  }
  

}
