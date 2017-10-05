package belcorp.test.MiCuentaOpciones;

import org.testng.Assert;
import org.testng.annotations.Test;
import belcorp.pages.LoginPage;
import belcorp.pages.MiCuentaMisPedidosPage;
import belcorp.utils.BrowserFactory;
import belcorp.utils.TakeScreenShot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class CPA_05_MiCuenta_Mis_Pedidos_Realizados {
	
	//Ingreso de Datos CPA_05
	  public static final String user = "tsoft@yopmail.com";
	  public static final String pass = "Hola123";
	
	@Test
	public void CPA_01() throws InterruptedException {
		
		WebDriver driver = BrowserFactory.startBrowser("firefox","https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
		MiCuentaMisPedidosPage misPedidos = PageFactory.initElements(driver, MiCuentaMisPedidosPage.class);
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
			  System.out.println("CPA_05: Usuario logeado en Sistema para Ejecucion: " +username);		 
			  TakeScreenShot.takeScreenShot(driver, "CPA_05_val1_evidencia_OK_");
		  }	 
		  else {
			  TakeScreenShot.takeScreenShot(driver, "CPA_05_val1_evidencia_NOK_");
			  System.out.println("CPA_05: Problema de login con los siguientes datos: " +user+".");
			  login_page.close();
			  Assert.assertTrue(val1, "CPA_05: Problema de login con los siguientes datos: " +user+".");
	  	  } 
		
		//Ingreso a Mis Pedidos
		misPedidos.MisPedidos();
		
		//Validacion 2 : verificar que hemos accedido a la página "Mis pedidos realizados" mediante el title de ésta.
		String val2;
		val2 = driver.getTitle();
		System.out.println(val2);
		Assert.assertEquals("Order History | Belcorp Site Colombi", val2);
		
		
	}
}
