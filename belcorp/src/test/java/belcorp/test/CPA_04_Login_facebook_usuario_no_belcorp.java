package belcorp.test;

import org.testng.annotations.Test;
import belcorp.utils.TakeScreenShot;
import belcorp.pages.LoginPage;
import belcorp.pages.RegistroPage;
import belcorp.utils.BrowserFactory;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class CPA_04_Login_facebook_usuario_no_belcorp {
  
  @Test
  public void Registro_nuevo_usuario() throws IOException 
  {
	  // CARGA URL Y PAGE
	  WebDriver driver = BrowserFactory.startBrowser("firefox","https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
	  LoginPage login_page = PageFactory.initElements(driver, LoginPage.class);
	  
	  // DATOS CPA
	  String user = "tsoft@yopmail.com";
      String pass= "Tsoft123";
	  
      // FUNCION CPA
	  login_page.loginFacebook(user,pass);
	  
	  //VALIDACION
	  TakeScreenShot.takeScreenShot(driver, "CPA_04_evidencia_");
	  
	  // CERRAR DRIVER
	  login_page.close();
	 	
  }
  

}
