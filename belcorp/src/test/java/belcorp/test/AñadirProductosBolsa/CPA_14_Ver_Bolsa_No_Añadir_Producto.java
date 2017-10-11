package belcorp.test.A�adirProductosBolsa;

import org.testng.annotations.Test;
import belcorp.pages.BolsaCompraPage;
import belcorp.utils.BrowserFactory;
import belcorp.utils.TakeScreenShot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class CPA_14_Ver_Bolsa_No_A�adir_Producto {
	
	@Test
	public void Ver_Bolsa_No_A�adir_Producto() throws InterruptedException
	{
		WebDriver driver = BrowserFactory.startBrowser("firefox","https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
		BolsaCompraPage AgregarABolsa = PageFactory.initElements(driver, BolsaCompraPage.class);
		
		//Ejecuci�n de la funci�n No Agregar Art�culo.
		AgregarABolsa.NoAgregarArticulo();
		
		////************** VALIDACION 1 : Verificar que contador de productos se encuentra en cero*****************
		///No Aplica, ya que en la validaci�n 3 se valida que no hay productos en la bolsa de compras.
		
		////************** VALIDACION 2 :  Verificar Carga de p�gina de bolsa de compras*****************
		
		String val1 = driver.getCurrentUrl();
	
		System.out.println("CPA_14: Se valida Url de Bolsa de Compra URL: "+val1);
	    //Esperar 2 Seg
		Thread.sleep(2000);
		// Validacion Visual
		TakeScreenShot.takeScreenShot(driver, "CPA_14_val2_evidencia_OK_");
		
		//volver a la p�gina inicial para verificar el listado de productos en 0.
		driver.navigate().back();
		
    	////************** VALIDACION 3 : verificar que la bolsa de compras no tiene ning�n producto*****************
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.id("cantItemsCart"))).perform();
		//captura de Evidencia jpg		 
		  TakeScreenShot.takeScreenShot(driver, "CPA_014_val3_evidencia_OK_");
		
		// CERRAR DRIVER 
		driver.close();
	}
	
}