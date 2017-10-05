package belcorp.test.MiCuentaOpciones;

import org.testng.Assert;
import org.testng.annotations.Test;

import belcorp.pages.LoginPage;
import belcorp.pages.MiCuentaMisDireccionesPage;
import belcorp.utils.BrowserFactory;
import belcorp.utils.TakeScreenShot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class CPA_08_MiCuenta_Direccion_Predeterminada {
	
	@Test
	public void MiCuenta_Direccion_Predeterminada() throws InterruptedException
	{
		 //Declaracion de Variables para CPA_07
		String user="tsoft@yopmail.com";
		String pass="Hola123";
		
		//Mostrar en Consola Datos de Ejecucion de Prueba
		System.out.println("CPA_08: Datos para Ejecucion Usuario: " +user+" , Password: "+pass);
		
		WebDriver driver = BrowserFactory.startBrowser("firefox","https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
		MiCuentaMisDireccionesPage direccionPredeterminada = PageFactory.initElements(driver, MiCuentaMisDireccionesPage.class);
		LoginPage login_page = PageFactory.initElements(driver, LoginPage.class);
		
		//Login Belcorp
		login_page.loginBelcorp(user,pass);
		
		//Validar si existe AccountName Nombre de usuario Registrado
		  boolean val1 = !(driver.findElements(By.xpath("//span[@class='accountName']")).size() == 0);
		  if (val1) {
			  Actions act1 = new Actions(driver);
			  act1.moveToElement(driver.findElement(By.xpath(".//*[@class='accountLi']"))).perform();
			  //Esperar 1.5 Seg
			  Thread.sleep(1500);
			  String username = driver.findElement(By.xpath("//span[@class='accountName']")).getText();
			  System.out.println("CPA_08: Usuario logeado en Sistema para Ejecucion: " +username);		 
			  TakeScreenShot.takeScreenShot(driver, "CPA_08_val1_evidencia_OK_");
			  			  		  
		  }	 
		  else {
			  TakeScreenShot.takeScreenShot(driver, "CPA_08_val1_evidencia_NOK_");
			  System.out.println("CPA_08: Problema de login con los siguientes datos: " +user+".");
			  login_page.close();
			  Assert.assertTrue(val1, "CPA_08: Problema de login con los siguientes datos: " +user+".");
	  	  } 
		
		  //Metodo para verificar Direccion Predeterminada
		  direccionPredeterminada.MiDirecionPredeterminada();
		  
		//VALIDACION DE DIRECCION PREDETERMINADA: valida que aparezca en pantalla la direccion predeterminada
			boolean val2 = !(driver.findElements(By.xpath(".//input[@type='checkbox' and @checked='checked']")).size() == 0); // Se pregunta si existe el objeto
			if (val2) 
			{
				  //captura de Evidencia jpg
				  System.out.println("CPA_08: Se valida que aparezca en pantalla la direccion predeterminadan al usuario:" +user);		 
				  TakeScreenShot.takeScreenShot(driver, "CPA_08_val2_evidencia_OK_");
			}
		
			//Cerrar Test
			driver.close();
		
	}
	

}
