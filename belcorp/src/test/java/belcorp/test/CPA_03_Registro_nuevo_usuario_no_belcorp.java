package belcorp.test;

import org.testng.annotations.Test;
import belcorp.utils.TakeScreenShot;
import belcorp.pages.RegistroPage;
import belcorp.utils.BrowserFactory;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class CPA_03_Registro_nuevo_usuario_no_belcorp {
  
  @Test
  public void Registro_nuevo_usuario() throws IOException 
  {
	
    try {
    	WebDriver driver = BrowserFactory.startBrowser("firefox","https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
    	RegistroPage registro = PageFactory.initElements(driver, RegistroPage.class);
    	
    	// DATOS CPA
    	String nombre = "juan";
    	String apellido= "juanes";
    	String email = "juan.juanes2@yopmail.com";
    	String pwd = "Tsoft123";
    	String tipoDoc = "Cédula de extranjería"; // valores "Cédula de identidad" ; "Cédula de extranjería"; "NIT" ; "Pasaporte"
    	String checkNumDoc= "154372002";
    	
    	
    	// FUNCION CPA
    	registro.RegistroBelcorp(nombre, apellido, email, pwd, tipoDoc, checkNumDoc);
		
    	Thread.sleep(3000);
		
		// FALTA VALIDACION REGISTRO EXITOSO
		// 
		
		TakeScreenShot.takeScreenShot(driver, "CPA_03_evidencia_");
		
		// CERRAR DRIVER 
		registro.close();
		
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
  }
  

}
