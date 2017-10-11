package belcorp.test.AñadirProductosBolsa;

import org.testng.annotations.Test;
import belcorp.pages.BolsaCompraPage;
import belcorp.pages.LoginPage;
import belcorp.pages.SearchPage;
import belcorp.test.RegistroLogin.CPA_01_Login_usuario_existente;
import belcorp.utils.BrowserFactory;
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

public class CPA_13_Añadir_Varios_Productos {
	
    @Test
    public void Añadir_Varios_Productos() throws IOException, InterruptedException {
          String sku = null;
          WebDriver driver = BrowserFactory.startBrowser("firefox","https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
          List<List<String>> excelDatos = belcorp.utils.Excel.leerArchivoXLSX("Datos.xlsx", 0);
          
          //lo siguiente está creado para que reciba X cantidad de SKU, además si la búsqueda no entrega nada
          //irá a buscar el siguiente SKU al datapool.*******
          
          for (int i = 1; i < excelDatos.size(); i++) {
                 List<?> list = (List<?>) excelDatos.get(i);
                 sku = list.get(0).toString();
                 System.out.println("SKU "+sku);
                 BolsaCompraPage AgregarABolsa = PageFactory.initElements(driver, BolsaCompraPage.class);
                 AgregarABolsa.AgregarArticuloPorSKUB(sku);
               
                 
               ///Validación 1 : verificar que contador de productos cambie por consola
                 System.out.println("SKU en la posición "+i+" es:"+sku);
                 
                 
          	}
          
          ///validación 2 : Verificar la lista desplegable con productos
			Actions act = new Actions(driver);
			act.moveToElement(driver.findElement(By.id("cantItemsCart"))).perform();
		  //captura de Evidencia jpg		 
			  TakeScreenShot.takeScreenShot(driver, "CPA_013_val2_evidencia_OK_");
			
          
          
          
        // CERRAR DRIVER 
  		driver.close();

    }


}