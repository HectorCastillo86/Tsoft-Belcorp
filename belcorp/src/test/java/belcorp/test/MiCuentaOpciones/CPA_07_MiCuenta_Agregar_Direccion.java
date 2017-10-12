package belcorp.test.MiCuentaOpciones;

import org.testng.Assert;
import org.testng.annotations.Test;
import belcorp.pages.AgregarDireccionPage;
import belcorp.pages.LoginPage;
import belcorp.test.RegistroLogin.CPA_01_Login_usuario_existente;
import belcorp.utils.BrowserFactory;
import belcorp.utils.LogResult;
import belcorp.utils.TakeScreenShot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class CPA_07_MiCuenta_Agregar_Direccion {

	//Datos CPA_07
			String user="tsoft@yopmail.com";
			String pass="Hola123";
			String direccion = "Las Condes Calle N°1";
			String deptoDetalle = "Block 4-B";
			String infoDireccion = "InfoDireccion Test 0001";
			String numTelefono = "012345678";
			String UrlBase = "https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03"; 
	@Test
	public void MiCuenta_Agregar_Direccion() throws InterruptedException
	{
		//Mostrar en Consola Datos de Ejecucion de Prueba
		System.out.println("CPA_07: Datos para Ejecucion Usuario: " +user+" , Password: "+pass+" , direccion: "+direccion+" , deptoDetalle: "+deptoDetalle+" , infoDireccion: "+infoDireccion+" , numTelefono: "+numTelefono);
		
		// Inicilaizar Driver y Page
		WebDriver driver = BrowserFactory.startBrowser("firefox",UrlBase);
		AgregarDireccionPage newDireccion = PageFactory.initElements(driver, AgregarDireccionPage.class);
		LoginPage login_page = PageFactory.initElements(driver, LoginPage.class);
		
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
					System.out.println("CPA_07: Usuario logeado en Sistema para Ejecucion: " + username);
					// TakeScreenShot.takeScreenShot(driver, "CPA_01_val1_evidencia_OK_");
					logResult.passLog("Validacion1", "Login Exitoso: " + user + ", " + pass, driver, nombreClase);

				} else {
					// TakeScreenShot.takeScreenShot(driver, "CPA_01_val1_evidencia_NOK_");
					System.out.println("CPA_07: Problema de login con los siguientes datos: " + user + ".");
					logResult.errorLog("Validacion1", "Login No Exitoso: " + user + ", " + pass, driver, nombreClase);
					login_page.close();
					// logResult.errorLog("CPA_01"+user,"Se detecto correctamente'",driver);
					logResult.crearLog(nombreClase);
					Assert.assertTrue(val1, "CPA_07: Problema de login con los siguientes datos: " + user + ".");
				}
				
		//Validacion 2:  verificar con título de la página que nos encontramos en "Mis direcciones"
				boolean val2 = !(driver.findElements(By.xpath("//span[@class='accountName']")).size() == 0); // Existe elemento?
				if (val2) {
					Actions act1 = new Actions(driver);
					act1.moveToElement(driver.findElement(By.xpath(".//*[@class='accountLi']"))).perform();
					// Esperar 2 Seg
					Thread.sleep(2000);
					driver.findElement(By.xpath(".//*[@id='navbar']/ul[2]/li[3]/div/div/div[3]/a")).click();
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
					
					//Validacion 3: Después de dar click en "Nueva Dirección" verificar que nos redirige a  página "Nueva Dirección".
					boolean val3 = !(driver.findElements(By.xpath("html/body/main/div[2]/div[4]/div/div[2]/div[1]/div[1]/div/a[1]")).size() == 0); // Se pregunta si existe boton agregar nueva direccion
					if (val3) 
					{
						driver.findElement(By.xpath("html/body/main/div[2]/div[4]/div/div[2]/div[1]/div[1]/div/a[1]")).click(); //presionar boton nueva direccion
						//Obtener Titulo de pagina Mis Direcciones
						sval2 = driver.getTitle();
						intIndex = sval2.indexOf("Add/Edit Address");
						if (intIndex != -1) {
							System.out.println("Acceso a Nueva Direccion OK");
							logResult.passLog("Validacion3", "Acceso a Nueva Direccion OK. Titulo de Pantalla: "+sval2, driver, nombreClase);
							driver.navigate().to(UrlBase);												
						} else {
							boolean val=false;
							System.out.println("Acceso a Mis Direcciones NOK");
							logResult.errorLog("Validacion3", "Acceso a Nueva Direccion NOK. Titulo de Pantalla: "+sval2, driver, nombreClase);
							login_page.close();
							logResult.crearLog(nombreClase);
							Assert.assertTrue(val, "CPA_07: Problema de Acceso a Nueva Direccion NOK. Titulo de Pantalla: "+sval2);
						}
					}
					
				} else {
					driver.navigate().to(UrlBase);
				}
				
		//Metodo para Agregar Nueva Direaccion
		newDireccion.AgregarDireccion(direccion, deptoDetalle, infoDireccion, numTelefono);
		
		//Validacion 4:  verificar con título de la página que nos encontramos en "Mis direcciones"
		boolean val4 = !(driver.findElements(By.xpath("//span[@class='accountName']")).size() == 0); // Existe elemento?
		if (val4) {
			Actions act1 = new Actions(driver);
			act1.moveToElement(driver.findElement(By.xpath(".//*[@class='accountLi']"))).perform();
			// Esperar 2 Seg
			Thread.sleep(2000);
			driver.findElement(By.xpath(".//*[@id='navbar']/ul[2]/li[3]/div/div/div[3]/a")).click();
			//Obtener Titulo de pagina Mis Direcciones
			String sval4 = driver.getTitle();
			int intIndex = sval4.indexOf("Address Book");
			if (intIndex != -1) {
				System.out.println("Acceso a Mis Direcciones OK");
				logResult.passLog("Validacion4", "Acceso a Mis Direcciones OK. Titulo de Pantalla: "+sval4, driver, nombreClase);
				//driver.navigate().back();												
			} else {
				boolean val=false;
				System.out.println("Acceso a Mis Direcciones NOK");
				logResult.errorLog("Validacion4", "Acceso a Mis Direcciones NOK. Titulo de Pantalla: "+sval4, driver, nombreClase);
				login_page.close();
				logResult.crearLog(nombreClase);
				Assert.assertTrue(val, "CPA_07: Problema de Acceso a Mis Direcciones NOK. Titulo de Pantalla: "+sval4);
			}
		}
		
		// Validacion 5: verificar que nueva dirección se encuentra en listado de "Mis Direcciones"
		// se valida si la variable infoDireccion se encuentra desplegada en la pagina
		boolean val5 = !(driver.findElements(By.xpath("//strong[contains(text(), '"+infoDireccion+"')]")).size()==0);
		if (val5) {
			System.out.println("Acceso a Mis Direcciones OK");
			logResult.passLog("Validacion5", "Direccion agregada correctamente desplegada en la pagina. Referencia de Direccion: "+infoDireccion, driver, nombreClase);			
		}else {
			boolean val=false;
			System.out.println("Direccion no agregada en la pagina. Referencia de Direccion: "+infoDireccion);
			logResult.errorLog("Validacion5", "Direccion no agregada en la pagina. Referencia no encontrada: "+infoDireccion, driver, nombreClase);
			login_page.close();
			logResult.crearLog(nombreClase);
			Assert.assertTrue(val, "CPA_07: Direccion no agregada en la pagina. Referencia no encontradan: "+infoDireccion);
			
		}
			
		driver.close();
		logResult.crearLog(nombreClase);
	
	}	

}

