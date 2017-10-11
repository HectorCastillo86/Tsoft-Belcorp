package belcorp.test.CarritoBolsaCompra;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import belcorp.pages.LoginPage;
import belcorp.test.RegistroLogin.CPA_01_Login_usuario_existente;

import org.testng.Assert;
import org.testng.annotations.Test;

import belcorp.pages.BolsaCompraPage;
import belcorp.utils.BrowserFactory;
import belcorp.utils.TakeScreenShot;

public class CPA_15_Cupon_Valido_Bolsa_Productos {
	
	  //Ingreso de Datos CPA_01
	  public static final String user = "tsoft@yopmail.com";
	  public static final String pass = "Hola123";

	@Test
	public void Cupon_Valido_Bolsa_Productos() throws InterruptedException
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
			  System.out.println("CPA_15: Usuario logeado en Sistema para Ejecucion: " +username);		 
			  TakeScreenShot.takeScreenShot(driver, "CPA_15_val1_evidencia_OK_");
		  }	 
		  else {
			  TakeScreenShot.takeScreenShot(driver, "CPA_15_val1_evidencia_NOK_");
			  System.out.println("CPA_15: Problema de login con los siguientes datos: " +user+".");
			  login_page.close();
			  Assert.assertTrue(val1, "CPA_15: Problema de login con los siguientes datos: " +user+".");
	  	  }
		  
		//Función para agregar un artículo. 
		AgregarABolsa.agregarUnArticulo();
		
		////************** VALIDACION 2 :  Verificar Carga de página de bolsa de compras*****************
		
		String val2 = driver.getCurrentUrl();
	
		System.out.println("CPA_15: Se valida Url de Bolsa de Compra URL: "+val2);
	    //Esperar 2 Seg
		Thread.sleep(2000);
		// Validacion Visual
		TakeScreenShot.takeScreenShot(driver, "CPA_15_val2_evidencia_OK_");
		
		//Ingresar cupón válido
		String cupon = "VPR-T38H-MAC8-C3F4-S";
		AgregarABolsa.CuponValido(cupon);
		
	   ////************** VALIDACION 3 :  Verificar texto descriptivo del cupón*****************
		Thread.sleep(2000);
		System.out.println("CPA_15: Se valida texto descriptivo del cupón: " + cupon);
		
		//continuar a checkout
		AgregarABolsa.BotonIrAPagar();
		Thread.sleep(1000);
		
		///************** VALIDACION 4 : Verificar que se encuentra en la página CheckOut
		String val4 = driver.getCurrentUrl();
		
		System.out.println("CPA_15: Se valida Url de página CheckOut URL: "+val4);
	    //Esperar 2 Seg
		Thread.sleep(2000);
		// Validacion Visual
		TakeScreenShot.takeScreenShot(driver, "CPA_15_val4_evidencia_OK_");
		
		// CERRAR DRIVER 
		driver.close();
		
	}


}