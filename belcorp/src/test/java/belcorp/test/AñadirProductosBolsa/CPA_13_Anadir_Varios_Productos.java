package belcorp.test.AñadirProductosBolsa;

import org.testng.annotations.Test;
import belcorp.pages.BolsaCompraPage;
import belcorp.pages.LoginPage;
import belcorp.pages.SearchPage;
import belcorp.test.RegistroLogin.CPA_01_Login_usuario_existente;
import belcorp.utils.BrowserFactory;
import belcorp.utils.LogResult;
import belcorp.utils.TakeScreenShot;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class CPA_13_Anadir_Varios_Productos {
	
    @Test
    public void Anadir_Varios_Productos() throws IOException, InterruptedException {
          String sku = null;
          List<List<String>> excelDatos = belcorp.utils.Excel.leerArchivoXLSX("Datos.xlsx", 0);
          
       // Mostrar en Consola Datos de Ejecucion de Prueba
  		WebDriver driver = BrowserFactory.startBrowser("firefox",
  				"https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
  		LoginPage login_page = PageFactory.initElements(driver, LoginPage.class);
  		//Inicializacion de Reporte
  		String nombreClase = getClass().getSimpleName();
  		LogResult logResult = new LogResult();
  		logResult.InicioScript(driver);
  		
  		try {

  			
            //lo siguiente está creado para que reciba X cantidad de SKU, además si la búsqueda no entrega nada
            //irá a buscar el siguiente SKU al datapool.*******
            
            for (int i = 1; i < excelDatos.size(); i++) {
            	
                   List<?> list = (List<?>) excelDatos.get(i);
                   sku = list.get(0).toString();
                   System.out.println("SKU "+sku);
                   BolsaCompraPage AgregarABolsa = PageFactory.initElements(driver, BolsaCompraPage.class);
                   AgregarABolsa.AgregarArticuloPorSKUB(sku);
                   Thread.sleep(1000);
                 
                   
                 ///Validación 1 : verificar que contador de productos cambie por consola
                   System.out.println("SKU en la posición "+i+" es:"+sku);
                   logResult.passLog("Validacion 1","Verificar Contador de productos_OK "+i,driver,nombreClase);
                   Thread.sleep(1000);
                   driver.navigate().back();
                   
            	}
            
            ///validación 2 : Verificar la lista desplegable con productos
  			Actions act = new Actions(driver);
  			act.moveToElement(driver.findElement(By.id("cantItemsCart"))).perform();
  		  //captura de Evidencia jpg		 
  			//  TakeScreenShot.takeScreenShot(driver, "CPA_013_val2_evidencia_OK_");
  			logResult.passLog("Validacion2","lista desplegable con productos_OK",driver,nombreClase);
            
            
            
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