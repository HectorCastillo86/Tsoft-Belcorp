package belcorp.test.Checkout;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import belcorp.pages.AgregarDireccionPage;
import belcorp.pages.BolsaCompraPage;
import belcorp.pages.CheckOutPage;
import belcorp.pages.LoginPage;
import belcorp.test.RegistroLogin.CPA_01_Login_usuario_existente;
import belcorp.utils.BrowserFactory;
import belcorp.utils.TakeScreenShot;

public class CPA_021_Registrar_DireccionDefecto_Comprar {
	
	  //Ingreso de Datos CPA_01
	  public static final String user = "tsoft@yopmail.com";
	  public static final String pass = "Hola123";


	@Test
	public void Registrar_AgregarNuevaDireccion_Comprar() throws InterruptedException, IOException {
		String sku = null;
		WebDriver driver = BrowserFactory.startBrowser("firefox","https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
		LoginPage login_page = PageFactory.initElements(driver, LoginPage.class);
		BolsaCompraPage AgregarABolsa = PageFactory.initElements(driver, BolsaCompraPage.class);
		CheckOutPage CheckOutPagar = PageFactory.initElements(driver, CheckOutPage.class);
		List<List<String>> excelDatos = belcorp.utils.Excel.leerArchivoXLSX("Datos.xlsx", 0);
		
		// Login Belcorp
		login_page.loginBelcorp(user, pass);
		Thread.sleep(2000);
		//Validar si existe AccountName Nombre de usuario Registrado
		  boolean val1 = !(driver.findElements(By.xpath("//span[@class='accountName']")).size() == 0);
		  if (val1) {
			  Actions act1 = new Actions(driver);
			  act1.moveToElement(driver.findElement(By.xpath(".//*[@class='accountLi']"))).perform();
			  //Esperar 1.5 Seg
			  Thread.sleep(1500);
			  String username = driver.findElement(By.xpath("//span[@class='accountName']")).getText();
			  System.out.println("CPA_21: Usuario logeado en Sistema para Ejecucion: " +username);		 
			  TakeScreenShot.takeScreenShot(driver, "CPA_21_val01_evidencia_OK_");
		  }	 
		  else {
			  TakeScreenShot.takeScreenShot(driver, "CPA_21_val01_evidencia_NOK_");
			  System.out.println("CPA_21: Problema de login con los siguientes datos: " +user+".");
			  login_page.close();
			  Assert.assertTrue(val1, "CPA_21: Problema de login con los siguientes datos: " +user+".");
	  	  }
		  
		  
		////********Validación 2: Verificar que el artículo se añadio a listado resumen****************  
		  Thread.sleep(500);
		  //Función para agregar varios artículos.
        for (int i = 1; i < excelDatos.size(); i++) {
            List<?> list = (List<?>) excelDatos.get(i);
            sku = list.get(0).toString();
            BolsaCompraPage AgregarABolsaB = PageFactory.initElements(driver, BolsaCompraPage.class);
            AgregarABolsaB.AgregarArticuloPorSKUB(sku);
          
            TakeScreenShot.takeScreenShot(driver, "CPA_21_val02_evidencia_OK_");  
          ///verificar que contador de productos cambie por consola
            System.out.println("SKU en la posición "+i+" es:"+sku);
            
     	}
      //Ir a bolsa de compra
      AgregarABolsa.irABolsaCompra(); 

      ////********Validación 3: Verificar que nos redirige a bolsa de compra  
        
		String val3 = driver.getCurrentUrl();
		
		System.out.println("CPA_21: Se valida Url de Bolsa de Compra URL: "+val3);
	    //Esperar 2 Seg
		Thread.sleep(2000);
		// Validacion Visual
		TakeScreenShot.takeScreenShot(driver, "CPA_21_val03_evidencia_OK_");
		
		////********Validación 4: Verificar que nos redirige a página CheckOut
      AgregarABolsa.BotonIrAPagar();
		Thread.sleep(1000);
		String val4 = driver.getCurrentUrl();
		
		System.out.println("CPA_21: Se valida Url de página CheckOut URL: "+val4);
	    //Esperar 2 Seg
		Thread.sleep(2000);
		// Validacion Visual
		TakeScreenShot.takeScreenShot(driver, "CPA_21_val04_evidencia_OK_");
				
		////********Validación 5: Verificar despliegue de página de opciones de envío
		
		CheckOutPagar.ContinuarOpcionesEnvio();
	    //Esperar 2 Seg
		Thread.sleep(2000);
		// Validacion Visual
		TakeScreenShot.takeScreenShot(driver, "CPA_21_val05_evidencia_OK_");
		
	    ////********Validación 6: Verificar despliegue de página de opciones de pago
		CheckOutPagar.ContinuarMetodoPago();
	    //Esperar 2 Seg
		Thread.sleep(2000);
		// Validacion Visual
		TakeScreenShot.takeScreenShot(driver, "CPA_21_val06_evidencia_OK_");
		
	    ////********Validación 7: Verificar despliegue de opción pago en efectivo
		CheckOutPagar.MetodoPagoEfectivo();
	    //Esperar 2 Seg
		Thread.sleep(2000);
		// Validacion Visual
		TakeScreenShot.takeScreenShot(driver, "CPA_21_val07_evidencia_OK_");
		
	    ////********Validación 8: Verificar revisión del pedido
		CheckOutPagar.RevisarPedido();
	    //Esperar 2 Seg
		Thread.sleep(2000);
		// Validacion Visual
		TakeScreenShot.takeScreenShot(driver, "CPA_21_val08_evidencia_OK_");
		
	    ////********Validación 9: Verificar compra reserveda mediante mensaje generado
		CheckOutPagar.FinalizarPedido();
	    //Esperar 2 Seg
		Thread.sleep(2000);
		// Validacion Visual
		TakeScreenShot.takeScreenShot(driver, "CPA_21_val09_evidencia_OK_");

	
	// CERRAR DRIVER 
	driver.close();
		
	}

}
