package belcorp.test.AñadirProductosBolsa;

import org.testng.annotations.Test;

import belcorp.pages.BolsaCompraPage;
import belcorp.pages.LoginPage;
import belcorp.utils.BrowserFactory;
import belcorp.utils.LogResult;
import belcorp.utils.TakeScreenShot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CPA_12_Anadir_Un_Producto {

	
	@Test
	public void Anadir_Un_Producto() throws InterruptedException
	{
		// Mostrar en Consola Datos de Ejecucion de Prueba
		WebDriver driver = BrowserFactory.startBrowser("chrome",
				"https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
		BolsaCompraPage AgregarABolsa = PageFactory.initElements(driver, BolsaCompraPage.class);
		//Inicializacion de Reporte
		String nombreClase = getClass().getSimpleName();
		LogResult logResult = new LogResult();
		logResult.InicioScript(driver);
		
		try {

			String sku="200086396";
			
			AgregarABolsa.AgregarArticuloPorSKUB(sku);
			
			////************** VALIDACION 1 :  Verificar Carga de página de bolsa de compras*****************
			AgregarABolsa.irABolsaCompra();
			String val1 = driver.getCurrentUrl();
		
			System.out.println("CPA_12: Se valida Url de Bolsa de Compra URL: "+val1);
		    //Esperar 2 Seg
			Thread.sleep(2000);
			// Validacion Visual
			//TakeScreenShot.takeScreenShot(driver, "CPA_12_val1_evidencia_OK_");
			logResult.passLog("Validacion1","Verificar Carga de página de bolsa de compras_OK",driver,nombreClase);
			
			
			//volver atrás
			driver.navigate().back();
		////************** VALIDACION 2 :  valida que producto seleccionado se encuentra en página de compras*****************
			
			AgregarABolsa.buscarProducto(sku);
			
			if (!driver.findElement(By.cssSelector(".headline")).isDisplayed())
			{
				  //captura de Evidencia jpg		 
				  //TakeScreenShot.takeScreenShot(driver, "CPA_012_val2_evidencia_OK_");
				logResult.passLog("Validacion2","SKU seleccionado: "+sku+", se encuentra en página de compras_OK",driver,nombreClase);
			}else
			{
				//System.out.println("El artículo no existe");
				  //captura de Evidencia jpg		 
				  //TakeScreenShot.takeScreenShot(driver, "CPA_012_val2_evidencia_NOK_");
				logResult.errorLog("Validacion2","Producto seleccionado se encuentra en página de compras_NOK",driver,nombreClase);
			}
			

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
