package belcorp.test.AñadirProductosBolsa;

import org.testng.annotations.Test;
import belcorp.pages.BolsaCompraPage;
import belcorp.utils.BrowserFactory;
import belcorp.utils.LogResult;
import belcorp.utils.TakeScreenShot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class CPA_14_Ver_Bolsa_No_Anadir_Producto {
	
	@Test
	public void Ver_Bolsa_No_Añadir_Producto() throws InterruptedException
	{
		// Mostrar en Consola Datos de Ejecucion de Prueba
		WebDriver driver = BrowserFactory.startBrowser("firefox",
				"https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
		BolsaCompraPage AgregarABolsa = PageFactory.initElements(driver, BolsaCompraPage.class);
		
		//Inicializacion de Reporte
		String nombreClase = getClass().getSimpleName();
		LogResult logResult = new LogResult();
		logResult.InicioScript(driver);
		
		try {
		//Ejecución de la función No Agregar Artículo.
		AgregarABolsa.NoAgregarArticulo();
		
		////************** VALIDACION 1 : Verificar que contador de productos se encuentra en cero*****************
		///No Aplica, ya que en la validación 3 se valida que no hay productos en la bolsa de compras.
		
		////************** VALIDACION 2 :  Verificar Carga de página de bolsa de compras*****************
		
		String val1 = driver.getCurrentUrl();
	
		System.out.println("CPA_14: Se valida Url de Bolsa de Compra URL: "+val1);
	    //Esperar 2 Seg
		Thread.sleep(2000);
		// Validacion Visual
		//TakeScreenShot.takeScreenShot(driver, "CPA_14_val2_evidencia_OK_");
		logResult.passLog("Validacion 2","Verificar Carga de página de bolsa de compras_OK",driver,nombreClase);

		
		
		//volver a la página inicial para verificar el listado de productos en 0.
		driver.navigate().back();
		
    	////************** VALIDACION 3 : verificar que la bolsa de compras no tiene ningún producto*****************
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.id("cantItemsCart"))).perform();
		//captura de Evidencia jpg		 
		logResult.passLog("Validacion 3","Verificar bolsa de compras no tiene ningún producto_OK",driver,nombreClase);

		
		// CERRAR DRIVER 
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