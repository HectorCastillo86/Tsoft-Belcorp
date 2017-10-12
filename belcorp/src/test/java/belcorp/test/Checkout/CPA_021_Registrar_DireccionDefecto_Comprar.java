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
import belcorp.utils.LogResult;
import belcorp.utils.TakeScreenShot;

public class CPA_021_Registrar_DireccionDefecto_Comprar {
	
	  //Ingreso de Datos CPA_01
	  public static final String user = "tsoft@yopmail.com";
	  public static final String pass = "Hola123";


	@Test
	public void Registrar_DireccionDefecto_Comprar() throws InterruptedException, IOException {
		String sku = null;
		// Mostrar en Consola Datos de Ejecucion de Prueba
		System.out.println("CPA_01: Datos para Ejecucion Usuario: " + user + " , Password : " + pass);
		WebDriver driver = BrowserFactory.startBrowser("firefox",
				"https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
		LoginPage login_page = PageFactory.initElements(driver, LoginPage.class);
		
		//Inicializacion de Reporte
		String nombreClase = getClass().getSimpleName();
		LogResult logResult = new LogResult();
		logResult.InicioScript(driver);
		BolsaCompraPage AgregarABolsa = PageFactory.initElements(driver, BolsaCompraPage.class);
		CheckOutPage CheckOutPagar = PageFactory.initElements(driver, CheckOutPage.class);
		List<List<String>> excelDatos = belcorp.utils.Excel.leerArchivoXLSX("Datos.xlsx", 0);
		
		try {

			login_page.loginBelcorp(user, pass);

			// *******************VALIDACION 1: verificar nick de usuario logeado****************

			// Validar si existe AccountName Nombre de usuario Registrado
			boolean val1 = !(driver.findElements(By.xpath("//span[@class='accountName']")).size() == 0); // Existe
																										 // elemento?
			if (val1) {
				Actions act1 = new Actions(driver);
				act1.moveToElement(driver.findElement(By.xpath(".//*[@class='accountLi']"))).perform();
				// Esperar 2 Seg
				Thread.sleep(2000);
				String username = driver.findElement(By.xpath("//span[@class='accountName']")).getText();
				System.out.println("CPA_01: Usuario logeado en Sistema para Ejecucion: " + username);
				//TakeScreenShot.takeScreenShot(driver, "CPA_01_val1_evidencia_OK_");

				 logResult.passLog("Validacion1","Login Exitoso: "+user+", "+pass,driver,nombreClase);
				 
				 
			} else {
				//TakeScreenShot.takeScreenShot(driver, "CPA_01_val1_evidencia_NOK_");
				System.out.println("CPA_01: Problema de login con los siguientes datos: " + user + ".");
				logResult.errorLog("Validacion1","Login No Exitoso: "+user+", "+pass,driver,nombreClase);
				login_page.close();
				// logResult.errorLog("CPA_01"+user,"Se detecto correctamente'",driver);
				logResult.crearLog(nombreClase);
				Assert.assertTrue(val1, "CPA_01: Problema de login con los siguientes datos: " + user + ".");
			}
			
		  
		////********Validaci�n 2: Verificar que el art�culo se a�adio a listado resumen****************  
		  Thread.sleep(500);
		  //Funci�n para agregar varios art�culos.
        for (int i = 1; i < excelDatos.size(); i++) {
            List<?> list = (List<?>) excelDatos.get(i);
            sku = list.get(0).toString();
            BolsaCompraPage AgregarABolsaB = PageFactory.initElements(driver, BolsaCompraPage.class);
            AgregarABolsaB.AgregarArticuloPorSKUB(sku);
          
            //TakeScreenShot.takeScreenShot(driver, "CPA_20_val2_evidencia_OK_");  
          ///verificar que contador de productos cambie por consola
            //System.out.println("SKU en la posici�n "+i+" es:"+sku);
            logResult.passLog("Validacion 2_"+i,"art�culo SKU: "+sku+", se a�adi� a listado resumen",driver,nombreClase);
            
     	}
      //Ir a bolsa de compra
      AgregarABolsa.irABolsaCompra(); 

      ////********Validaci�n 3: Verificar que nos redirige a bolsa de compra  
        
		String val2 = driver.getCurrentUrl();
		
		//System.out.println("CPA_16: Se valida Url de Bolsa de Compra URL: "+val2);
	    //Esperar 2 Seg
		Thread.sleep(2000);
		// Validacion Visual
		//TakeScreenShot.takeScreenShot(driver, "CPA_16_val2_evidencia_OK_");
		logResult.passLog("Validacion 3","Carga de p�gina de bolsa de compras_OK",driver,nombreClase);
		
		
		////********Validaci�n 4: Verificar que nos redirige a p�gina CheckOut
		driver.navigate().refresh();
		Thread.sleep(2000);
		if(!driver.findElement(By.className("pageBagSubTitle")).getText().equals("No tienes productos en tu bolsa"))
		{
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[@class='btn btn-block checkoutButton continueCheckout']")).click();
			Thread.sleep(1000);
			
			String val7 = driver.getCurrentUrl();
			
			//System.out.println("CPA_18: Se valida Url de p�gina CheckOut URL: "+val6);
		    //Esperar 2 Seg
			Thread.sleep(2000);
			// Validacion Visual
			//TakeScreenShot.takeScreenShot(driver, "CPA_18_val7_evidencia_OK_");
			logResult.passLog("Validacion 4","Carga de p�gina CheckOut_OK",driver,nombreClase);
		}
		else 
		{
			//Esperar 2 Seg
			Thread.sleep(2000);
			logResult.errorLog("Validacion 4","Carga de p�gina CheckOut_NOK",driver,nombreClase);
			
		
		}
		
		
		////********Validaci�n 5: Verificar despliegue de p�gina de opciones de env�o
		Thread.sleep(2000);
		CheckOutPagar.ContinuarOpcionesEnvio();
	    //Esperar 2 Seg
		Thread.sleep(2000);
		// Validacion Visual
		//TakeScreenShot.takeScreenShot(driver, "CPA_20_val6_evidencia_OK_");
		logResult.passLog("Validacion 5","Despliegue de p�gina de opciones de env�o_OK",driver,nombreClase);
		
	    ////********Validaci�n 6: Verificar despliegue de p�gina de opciones de pago
		CheckOutPagar.ContinuarMetodoPago();
	    //Esperar 2 Seg
		Thread.sleep(2000);
		// Validacion Visual
		//TakeScreenShot.takeScreenShot(driver, "CPA_20_val7_evidencia_OK_");
		logResult.passLog("Validacion 6","Despliegue de p�gina de opciones de pago_OK",driver,nombreClase);
		
		
	    ////********Validaci�n 7: Verificar despliegue de opci�n pago en efectivo
		CheckOutPagar.MetodoPagoEfectivo();
	    //Esperar 2 Seg
		Thread.sleep(2000);
		// Validacion Visual
		//TakeScreenShot.takeScreenShot(driver, "CPA_20_val8_evidencia_OK_");
		logResult.passLog("Validacion 7","Despliegue de opci�n pago en efectivo_OK",driver,nombreClase);
		
		
	    ////********Validaci�n 8: Verificar revisi�n del pedido
		CheckOutPagar.RevisarPedido();
	    //Esperar 2 Seg
		Thread.sleep(2000);
		// Validacion Visual
		//TakeScreenShot.takeScreenShot(driver, "CPA_20_val9_evidencia_OK_");
		logResult.passLog("Validacion 8","Verificar revisi�n del pedido_OK",driver,nombreClase);
		
		
		
	    ////********Validaci�n 9: Verificar compra reservada mediante mensaje generado
		Thread.sleep(1000);
		CheckOutPagar.FinalizarPedido();
	    //Esperar 2 Seg
		Thread.sleep(2000);
		// Validacion Visual
		//TakeScreenShot.takeScreenShot(driver, "CPA_20_val10_evidencia_OK_");
		logResult.passLog("Validacion 9","Verificar compra reservada_OK",driver,nombreClase);
		
		// Cerrar Test
		driver.close();
		logResult.crearLog(nombreClase);
	
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logResult.errorLog("Error Inesperado", "Error exception: "+e, driver, nombreClase);
			driver.close();
			logResult.crearLog(nombreClase);
	
		}
		
	}

}
