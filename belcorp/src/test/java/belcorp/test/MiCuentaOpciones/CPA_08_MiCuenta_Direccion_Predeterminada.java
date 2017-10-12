package belcorp.test.MiCuentaOpciones;

import org.testng.Assert;
import org.testng.annotations.Test;

import belcorp.pages.AgregarDireccionPage;
import belcorp.pages.LoginPage;
import belcorp.pages.MiCuentaMisDireccionesPage;
import belcorp.utils.BrowserFactory;
import belcorp.utils.LogResult;
import belcorp.utils.TakeScreenShot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class CPA_08_MiCuenta_Direccion_Predeterminada {
	
	//DATOS CPA_08
	String user="tsoft@yopmail.com";
	String pass="Hola123";
	String UrlBase = "https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03";
	String direccion = "Las Condes Calle N°1";
	String deptoDetalle = "Block 4-B";
	String infoDireccion = "InfoDireccion Test 0011";
	String numTelefono = "012345678";
	
	
	@Test
	public void MiCuenta_Direccion_Predeterminada() throws InterruptedException
	{
		 
		//Mostrar en Consola Datos de Ejecucion de Prueba
		System.out.println("CPA_08: Datos para Ejecucion Usuario: " +user+" , Password: "+pass);
		
		// Inicilaizar Driver y Page
		WebDriver driver = BrowserFactory.startBrowser("firefox",UrlBase);
		MiCuentaMisDireccionesPage direccionPredeterminada = PageFactory.initElements(driver, MiCuentaMisDireccionesPage.class);
		LoginPage login_page = PageFactory.initElements(driver, LoginPage.class);
		AgregarDireccionPage dir_predet = PageFactory.initElements(driver, AgregarDireccionPage.class);
		
		// Inicializacion de Reporte
		String nombreClase = getClass().getSimpleName();
		LogResult logResult = new LogResult();
		logResult.InicioScript(driver);
		
		//Login Belcorp
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
			System.out.println("CPA_08: Usuario logeado en Sistema para Ejecucion: " + username);
			// TakeScreenShot.takeScreenShot(driver, "CPA_01_val1_evidencia_OK_");
			logResult.passLog("Validacion1", "Login Exitoso: " + user + ", " + pass, driver, nombreClase);

		} else {
			// TakeScreenShot.takeScreenShot(driver, "CPA_01_val1_evidencia_NOK_");
			System.out.println("CPA_08: Problema de login con los siguientes datos: " + user + ".");
			logResult.errorLog("Validacion1", "Login No Exitoso: " + user + ", " + pass, driver, nombreClase);
			login_page.close();
			// logResult.errorLog("CPA_01"+user,"Se detecto correctamente'",driver);
			logResult.crearLog(nombreClase);
			Assert.assertTrue(val1, "CPA_08: Problema de login con los siguientes datos: " + user + ".");
		}
		
		  //Metodo para verificar Direccion Predeterminada
		 // direccionPredeterminada.MiDirecionPredeterminada();
		
		dir_predet.AgregarDireccionPredeterminada(direccion, deptoDetalle, infoDireccion, numTelefono);
		
		
		 //Validacion 2:  verificar con título de la página que nos encontramos en "Mis direcciones"
			boolean val2 = !(driver.findElements(By.xpath("//span[@class='accountName']")).size() == 0); // Existe elemento?
			if (val2) {
				//Obtener Titulo de pagina Mis Direcciones
				String sval2 = driver.getTitle();
				int intIndex = sval2.indexOf("Address Book");
				if (intIndex != -1) {
					System.out.println("Acceso a Mis Direcciones OK");
					logResult.passLog("Validacion2", "Acceso a Mis Direcciones OK. Titulo de Pantalla: "+sval2, driver, nombreClase);
					//driver.navigate().back();												
				} else {
					boolean val = false;
					System.out.println("Acceso a Mis Direcciones NOK");
					logResult.errorLog("Validacion2", "Acceso a Mis Direcciones NOK. Titulo de Pantalla: "+sval2, driver, nombreClase);
					login_page.close();
					logResult.crearLog(nombreClase);
					Assert.assertTrue(val, "CPA_07: Problema de Acceso a Mis Direcciones NOK. Titulo de Pantalla: "+sval2);
				}
			}
		  
		  
		//VALIDACION 3: valida que aparezca en pantalla la direccion predeterminada
			boolean val3 = !(driver.findElements(By.xpath(".//input[@type='checkbox' and @checked='checked']")).size() == 0); // Se pregunta si existe el objeto
			if (val3) 
			{
				  //captura de Evidencia jpg
				  System.out.println("CPA_08: Se valida que aparezca en pantalla la direccion predeterminada al usuario:" +user);		 
				  logResult.passLog("Validacion3", "Seleccionada en Pantalla Direccion Predeterminada", driver, nombreClase);
			}else {
				boolean val = false;
				System.out.println("Acceso a Mis Direcciones NOK");
				logResult.errorLog("Validacion3", "Sin Direccion Predeterminada", driver, nombreClase);
				login_page.close();
				logResult.crearLog(nombreClase);
				Assert.assertTrue(val, "CPA_08: Sin Direccion Predeterminada");
			}
			
			// Validacion 4: verificar que nueva dirección se encuentra en listado de "Mis Direcciones"
			// se valida si la variable infoDireccion se encuentra desplegada en la pagina
			boolean val4 = !(driver.findElements(By.xpath("//strong[contains(text(), '"+infoDireccion+"')]")).size()==0);
			if (val4) {
				System.out.println("Acceso a Mis Direcciones OK");
				logResult.passLog("Validacion4", "Direccion agregada correctamente desplegada en la pagina. Referencia de Direccion: "+infoDireccion, driver, nombreClase);			
			}else {
				boolean val=false;
				System.out.println("Direccion no agregada en la pagina. Referencia de Direccion: "+infoDireccion);
				logResult.errorLog("Validacion4", "Direccion no agregada en la pagina. Referencia no encontrada: "+infoDireccion, driver, nombreClase);
				login_page.close();
				logResult.crearLog(nombreClase);
				Assert.assertTrue(val, "CPA_08: Direccion no agregada en la pagina. Referencia no encontradan: "+infoDireccion);
				
			}
		
			//Cerrar Test
			driver.close();
			logResult.crearLog(nombreClase);
		
	}
	

}
