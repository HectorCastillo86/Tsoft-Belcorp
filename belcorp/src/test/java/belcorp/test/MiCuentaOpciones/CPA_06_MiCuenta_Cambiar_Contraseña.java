package belcorp.test.MiCuentaOpciones;

import org.testng.Assert;
import org.testng.annotations.Test;
import belcorp.pages.MiCuentaCambiarContrase�aPage;
import belcorp.test.RegistroLogin.CPA_01_Login_usuario_existente;
import belcorp.utils.BrowserFactory;
import belcorp.utils.TakeScreenShot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import belcorp.pages.LoginPage;

public class CPA_06_MiCuenta_Cambiar_Contrase�a {

	//Datos CPA_06
	String user=CPA_01_Login_usuario_existente.user;
	String pass=CPA_01_Login_usuario_existente.pass;
	public static String nuevaContrase�a="Hola123";
	String repetirConrase�a="Hola123";
	
	
	@Test
	public void MiCuenta_Cambiar_Contrase�a() throws InterruptedException
	{

		//Mostrar en Consola Datos de Ejecucion de Prueba
		System.out.println("CPA_06: Datos para Ejecucion Usuario: " +user+" , Password Anterior: "+pass+" , Password Nueva: "+nuevaContrase�a);
		
		//Inicilaizar Variables
		WebDriver driver = BrowserFactory.startBrowser("firefox","https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
		MiCuentaCambiarContrase�aPage cambiarContrase�a = PageFactory.initElements(driver, MiCuentaCambiarContrase�aPage.class);
		LoginPage login_page = PageFactory.initElements(driver, LoginPage.class);
		
		// Login Belcorp
		login_page.loginBelcorp(user, pass);
		
		//Validar si existe AccountName Nombre de usuario Registrado
		  boolean val1 = !(driver.findElements(By.xpath("//span[@class='accountName']")).size() == 0);
		  if (val1) {
			  Actions act1 = new Actions(driver);
			  act1.moveToElement(driver.findElement(By.xpath(".//*[@class='accountLi']"))).perform();
			  //Esperar 1.5 Seg
			  Thread.sleep(1500);
			  String username = driver.findElement(By.xpath("//span[@class='accountName']")).getText();
			  System.out.println("CPA_06: Usuario logeado en Sistema para Ejecucion: " +username);		 
			  TakeScreenShot.takeScreenShot(driver, "CPA_06_val1_evidencia_OK_");
		  }	 
		  else {
			  TakeScreenShot.takeScreenShot(driver, "CPA_06_val1_evidencia_NOK_");
			  System.out.println("CPA_06: Problema de login con los siguientes datos: " +user+".");
			  login_page.close();
			  Assert.assertTrue(val1, "CPA_06: Problema de login con los siguientes datos: " +user+".");
	  	  } 
		
		//Cambio de Contrase�a
		cambiarContrase�a.cambiarContrase�a(pass, nuevaContrase�a, repetirConrase�a);
		
		//Validacion 2 : verificar que nos encontramos en "Cambiar de contrase�a".
				String val2;
				val2 = driver.getTitle();
				System.out.println("CPA_06: Titulo de Pagina Cambiar Contrase�a: "+val2);
				//Esperar 1.5 Seg
				Thread.sleep(1500);
				TakeScreenShot.takeScreenShot(driver, "CPA_06_val2_evidencia_");
				Assert.assertEquals("Update Forgotten Password | Belcorp Site Colombia", val2);
				driver.close();
		
	}


	
}
