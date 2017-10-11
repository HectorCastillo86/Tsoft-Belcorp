package belcorp.test.MiCuentaOpciones;

import org.testng.Assert;
import org.testng.annotations.Test;
import belcorp.pages.MiCuentaCambiarContraseñaPage;
import belcorp.pages.MiCuentaMisPedidosPage;
import belcorp.test.RegistroLogin.CPA_01_Login_usuario_existente;
import belcorp.utils.BrowserFactory;
import belcorp.utils.LogResult;
import belcorp.utils.TakeScreenShot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import belcorp.pages.LoginPage;

public class CPA_06_MiCuenta_Cambiar_Contrasena {

	// Datos CPA_06
	String user = "tsoft@yopmail.com";
	String pass = "Hola123";
	public static String nuevaContraseña = "Hola123";
	String repetirConraseña = "Hola123";

	@Test
	public void MiCuenta_Cambiar_Contrasena() throws InterruptedException {

		// Inicilaizar Variables
		WebDriver driver = BrowserFactory.startBrowser("firefox",
				"https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
		MiCuentaCambiarContraseñaPage cambiarContraseña = PageFactory.initElements(driver,
				MiCuentaCambiarContraseñaPage.class);
		LoginPage login_page = PageFactory.initElements(driver, LoginPage.class);
		
		
		// Inicializacion de Reporte
				String nombreClase = getClass().getSimpleName();
				LogResult logResult = new LogResult();
				logResult.InicioScript(driver);
				
		

		// Mostrar en Consola Datos de Ejecucion de Prueba
		System.out.println("CPA_06: Datos para Ejecucion Usuario: " + user + " , Password Anterior: " + pass
				+ " , Password Nueva: " + nuevaContraseña);
		
		

		// Login Belcorp
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
			System.out.println("CPA_06: Usuario logeado en Sistema para Ejecucion: " + username);
			// TakeScreenShot.takeScreenShot(driver, "CPA_01_val1_evidencia_OK_");
			logResult.passLog("Validacion1", "Login Exitoso: " + user + ", " + pass, driver, nombreClase);

		} else {
			// TakeScreenShot.takeScreenShot(driver, "CPA_01_val1_evidencia_NOK_");
			System.out.println("CPA_06: Problema de login con los siguientes datos: " + user + ".");
			logResult.errorLog("Validacion1", "Login No Exitoso: " + user + ", " + pass, driver, nombreClase);
			login_page.close();
			// logResult.errorLog("CPA_01"+user,"Se detecto correctamente'",driver);
			logResult.crearLog(nombreClase);
			Assert.assertTrue(val1, "CPA_06: Problema de login con los siguientes datos: " + user + ".");
		}

		// Cambio de Contraseña
		cambiarContraseña.cambiarContraseña(pass, nuevaContraseña, repetirConraseña);

		// Validacion 2: validar titulo de ventana "Mis pedidos"
				String val2;
				val2 = driver.getTitle();
				int intIndex = val2.indexOf("Update Forgotten Password");
				if (intIndex != -1) {
					System.out.println("Acceso a Cambio de Contraseña OK. Titulo de Pantalla: "+val2);
					// Esperar 1.5 Seg
					Thread.sleep(1500);
					logResult.passLog("Validacion2", "Acceso a Cambio de Contraseña OK. Titulo de Pantalla: "+val2, driver, nombreClase);
					logResult.passLog("Validacion3", "Se cambia Contraseña de: "+pass+" a la Contraseña: "+nuevaContraseña, driver, nombreClase);
					
				} else {
					System.out.println("Acceso a Cambio de Contraseña NOK. Titulo Pantalla: "+val2);
					// Esperar 1.5 Seg
					Thread.sleep(1500);
					logResult.errorLog("Validacion2", "Problemas de acceso a Cambio de Contraseña. Titulo Pantalla: "+val2, driver,nombreClase);

				}

				driver.close();
				logResult.crearLog(nombreClase);

	}

}
