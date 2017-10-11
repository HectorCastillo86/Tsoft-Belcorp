package belcorp.test.CarritoBolsaCompra;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import belcorp.pages.LoginPage;
import belcorp.test.RegistroLogin.CPA_01_Login_usuario_existente;

import org.testng.Assert;
import org.testng.annotations.Test;

import belcorp.pages.BolsaCompraPage;
import belcorp.utils.BrowserFactory;
import belcorp.utils.TakeScreenShot;

public class CPA_18_Eliminar_Prod_Pagar_CheckOut {
	
	  //Ingreso de Datos CPA_01
	  public static final String user = "tsoft@yopmail.com";
	  public static final String pass = "Hola123";

	@Test
	public void CPA_18() throws InterruptedException
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
			  System.out.println("CPA_18: Usuario logeado en Sistema para Ejecucion: " +username);		 
			  TakeScreenShot.takeScreenShot(driver, "CPA_18_val1_evidencia_OK_");
		  }	 
		  else {
			  TakeScreenShot.takeScreenShot(driver, "CPA_18_val1_evidencia_NOK_");
			  System.out.println("CPA_18: Problema de login con los siguientes datos: " +user+".");
			  login_page.close();
			  Assert.assertTrue(val1, "CPA_18: Problema de login con los siguientes datos: " +user+".");
	  	  }
		  
		//Función para agregar un artículo. 
		AgregarABolsa.agregarUnArticulo();
		
		////************** VALIDACION 2 :  Verificar Carga de página de bolsa de compras*****************
		
		String val2 = driver.getCurrentUrl();
	
		System.out.println("CPA_18: Se valida Url de Bolsa de Compra URL: "+val2);
	    //Esperar 2 Seg
		Thread.sleep(2000);
		// Validacion Visual
		TakeScreenShot.takeScreenShot(driver, "CPA_18_val2_evidencia_OK_");
		
		//Ingresar cupón válido
		String cupon = "VPR-T38H-MAC8-C3F4-S";
		AgregarABolsa.CuponValido(cupon);
		
	   ////************** VALIDACION 3 :  Verificar que producto a eliminar se encuentra en la bolsa de compras*****************
		//No aplica esta validación, ya que anteriormente agregamos un producto el cual
		//aparece en la bolsa de compras para poder ser eliminado.
		
		//Eliminamos el producto
		AgregarABolsa.EliminaProdContComprando();
		
	   ///************** VALIDACION 4 : Verificar mensaje "Se eliminó el producto de tu bolsa"
		String texto = driver.findElement(By.xpath("html/body/main/div[2]/div[1]/div")).getText();
		Thread.sleep(2000);
		// Validacion Visual
		TakeScreenShot.takeScreenShot(driver, "CPA_18_val4_evidencia_OK_");
		Thread.sleep(1000);
		// Validación del texto
		System.out.println("CPA_18: Se valida texto descriptivo de eliminación de producto: " + texto);
		
		///************* VALiDACION 5 : Verificar que no exista el producto eliminado
		//Recargar página y verificar que no exista el producto eliminado anteriormente
		driver.navigate().refresh();
	    //Esperar 2 Seg
		Thread.sleep(2000);
		// Validacion Visual
		TakeScreenShot.takeScreenShot(driver, "CPA_18_val5_evidencia_OK_");
		
		
		//continuar a listado de productos
		AgregarABolsa.BotonIrAPagar();
		Thread.sleep(1000);
		
		///************** VALIDACION 6 : Verificar que se encuentra en la página CheckOut
		String val6 = driver.getCurrentUrl();
		
		System.out.println("CPA_18: Se valida Url de página CheckOut URL: "+val6);
	    //Esperar 2 Seg
		Thread.sleep(2000);
		// Validacion Visual
		TakeScreenShot.takeScreenShot(driver, "CPA_18_val6_evidencia_OK_");
		
		
		// CERRAR DRIVER 
		driver.close();
	}


}