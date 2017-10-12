package belcorp.test.LoginBusquedaProducto;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class CPA_10_Busqueda_Cat_SubCategoria {
	
	// Data para Ejecucion
	//Ingreso de Datos CPA_10
		  String user = "tsoft@yopmail.com";
		  String pass = "Hola123";
		  String categoria_unica = "cuidado"; // Categorias : "maquillaje", "perfume","piel","cuidado"
		  String urlBase = "https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03";
		  
	@Test
	public void Busqueda_Cat_SubCategoria()
	{
		//Mostrar en Consola Datos de Ejecucion de Prueba
		System.out.println("CPA_10: Datos para Ejecucion Usuario: " +user+" , Password: "+pass+" , Categoria: "+categoria_unica);

		try {
		
		// Inicilaizar Driver y Page
		WebDriver driver = BrowserFactory.startBrowser("firefox",urlBase);
		SearchPage BusquedaProd = PageFactory.initElements(driver, SearchPage.class);
		LoginPage login_page = PageFactory.initElements(driver, LoginPage.class);
		
		// Inicializacion de Reporte
		String nombreClase = getClass().getSimpleName();
		LogResult logResult = new LogResult();
		logResult.InicioScript(driver);
		
		// Login
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
					System.out.println("CPA_10: Usuario logeado en Sistema para Ejecucion: " + username);
					// TakeScreenShot.takeScreenShot(driver, "CPA_01_val1_evidencia_OK_");
					logResult.passLog("Validacion1", "Login Exitoso: " + user + ", " + pass, driver, nombreClase);

				} else {
					// TakeScreenShot.takeScreenShot(driver, "CPA_01_val1_evidencia_NOK_");
					System.out.println("CPA_10: Problema de login con los siguientes datos: " + user + ".");
					logResult.errorLog("Validacion1", "Login No Exitoso: " + user + ", " + pass, driver, nombreClase);
					login_page.close();
					// logResult.errorLog("CPA_01"+user,"Se detecto correctamente'",driver);
					logResult.crearLog(nombreClase);
					Assert.assertTrue(val1, "CPA_10: Problema de login con los siguientes datos: " + user + ".");
				}
		
		
		String[ ] Categoria = {"maquillaje", "perfume","piel","cuidado","oferta"};
		//String categoria_unica = Categoria[ new Random().nextInt(4)];
		System.out.println("CPA_10: Categoria utilizada en ejecucion: " + categoria_unica);
		String Subcategoria;
		categoria_unica = categoria_unica.toLowerCase();
		Subcategoria = BusquedaProd.BuscarSubCategoriaStr(categoria_unica);
		
		//Formateo de ñ, acentos y _ por espacios al valor de la subcategoria
		Subcategoria = Subcategoria.replace("ñ", "n");
		Subcategoria = Subcategoria.replace("_", " ");
		Subcategoria = Subcategoria.replace("á", "a");
		Subcategoria = Subcategoria.replace("é", "e");
		Subcategoria = Subcategoria.replace("í", "i");
		Subcategoria = Subcategoria.replace("ó", "o");
		Subcategoria = Subcategoria.replace("ú", "u");
		
		//validar solo primera cadena
		String[] parts = Subcategoria.split(" ");
		
		////************** VALIDACION 2 :  Verificar Carga de categoria Seleccionada*****************
		
		String val2 = driver.getCurrentUrl();
		int intIndex = val2.indexOf(parts[0].toLowerCase());
		if (intIndex != -1) {
			System.out.println("CPA_10: Se valida Url de Subcategoria: " + Subcategoria + ". URL: "+val2);
			logResult.passLog("Validacion2", "Se valida Url de Subcategoria: " + Subcategoria + ". URL: "+val2, driver, nombreClase);
			//driver.navigate().back();												
		} else {
			boolean val = false;
			System.out.println("CPA_10: URL de Subcategoria equivocada: " + Subcategoria + ". URL: "+val2);
			logResult.errorLog("Validacion2", "URL de Subcategoria equivocada: " + Subcategoria + ". URL: "+val2, driver, nombreClase);
			login_page.close();
			logResult.crearLog(nombreClase);
			Assert.assertTrue(val, "CPA_10: URL de Subcategoria equivocada: " + Subcategoria + ". URL: "+val2);
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
