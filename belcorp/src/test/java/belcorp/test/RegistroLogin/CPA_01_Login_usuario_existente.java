package belcorp.test.RegistroLogin;

import org.testng.Assert;
import org.testng.annotations.Test;
import belcorp.pages.LoginPage;
import belcorp.test.MiCuentaOpciones.CPA_06_MiCuenta_Cambiar_Contraseña;
import belcorp.utils.BrowserFactory;
import belcorp.utils.TakeScreenShot;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class CPA_01_Login_usuario_existente {
  
  public static final String user = "tsoft@yopmail.com";
  public static final String pass = CPA_06_MiCuenta_Cambiar_Contraseña.nuevaContraseña;

@Test
  public void CPA_01() 
  {
	try {
	  WebDriver driver = BrowserFactory.startBrowser("firefox","https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
	  LoginPage login_page = PageFactory.initElements(driver, LoginPage.class);
	  
	  login_page.loginBelcorp(user ,pass);
	  
	  //Validacion verificar nick de usuario logeado
	  //Posicionarse en icono de usuario
	  Actions act1 = new Actions(driver);
	  act1.moveToElement(driver.findElement(By.xpath(".//*[@class='accountLi']"))).perform();
	  //Esperar 1.5 Seg
	  Thread.sleep(1500);
	   
	  //Validar si existe AccountName Nombre de usuario Registrado
	  boolean val1 = driver.findElement(By.xpath("//span[@class='accountName']")).isEnabled();
	  if (val1) {
		  String username = driver.findElement(By.xpath("//span[@class='accountName']")).getText();
		  System.out.println("CPA_01: Usuario logeado en Sistema para Ejecucion: " +username);		 
		  TakeScreenShot.takeScreenShot(driver, "CPA_01_val1_evidencia_OK_");
	  }	 
	  else {
		  TakeScreenShot.takeScreenShot(driver, "CPA_01_val1_evidencia_NOK_");		  		  
	  } 
	 
	  //Cerrar Test
	  login_page.close();
	  
	  } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
	  
  }
  

}
