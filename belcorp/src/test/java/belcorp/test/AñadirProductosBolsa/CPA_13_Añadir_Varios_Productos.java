package belcorp.test.AñadirProductosBolsa;

import org.testng.annotations.Test;
import belcorp.pages.BolsaCompraPage;
import belcorp.pages.LoginPage;
import belcorp.pages.SearchPage;
import belcorp.test.RegistroLogin.CPA_01_Login_usuario_existente;
import belcorp.utils.BrowserFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CPA_13_Añadir_Varios_Productos {
	
    @Test
    public void CPA_02() throws IOException, InterruptedException {
          String sku = null;
          WebDriver driver = BrowserFactory.startBrowser("firefox","https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
          List<List<String>> excelDatos = belcorp.utils.Excel.leerArchivoXLSX("Datos.xlsx", 0);
          
          for (int i = 1; i < excelDatos.size(); i++) {
                 List<?> list = (List<?>) excelDatos.get(i);
                 sku = list.get(0).toString();
                 System.out.println("SKU "+sku);
                 BolsaCompraPage AgregarABolsa = PageFactory.initElements(driver, BolsaCompraPage.class);
                 AgregarABolsa.AgregarArticuloPorSKU(sku);
          }
          
  		// CERRAR DRIVER 
  		driver.close();

    }


}