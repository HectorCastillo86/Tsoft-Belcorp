package belcorp.test.RegistroLogin;

import org.testng.Assert;
import org.testng.annotations.Test;
import belcorp.utils.TakeScreenShot;
import belcorp.pages.LoginPage;
import belcorp.pages.RegistroPage;
import belcorp.utils.BrowserFactory;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;


public class CPA_04_Login_facebook_usuario_no_belcorp {
  
  @Test
  public void Login_usuario_facebook() throws IOException 
  {
	  // CARGA URL Y PAGE
	  WebDriver driver = BrowserFactory.startBrowser("firefox","https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
	  LoginPage login_page = PageFactory.initElements(driver, LoginPage.class);
	  try
	  {
	  // DATOS CPA
	  String user = "john_fdtbmaw_dos@tfbnw.net";
      String pass= "Belcorp2017";
	  
      // FUNCION CPA
	  login_page.loginFacebook(user,pass);
	  //Esperar 2 Seg
	  Thread.sleep(2000);
	  
	  //VALIDACION REGISTRO EXITOSO
	  //Validar si existe AccountName Nombre de usuario Registrado
	      boolean val1 = !(driver.findElements(By.xpath("//span[@class='accountName']")).size() == 0);
	  	  //System.out.println(val1);
	  	  if (val1 == true) {
	  		  //Posicionarse en icono de usuario
	  	  	  Actions act1 = new Actions(driver);
	  	  	  act1.moveToElement(driver.findElement(By.xpath(".//*[@class='accountLi']"))).perform();
	  	  	  //Esperar 2 Seg
	  	  	  Thread.sleep(2000);
	  		  String username = driver.findElement(By.xpath("//span[@class='accountName']")).getText();
	  		  System.out.println("CPA_04: Usuario Facebook logeado en Sistema para Ejecucion: " +username);		 
	  		  TakeScreenShot.takeScreenShot(driver, "CPA_04_val1_evidencia_OK_");
	  	  }	 
	  	  else {
	  		  //Esperar 2 Seg
	  	  	  Thread.sleep(2000);
	  		  TakeScreenShot.takeScreenShot(driver, "CPA_04_val1_evidencia_NOK_");
	  		  System.out.println("CPA_04: Problema de login Facebook con los siguientes datos: " +user+".");
	  		  login_page.close();
	  		  Assert.assertTrue(val1, "Problema de login Facebook");	  			
	  	  }  
							
			// CERRAR DRIVER 
	  	  	login_page.close();
	  } 
	  catch (InterruptedException e)
	  {
	  // TODO Auto-generated catch block
	  e.printStackTrace();
	  }	 	
  }
}
