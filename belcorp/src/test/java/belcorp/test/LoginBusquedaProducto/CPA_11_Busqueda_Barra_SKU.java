package belcorp.test.LoginBusquedaProducto;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import belcorp.pages.LoginPage;
import belcorp.pages.SearchPage;
import belcorp.utils.BrowserFactory;
import belcorp.utils.LogResult;
import belcorp.utils.TakeScreenShot;

public class CPA_11_Busqueda_Barra_SKU {
	
	// Data para Ejecucion
		//Ingreso de Datos CPA_11
			  String user = "tsoft@yopmail.com";
			  String pass = "Hola123";
			  String busqueda_unica = "cremas";
			  String urlBase = "https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03";
			  
	
	@Test
	public void Busqueda_Barra_SKU() throws InterruptedException
	{
		//Mostrar en Consola Datos de Ejecucion de Prueba
		System.out.println("CPA_11: Datos para Ejecucion Usuario: " +user+" , Password: "+pass+" , Frase Busqueda: "+busqueda_unica);

		// Inicilaizar Driver y Page
		WebDriver driver = BrowserFactory.startBrowser("firefox",urlBase);
		SearchPage BusquedaProd = PageFactory.initElements(driver, SearchPage.class);
		LoginPage login_page = PageFactory.initElements(driver, LoginPage.class);
		
		// Inicializacion de Reporte
		String nombreClase = getClass().getSimpleName();
		LogResult logResult = new LogResult();
		logResult.InicioScript(driver);
		
		login_page.loginBelcorp(user,pass);
		
		// *******************VALIDACION 1: verificar nick de usuario logeado****************
		// Validar si existe AccountName Nombre de usuario Registrado
		
		boolean val1 = !(driver.findElements(By.xpath("//span[@class='accountName']")).size() == 0); // Existe elemento?
		if (val1) {
			Actions act1 = new Actions(driver);
			act1.moveToElement(driver.findElement(By.xpath(".//*[@class='accountLi']"))).perform();
			// Esperar 2 Seg
			Thread.sleep(2000);
			String username = driver.findElement(By.xpath("//span[@class='accountName']")).getText();
			System.out.println("CPA_11: Usuario logeado en Sistema para Ejecucion: " + username);
			// TakeScreenShot.takeScreenShot(driver, "CPA_01_val1_evidencia_OK_");
			logResult.passLog("Validacion1", "Login Exitoso: " + user + ", " + pass, driver, nombreClase);

		} else {
			// TakeScreenShot.takeScreenShot(driver, "CPA_01_val1_evidencia_NOK_");
			System.out.println("CPA_11: Problema de login con los siguientes datos: " + user + ".");
			logResult.errorLog("Validacion1", "Login No Exitoso: " + user + ", " + pass, driver, nombreClase);
			login_page.close();
			// logResult.errorLog("CPA_01"+user,"Se detecto correctamente'",driver);
			logResult.crearLog(nombreClase);
			Assert.assertTrue(val1, "CPA_11: Problema de login con los siguientes datos: " + user + ".");
		}
		
		//String[ ] valorBuscar = {"mascara", "pelo","shampoo","uñas","cara","accesorios"};
		//String SubCat_Aleatoria = valorBuscar[ new Random().nextInt(4)];
		BusquedaProd.BarraBusqueda(busqueda_unica);
		
		
		//************** VALIDACION 2 :  Verificar Carga de categoria Seleccionada*****************
		
				String val2 = driver.getCurrentUrl();
				int intIndex = val2.indexOf(busqueda_unica);
				if (intIndex != -1) {
					System.out.println("CPA_11: Se valida Url de categoria: " + busqueda_unica + ". URL: "+val2);
					logResult.passLog("Validacion2", "Se valida Url de categoria: " + busqueda_unica + ". URL: "+val2, driver, nombreClase);
					//driver.navigate().back();												
				} else {
					boolean val = false;
					System.out.println("CPA_11: URL de categoria equivocada: " + busqueda_unica + ". URL: "+val2);
					logResult.errorLog("Validacion2", "URL de categoria equivocada: " + busqueda_unica + ". URL: "+val2, driver, nombreClase);
					login_page.close();
					logResult.crearLog(nombreClase);
					Assert.assertTrue(val, "CPA_11: URL de categoria equivocada: " + busqueda_unica + ". URL: "+val2);
				}		
			
			driver.close();
			logResult.crearLog(nombreClase);

	}

}
