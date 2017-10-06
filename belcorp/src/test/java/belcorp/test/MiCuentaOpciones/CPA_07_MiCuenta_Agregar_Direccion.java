package belcorp.test.MiCuentaOpciones;

import org.testng.Assert;
import org.testng.annotations.Test;
import belcorp.pages.AgregarDireccionPage;
import belcorp.pages.LoginPage;
import belcorp.test.RegistroLogin.CPA_01_Login_usuario_existente;
import belcorp.utils.BrowserFactory;
import belcorp.utils.TakeScreenShot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class CPA_07_MiCuenta_Agregar_Direccion {

	//Datos CPA_07
			String user="tsoft@yopmail.com";
			String pass="Hola123";
			String direccion = "Las Condes Calle N°1";
			String deptoDetalle = "Block 4-B";
			String infoDireccion = "Cercano al metro Tobalaba";
			String numTelefono = "012345678";
	@Test
	public void MiCuenta_Agregar_Direccion() throws InterruptedException
	{
		//Mostrar en Consola Datos de Ejecucion de Prueba
		System.out.println("CPA_07: Datos para Ejecucion Usuario: " +user+" , Password: "+pass+" , direccion: "+direccion+" , deptoDetalle: "+deptoDetalle+" , infoDireccion: "+infoDireccion+" , numTelefono: "+numTelefono);
		
		
		WebDriver driver = BrowserFactory.startBrowser("firefox","https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
		AgregarDireccionPage newDireccion = PageFactory.initElements(driver, AgregarDireccionPage.class);
		LoginPage login_page = PageFactory.initElements(driver, LoginPage.class);
		
		//Login Belcorp
		login_page.loginBelcorp(user,pass);
		
		//Validar si existe AccountName Nombre de usuario Registrado
		  boolean val1 = !(driver.findElements(By.xpath("//span[@class='accountName']")).size() == 0); // Se pregunta si existe el objeto
		  if (val1) {
			  Actions act1 = new Actions(driver);
			  act1.moveToElement(driver.findElement(By.xpath(".//*[@class='accountLi']"))).perform();
			  //Esperar 1.5 Seg
			  Thread.sleep(1500);
			  String username = driver.findElement(By.xpath("//span[@class='accountName']")).getText();
			  System.out.println("CPA_07: Usuario logeado en Sistema para Ejecucion: " +username);		 
			  TakeScreenShot.takeScreenShot(driver, "CPA_07_val1_evidencia_OK_");
		  }	 
		  else {
			  TakeScreenShot.takeScreenShot(driver, "CPA_07_val1_evidencia_NOK_");
			  System.out.println("CPA_07: Problema de login con los siguientes datos: " +user+".");
			  login_page.close();
			  Assert.assertTrue(val1, "CPA_07: Problema de login con los siguientes datos: " +user+".");
	  	  } 
				
		//Metodo para Agregar Nueva Direaccion
		newDireccion.AgregarDireccion(direccion, deptoDetalle, infoDireccion, numTelefono);
		
		//VALIDACION DE NUEVA DIRECCION : valida que aparezca en pantalla la direccion
		boolean val2 = !(driver.findElements(By.xpath(".//a[@title='MIS DIRECCIONES']")).size() == 0); // Se pregunta si existe el objeto
		if (val2) 
		{
			  //captura de Evidencia jpg
			  System.out.println("CPA_07: Se carga Nueva Direccion al usuario:" +user);		 
			  TakeScreenShot.takeScreenShot(driver, "CPA_07_val2_evidencia_OK_");
		}
		
		driver.close();
	
	}	

}

