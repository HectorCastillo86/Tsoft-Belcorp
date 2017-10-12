package belcorp.test.MiCuentaOpciones;

import org.testng.Assert;
import org.testng.annotations.Test;
import belcorp.pages.LoginPage;
import belcorp.pages.MiCuentaMisPedidosPage;
import belcorp.utils.BrowserFactory;
import belcorp.utils.LogResult;
import belcorp.utils.TakeScreenShot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class CPA_05_MiCuenta_Mis_Pedidos_Realizados {

	// Ingreso de Datos CPA_05
	public static final String user = "tsoft@yopmail.com";
	public static final String pass = "Hola123";

	@Test
	public void MiCuenta_Mis_Pedidos_Realizados() throws InterruptedException {

		// Inicializar CARGA URL Y PAGE
		WebDriver driver = BrowserFactory.startBrowser("firefox","https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
		MiCuentaMisPedidosPage misPedidos = PageFactory.initElements(driver, MiCuentaMisPedidosPage.class);
		LoginPage login_page = PageFactory.initElements(driver, LoginPage.class);

		// Inicializacion de Reporte
		String nombreClase = getClass().getSimpleName();
		LogResult logResult = new LogResult();
		logResult.InicioScript(driver);

		// Mostrar en Consola Datos de Ejecucion de Prueba
		System.out.println("CPA_05: Datos para Ejecucion User: " + user + " , Password: " + pass);

		// Login Belcorp
		login_page.loginBelcorp(user, pass);

		// *******************VALIDACION 1: verificar nick de usuario
		// logeado****************

		// Validar si existe AccountName Nombre de usuario Registrado
		boolean val1 = !(driver.findElements(By.xpath("//span[@class='accountName']")).size() == 0); // Existe
																										// elemento?
		if (val1) {
			Actions act1 = new Actions(driver);
			act1.moveToElement(driver.findElement(By.xpath(".//*[@class='accountLi']"))).perform();
			// Esperar 2 Seg
			Thread.sleep(2000);
			String username = driver.findElement(By.xpath("//span[@class='accountName']")).getText();
			System.out.println("CPA_05: Usuario logeado en Sistema para Ejecucion: " + username);
			logResult.passLog("Validacion1", "Login Exitoso: " + user + ", " + pass, driver, nombreClase);

		} else {
			// TakeScreenShot.takeScreenShot(driver, "CPA_01_val1_evidencia_NOK_");
			System.out.println("CPA_05: Problema de login con los siguientes datos: " + user + ".");
			logResult.errorLog("Validacion1", "Login No Exitoso: " + user + ", " + pass, driver, nombreClase);
			login_page.close();
			// logResult.errorLog("CPA_01"+user,"Se detecto correctamente'",driver);
			logResult.crearLog(nombreClase);
			Assert.assertTrue(val1, "CPA_05: Problema de login con los siguientes datos: " + user + ".");
		}

		// Ingreso a Mis Pedidos
		misPedidos.MisPedidos();

		// Validacion 2: validar titulo de ventana "Mis pedidos"
		String val2;
		val2 = driver.getTitle();
		int intIndex = val2.indexOf("Order History");
		if (intIndex != -1) {
			System.out.println("Acceso a Mis Pedidos OK Titulo de Pantalla: "+val2);
			// Esperar 1.5 Seg
			Thread.sleep(1500);
			logResult.passLog("Validacion2", "Acceso a Mis Pedidos OK Titulo de Pantalla: "+val2, driver, nombreClase);
			
		} else {
			boolean val =false;
			System.out.println("Acceso a Mis Pedidos NOK: "+val2);
			// Esperar 1.5 Seg
			Thread.sleep(1500);
			logResult.errorLog("Validacion2", "Problemas de acceso a Mis Pedidos URL: "+val2, driver,nombreClase);
			login_page.close();
			logResult.crearLog(nombreClase);
			Assert.assertTrue(val, "CPA_05: Problemas de acceso a Mis Pedidos URL: "+val2);

		}

		driver.close();
		logResult.crearLog(nombreClase);

	}
}
