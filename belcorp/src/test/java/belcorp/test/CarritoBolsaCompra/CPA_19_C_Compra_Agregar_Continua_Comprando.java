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
import belcorp.utils.LogResult;
import belcorp.utils.TakeScreenShot;

public class CPA_19_C_Compra_Agregar_Continua_Comprando {


	@Test
	public void C_Compra_Agregar_Continua_Comprando() throws InterruptedException
	{
		// Mostrar en Consola Datos de Ejecucion de Prueba
		WebDriver driver = BrowserFactory.startBrowser("firefox",
				"https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
		
		//Inicializacion de Reporte
		String nombreClase = getClass().getSimpleName();
		LogResult logResult = new LogResult();
		logResult.InicioScript(driver);
		BolsaCompraPage AgregarABolsa = PageFactory.initElements(driver, BolsaCompraPage.class);
		
		try {

			//Función para agregar un artículo. 
			AgregarABolsa.agregarUnArticulo();
				
		////************** VALIDACION 1 y 2 :  Verificar Carga de página de bolsa de compras y Verificar 
		///que el artículo se encuentra en la bolsa de compra*****************
				
		String val2 = driver.getCurrentUrl();
			
		System.out.println("CPA_19: Se valida Url de Bolsa de Compra URL: "+val2);
	    //Esperar 2 Seg
		Thread.sleep(2000);
		// Validacion Visual
		//TakeScreenShot.takeScreenShot(driver, "CPA_19_val2_evidencia_OK_");
		logResult.passLog("Validacion 1 y 2","Carga de página y artículo se encuenra_OK",driver,nombreClase);
		
		
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
			//TakeScreenShot.takeScreenShot(driver, "CPA_19_val3_evidencia_OK_");
			logResult.passLog("Validacion 3_"+i,"Carga de página y Valor para repetir el agregar más artículos_OK",driver,nombreClase);
		    //Esperar 1 Seg
			Thread.sleep(1000);
			i++;
		}
		driver.navigate().refresh();
		Thread.sleep(2000);
		driver.findElement(By.xpath("html/body/main/div[2]/div[6]/div/div/div/div[2]/div[3]/button")).click();
		///************** VALIDACION 4 : Verificar que se encuentra en la página listado de productos
		String val4 = driver.getCurrentUrl();
		
		System.out.println("CPA_19: Se valida Url de página listado de productos URL: "+val4);
	    //Esperar 2 Seg
		Thread.sleep(2000);
		// Validacion Visual
		//TakeScreenShot.takeScreenShot(driver, "CPA_19_val4_evidencia_OK_");
		logResult.passLog("Validacion 4","Se valida Url de página listado de productos_OK",driver,nombreClase);
		
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

