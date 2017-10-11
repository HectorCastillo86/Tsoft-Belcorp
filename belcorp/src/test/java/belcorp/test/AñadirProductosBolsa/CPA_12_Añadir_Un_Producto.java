package belcorp.test.AñadirProductosBolsa;

import org.testng.annotations.Test;

import belcorp.pages.BolsaCompraPage;
import belcorp.utils.BrowserFactory;
import belcorp.utils.TakeScreenShot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CPA_12_Añadir_Un_Producto {
	
	@Test
	public void Añadir_Un_Producto() throws InterruptedException
	{
		WebDriver driver = BrowserFactory.startBrowser("firefox","https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
		BolsaCompraPage AgregarABolsa = PageFactory.initElements(driver, BolsaCompraPage.class);
		
		String sku="200086396";
		
		AgregarABolsa.AgregarArticuloPorSKUB(sku);
		
		////************** VALIDACION 1 :  Verificar Carga de página de bolsa de compras*****************
		AgregarABolsa.irABolsaCompra();
		String val1 = driver.getCurrentUrl();
	
		System.out.println("CPA_12: Se valida Url de Bolsa de Compra URL: "+val1);
	    //Esperar 2 Seg
		Thread.sleep(2000);
		// Validacion Visual
		TakeScreenShot.takeScreenShot(driver, "CPA_12_val1_evidencia_OK_");
		
		//volver atrás
		driver.navigate().back();
	////************** VALIDACION 2 :  valida que producto seleccionado se encuentra en página de compras*****************
		
		AgregarABolsa.buscarProducto(sku);
		
		if (!driver.findElement(By.cssSelector(".headline")).isDisplayed())
		{
			  //captura de Evidencia jpg		 
			  TakeScreenShot.takeScreenShot(driver, "CPA_012_val2_evidencia_OK_");
		}else
		{
			System.out.println("El artículo no existe");
			  //captura de Evidencia jpg		 
			  TakeScreenShot.takeScreenShot(driver, "CPA_012_val2_evidencia_NOK_");
		}
		
		// CERRAR DRIVER 
		driver.close();
	}


}
