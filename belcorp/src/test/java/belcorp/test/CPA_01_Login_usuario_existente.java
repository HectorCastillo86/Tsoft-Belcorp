package belcorp.test;

import org.testng.annotations.Test;
import belcorp.pages.LoginPage;
import belcorp.test.MiCuentaOpciones.CPA_06_MiCuenta_Cambiar_Contraseña;
import belcorp.utils.BrowserFactory;
import belcorp.utils.TakeScreenShot;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CPA_01_Login_usuario_existente {
	
	  public static final String user = "tsoft@yopmail.com";
	  public static final String pass = CPA_06_MiCuenta_Cambiar_Contraseña.nuevaContraseña;
  
  @Test
  public void Login_usuario_existente() throws InterruptedException 
  {
	  // CARGA URL Y PAGE
	  WebDriver driver = BrowserFactory.startBrowser("firefox","https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
	  LoginPage login_page = PageFactory.initElements(driver, LoginPage.class);
	  
	  // DATOS CPA
	  //String user = "tsoft@yopmail.com";
      //String pass= "Tsoft123";
	  
      // FUNCION CPA
	  login_page.loginBelcorp(user ,pass);	
	  
	  //VALIDACION
	  TakeScreenShot.takeScreenShot(driver, "CPA_01_evidencia_");
	  
	  // CERRAR DRIVER
	  //login_page.close();
  }
  

}
