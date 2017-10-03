package belcorp.test.AñadirProductosBolsa;

import org.testng.annotations.Test;
import belcorp.pages.BolsaCompraPage;
import belcorp.utils.BrowserFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CPA_13_Añadir_Varios_Productos {
	
	@Test
	public void CPA_02()
	{
		WebDriver driver = BrowserFactory.startBrowser("firefox","https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
		BolsaCompraPage AgregarABolsa = PageFactory.initElements(driver, BolsaCompraPage.class);

		AgregarABolsa.agregarVariosArticulos();
		
	
	}

}