package belcorp.test.RegistroLogin;

import org.testng.Assert;
import org.testng.annotations.Test;
import belcorp.utils.TakeScreenShot;
import belcorp.pages.RegistroPage;
import belcorp.utils.BrowserFactory;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;


public class CPA_02_Registro_nuevo_usuario_belcorp {
	
	// DATOS CPA_02
	String nombre = "AUNO";
	String apellido= "Auno";
	String email = "Auno_01@yopmail.com";
	String pwd = "Tsoft123";
	String tipoDoc = "Cédula de extranjería"; // valores "Cédula de identidad" ; "Cédula de extranjería"; "NIT" ; "Pasaporte"
	String checkNumDoc= "154440001";
  
  @Test
  public void Registro_nuevo_usuario_belcorp() throws IOException 
  {
	  
	//Mostrar en Consola Datos de Ejecucion de Prueba
	System.out.println("CPA_02: Datos para Ejecucion Nombre: " +nombre+" , apellido: "+apellido+" , email: "+email+" , Password: "+pwd+" , Tipo Documento: "+tipoDoc+" , Id Documento: "+checkNumDoc);
			
	// Inicializar Driver y Page
	WebDriver driver = BrowserFactory.startBrowser("firefox","https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
  	RegistroPage registro = PageFactory.initElements(driver, RegistroPage.class);
	
    try {    	
    	// FUNCION CPA
    	registro.RegistroBelcorp(nombre, apellido, email, pwd, tipoDoc, checkNumDoc);
    	//Esperar 2 Seg
	  	Thread.sleep(2000);
	  	  
	  	//*******************VALIDACION 1: verificar nick de usuario logeado****************
		//Validar si existe AccountName Nombre de usuario Registrado
        boolean val1 = !(driver.findElements(By.xpath("//span[@class='accountName']")).size() == 0);
  	  
	  	  if (val1 == true) {
	  		  //Posicionarse en icono de usuario
	  	  	  Actions act1 = new Actions(driver);
	  	  	  act1.moveToElement(driver.findElement(By.xpath(".//*[@class='accountLi']"))).perform();
	  	  	  //Esperar 2 Seg
	  	  	  Thread.sleep(2000);
	  		  String username = driver.findElement(By.xpath("//span[@class='accountName']")).getText();
	  		  System.out.println("CPA_02: Usuario logeado en Sistema para Ejecucion: " +username);		 
	  		  TakeScreenShot.takeScreenShot(driver, "CPA_02_val1_evidencia_OK_");
	  	  }	 
	  	  else {
	  		  //Esperar 2 Seg
	  	  	  Thread.sleep(2000);
	  	  	  System.out.println("CPA_02: Problema de Creacion de Usuario con los siguientes datos: " +nombre+" , "+apellido+" , "+email+" , "+pwd+" , "+tipoDoc+" , "+checkNumDoc+".");
	  		  TakeScreenShot.takeScreenShot(driver, "CPA_02_val1_evidencia_NOK_");
	  		  registro.close();
	  		  Assert.assertTrue(val1, "CPA_02: Problema con Creacion de Usuario");
	  	  }  
						
		// CERRAR DRIVER 
		registro.close();
		
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		registro.close();
	}
	
  }
  

}
