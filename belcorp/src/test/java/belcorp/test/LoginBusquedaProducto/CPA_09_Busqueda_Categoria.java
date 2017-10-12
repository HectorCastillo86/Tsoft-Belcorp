package belcorp.test.LoginBusquedaProducto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import belcorp.pages.LoginPage;
import belcorp.pages.SearchPage;
import belcorp.test.RegistroLogin.CPA_01_Login_usuario_existente;
import belcorp.utils.BrowserFactory;
import belcorp.utils.LogResult;
import belcorp.utils.TakeScreenShot;

import java.util.Random;

public class CPA_09_Busqueda_Categoria {
	
	// Datos CPA_09
	  String user = "tsoft@yopmail.com";
	  String pass = "Hola123";
	  String categoria_unica = "maquillaje"; // Categorias : "maquillaje", "perfume","piel","cuidado"
	  String urlBase = "https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03";
	
	@Test
	public void Busqueda_Categoria()
	{
		//Mostrar en Consola Datos de Ejecucion de Prueba
		System.out.println("CPA_09: Datos para Ejecucion Usuario: " +user+" , Password: "+pass+" , Categoria: "+categoria_unica);

		try {
		// Inicilaizar Driver y Page
		WebDriver driver = BrowserFactory.startBrowser("firefox",urlBase);
		SearchPage BusquedaProd = PageFactory.initElements(driver, SearchPage.class);
		LoginPage login_page = PageFactory.initElements(driver, LoginPage.class);
		
		// Inicializacion de Reporte
		String nombreClase = getClass().getSimpleName();
		LogResult logResult = new LogResult();
		logResult.InicioScript(driver);
		
		
		//Metodo Login
		login_page.loginBelcorp(user, pass);
		
		
		// *******************VALIDACION 1: verificar nick de usuario logeado****************
				// Validar si existe AccountName Nombre de usuario Registrado
				
				boolean val1 = !(driver.findElements(By.xpath("//span[@class='accountName']")).size() == 0); // Existe elemento?
				if (val1) {
					Actions act1 = new Actions(driver);
					act1.moveToElement(driver.findElement(By.xpath(".//*[@class='accountLi']"))).perform();
					// Esperar 2 Seg
					Thread.sleep(2000);
					String username = driver.findElement(By.xpath("//span[@class='accountName']")).getText();
					System.out.println("CPA_09: Usuario logeado en Sistema para Ejecucion: " + username);
					// TakeScreenShot.takeScreenShot(driver, "CPA_01_val1_evidencia_OK_");
					logResult.passLog("Validacion1", "Login Exitoso: " + user + ", " + pass, driver, nombreClase);

				} else {
					// TakeScreenShot.takeScreenShot(driver, "CPA_01_val1_evidencia_NOK_");
					System.out.println("CPA_09: Problema de login con los siguientes datos: " + user + ".");
					logResult.errorLog("Validacion1", "Login No Exitoso: " + user + ", " + pass, driver, nombreClase);
					login_page.close();
					// logResult.errorLog("CPA_01"+user,"Se detecto correctamente'",driver);
					logResult.crearLog(nombreClase);
					Assert.assertTrue(val1, "CPA_09: Problema de login con los siguientes datos: " + user + ".");
				}
				
		
		String[ ] Categoria = {"maquillaje", "perfume","piel","cuidado","oferta"};
		//String Cat_Aleatoria = Categoria[ new Random().nextInt(4)];
		System.out.println("CPA_09: Categoria utilizada en ejecucion: " + categoria_unica);
		BusquedaProd.BuscarCategoria(categoria_unica);
		
		//************** VALIDACION 2 :  Verificar Carga de categoria Seleccionada*****************
		
		String val2 = driver.getCurrentUrl();
		int intIndex = val2.indexOf(categoria_unica);
		if (intIndex != -1) {
			System.out.println("CPA_09: Se valida Url de categoria: " + categoria_unica + ". URL: "+val2);
			logResult.passLog("Validacion2", "Se valida Url de categoria: " + categoria_unica + ". URL: "+val2, driver, nombreClase);
			//driver.navigate().back();												
		} else {
			boolean val = false;
			System.out.println("CPA_09: URL de categoria equivocada: " + categoria_unica + ". URL: "+val2);
			logResult.errorLog("Validacion2", "URL de categoria equivocada: " + categoria_unica + ". URL: "+val2, driver, nombreClase);
			login_page.close();
			logResult.crearLog(nombreClase);
			Assert.assertTrue(val, "CPA_09: URL de categoria equivocada: " + categoria_unica + ". URL: "+val2);
		}		
		
		
		driver.close();
		logResult.crearLog(nombreClase);
		
		} 
		catch (InterruptedException e)
		{
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}

}
