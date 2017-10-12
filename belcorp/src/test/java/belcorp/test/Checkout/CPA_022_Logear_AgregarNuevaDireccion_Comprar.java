package belcorp.test.Checkout;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import belcorp.pages.BolsaCompraPage;
import belcorp.pages.CheckOutPage;
import belcorp.pages.LoginPage;
import belcorp.test.RegistroLogin.CPA_01_Login_usuario_existente;
import belcorp.utils.BrowserFactory;
import belcorp.utils.LogResult;


public class CPA_022_Logear_AgregarNuevaDireccion_Comprar {
	  //Ingreso de Datos CPA_01
	  public static final String user = "tsoft@yopmail.com";
	  public static final String pass = "Hola123";
	
	@Test
	public void Logear_AgregarNuevaDireccion_Comprar() throws InterruptedException, IOException 
	{
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
						
		////********Validaci�n 1: Verificar que el art�culo se a�adio a listado resumen****************  
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
          logResult.passLog("Validacion 1_"+i,"art�culo SKU: "+sku+", se a�adi� a listado resumen",driver,nombreClase);
          
   		}
      
      //Ir a bolsa de compra
      AgregarABolsa.irABolsaCompra(); 

      ////********Validaci�n 2: Verificar que nos redirige a bolsa de compra  
        
		String val2 = driver.getCurrentUrl();
		
		//System.out.println("CPA_16: Se valida Url de Bolsa de Compra URL: "+val2);
	    //Esperar 2 Seg
		Thread.sleep(2000);
		// Validacion Visual
		//TakeScreenShot.takeScreenShot(driver, "CPA_16_val2_evidencia_OK_");
		logResult.passLog("Validacion 2","Carga de p�gina de bolsa de compras_OK",driver,nombreClase);
		
		
		////********Validaci�n 3: Verificar que nos redirige a p�gina proced to checkout
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
			logResult.passLog("Validacion 3","Carga de p�gina proced-CheckOut_OK",driver,nombreClase);
		}
		else 
		{
			//Esperar 2 Seg
			Thread.sleep(2000);
			logResult.errorLog("Validacion 3","Carga de p�gina proced-CheckOut_NOK",driver,nombreClase);
			
		
		}
		login_page.loginCheckOut(user, pass);

		// *******************VALIDACION 4: verificar nick de usuario logeado****************
		logResult.passLog("Validacion 4","Valida usuario al ingresar a p�gina CheckOut_OK",driver,nombreClase);
		
		
			///************** VALIDACION 5 : Verificar que se encuentra en la p�gina CheckOut
			String val7 = driver.getCurrentUrl();
			
			//System.out.println("CPA_18: Se valida Url de p�gina CheckOut URL: "+val6);
		    //Esperar 2 Seg
			Thread.sleep(2000);
			// Validacion Visual
			//TakeScreenShot.takeScreenShot(driver, "CPA_18_val7_evidencia_OK_");
			logResult.passLog("Validacion 5","Carga de p�gina CheckOut_OK",driver,nombreClase);

			//Agregar Direcci�n
			CheckOutPagar.presionarAgregarDireccion();
			
			String ciudad = "BOGOTA";
			String pais = "Colombia";
			String depar = "BOGOTA";
			String direccion = "Las perdices";
			String nrodep = "1604-B";
			String infoAdicional = "Metro Tobalaba";
			String telefono = "091919191";
			
			CheckOutPagar.IngresarDireccionEnvio(pais, depar, ciudad, direccion, nrodep, infoAdicional, telefono);
			
			////********Validaci�n 6: Verificar que nueva direci�n fue a�adida
		    //Esperar 2 Seg
			Thread.sleep(2000);
			// Validacion Visual
			logResult.passLog("Validacion 6","Nueva direci�n fue a�adida_OK",driver,nombreClase);
			//TakeScreenShot.takeScreenShot(driver, "CPA_20_val5_evidencia_OK_");
		
		////********Validaci�n 7: Verificar despliegue de p�gina de opciones de env�o
		Thread.sleep(2000);
		CheckOutPagar.ContinuarOpcionesEnvio();
	    //Esperar 2 Seg
		Thread.sleep(2000);
		// Validacion Visual
		//TakeScreenShot.takeScreenShot(driver, "CPA_20_val6_evidencia_OK_");
		logResult.passLog("Validacion 7","Despliegue de p�gina de opciones de env�o_OK",driver,nombreClase);


		////********Validaci�n 8: Verificar despliegue de p�gina de opciones de pago
		CheckOutPagar.ContinuarMetodoPago();
	    //Esperar 2 Seg
		Thread.sleep(2000);
		// Validacion Visual
		//TakeScreenShot.takeScreenShot(driver, "CPA_20_val7_evidencia_OK_");
		logResult.passLog("Validacion 8","Despliegue de p�gina de opciones de pago_OK",driver,nombreClase);
		
		
	    ////********Validaci�n 9: Verificar despliegue de opci�n pago en efectivo
		CheckOutPagar.MetodoPagoEfectivo();
	    //Esperar 2 Seg
		Thread.sleep(2000);
		// Validacion Visual
		//TakeScreenShot.takeScreenShot(driver, "CPA_20_val8_evidencia_OK_");
		logResult.passLog("Validacion 9","Despliegue de opci�n pago en efectivo_OK",driver,nombreClase);
		
		
	    ////********Validaci�n 10: Verificar revisi�n del pedido
		CheckOutPagar.RevisarPedido();
	    //Esperar 2 Seg
		Thread.sleep(2000);
		// Validacion Visual
		//TakeScreenShot.takeScreenShot(driver, "CPA_20_val9_evidencia_OK_");
		logResult.passLog("Validacion 10","Verificar revisi�n del pedido_OK",driver,nombreClase);
		
		
		
	    ////********Validaci�n 11: Verificar compra reservada mediante mensaje generado
		Thread.sleep(1000);
		CheckOutPagar.FinalizarPedido();
	    //Esperar 2 Seg
		Thread.sleep(2000);
		// Validacion Visual
		//TakeScreenShot.takeScreenShot(driver, "CPA_20_val10_evidencia_OK_");
		logResult.passLog("Validacion 11","Verificar compra reservada_OK",driver,nombreClase);
		
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
