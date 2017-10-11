package belcorp.test.CarritoBolsaCompra;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import belcorp.pages.BolsaCompraPage;
import belcorp.pages.LoginPage;
import belcorp.utils.BrowserFactory;
import belcorp.utils.TakeScreenShot;

public class CPA_19_C_Compra_Agregar_Continua_Comprando {
	
	  //Ingreso de Datos CPA_01
	  public static final String user = "tsoft@yopmail.com";
	  public static final String pass = "Hola123";

	@Test
	public void C_Compra_Agregar_Continua_Comprando() throws InterruptedException
	{
		WebDriver driver = BrowserFactory.startBrowser("firefox","https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
		BolsaCompraPage AgregarABolsa = PageFactory.initElements(driver, BolsaCompraPage.class);
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
			  System.out.println("CPA_19: Usuario logeado en Sistema para Ejecucion: " +username);		 
			  TakeScreenShot.takeScreenShot(driver, "CPA_19_val1_evidencia_OK_");
		  }	 
		  else {
			  TakeScreenShot.takeScreenShot(driver, "CPA_19_val1_evidencia_NOK_");
			  System.out.println("CPA_19: Problema de login con los siguientes datos: " +user+".");
			  login_page.close();
			  Assert.assertTrue(val1, "CPA_19: Problema de login con los siguientes datos: " +user+".");
	  	  }
		  
		//Función para agregar un artículo. 
		AgregarABolsa.agregarUnArticulo();
				
		////************** VALIDACION 1 y 2 :  Verificar Carga de página de bolsa de compras y Verificar 
		///que el artículo se encuentra en la bolsa de compra*****************
				
		String val2 = driver.getCurrentUrl();
			
		System.out.println("CPA_19: Se valida Url de Bolsa de Compra URL: "+val2);
	    //Esperar 2 Seg
		Thread.sleep(2000);
		// Validacion Visual
		TakeScreenShot.takeScreenShot(driver, "CPA_19_val2_evidencia_OK_");
		
	    ////************** VALIDACION 3 :  Verificar Carga de página de bolsa de compras*****************
		//Valor para repetir el agregar más artículos del mismo tipo
		int cantidad = 4;
		int i = 1;
		while (i <= cantidad)
		{
			BolsaCompraPage AgregarABolsaB = PageFactory.initElements(driver, BolsaCompraPage.class);
			AgregarABolsaB.incrementarProducto();
		    //Esperar 2 Seg
			Thread.sleep(2000);
			// Validacion Visual
			TakeScreenShot.takeScreenShot(driver, "CPA_19_val3_evidencia_OK_");
		    //Esperar 1 Seg
			Thread.sleep(1000);
			i++;
		}

		///************** VALIDACION 4 : Verificar que se encuentra en la página listado de productos
		String val6 = driver.getCurrentUrl();
		
		System.out.println("CPA_19: Se valida Url de página listado de productos URL: "+val6);
	    //Esperar 2 Seg
		Thread.sleep(2000);
		// Validacion Visual
		TakeScreenShot.takeScreenShot(driver, "CPA_19_val4_evidencia_OK_");
		
		
   
		// CERRAR DRIVER 
		driver.close();
	}


}

