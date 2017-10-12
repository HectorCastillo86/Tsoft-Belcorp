package belcorp.test.CarritoBolsaCompra;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import belcorp.pages.LoginPage;
import belcorp.test.RegistroLogin.CPA_01_Login_usuario_existente;

import org.testng.Assert;
import org.testng.annotations.Test;

import belcorp.pages.BolsaCompraPage;
import belcorp.utils.BrowserFactory;
import belcorp.utils.LogResult;
import belcorp.utils.TakeScreenShot;

public class CPA_16_Cupon_Valido_Contiua_Comprando {
	  //Ingreso de Datos CPA_01
	  public static final String user = "tsoft@yopmail.com";
	  public static final String pass = "Hola123";
	  
	@Test
	public void Cupon_Valido_Contiua_Comprando() throws InterruptedException
	{
		// Mostrar en Consola Datos de Ejecucion de Prueba
		System.out.println("CPA_01: Datos para Ejecucion Usuario: " + user + " , Password : " + pass);
		WebDriver driver = BrowserFactory.startBrowser("firefox",
				"https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
		LoginPage login_page = PageFactory.initElements(driver, LoginPage.class);
		
		//Inicializacion de Reporte
		String nombreClase = getClass().getSimpleName();
		LogResult logResult = new LogResult();
		logResult.InicioScript(driver);
		BolsaCompraPage AgregarABolsa = PageFactory.initElements(driver, BolsaCompraPage.class);
		
		try {

			login_page.loginBelcorp(user, pass);

			// *******************VALIDACION 1: verificar nick de usuario logeado****************

			// Validar si existe AccountName Nombre de usuario Registrado
			boolean val1 = !(driver.findElements(By.xpath("//span[@class='accountName']")).size() == 0); // Existe
																										 // elemento?
			if (val1) {
				Actions act1 = new Actions(driver);
				act1.moveToElement(driver.findElement(By.xpath(".//*[@class='accountLi']"))).perform();
				// Esperar 2 Seg
				Thread.sleep(2000);
				String username = driver.findElement(By.xpath("//span[@class='accountName']")).getText();
				System.out.println("CPA_01: Usuario logeado en Sistema para Ejecucion: " + username);
				//TakeScreenShot.takeScreenShot(driver, "CPA_01_val1_evidencia_OK_");

				 logResult.passLog("Validacion1","Login Exitoso: "+user+", "+pass,driver,nombreClase);
				 
				 
			} else {
				//TakeScreenShot.takeScreenShot(driver, "CPA_01_val1_evidencia_NOK_");
				System.out.println("CPA_01: Problema de login con los siguientes datos: " + user + ".");
				logResult.errorLog("Validacion1","Login No Exitoso: "+user+", "+pass,driver,nombreClase);
				login_page.close();
				// logResult.errorLog("CPA_01"+user,"Se detecto correctamente'",driver);
				logResult.crearLog(nombreClase);
				Assert.assertTrue(val1, "CPA_01: Problema de login con los siguientes datos: " + user + ".");
			}
			
			//Función para agregar un artículo. 
			AgregarABolsa.agregarUnArticulo();
			
			////************** VALIDACION 2 :  Verificar Carga de página de bolsa de compras*****************
			
			String val2 = driver.getCurrentUrl();
		
			//System.out.println("CPA_16: Se valida Url de Bolsa de Compra URL: "+val2);
		    //Esperar 2 Seg
			Thread.sleep(2000);
			// Validacion Visual
			//TakeScreenShot.takeScreenShot(driver, "CPA_16_val2_evidencia_OK_");
			logResult.passLog("Validacion 2","Carga de página de bolsa de compras_OK",driver,nombreClase);
			
			
			//Ingresar cupón válido
			String cupon = "VPR-T38H-MAC8-C3F4-S";
			AgregarABolsa.CuponValido(cupon);
			
			////************** VALIDACION 3 :  Verificar texto descriptivo del cupón*****************
			Thread.sleep(2000);
				
			//System.out.println("CPA_15: Se valida texto descriptivo del cupón: " + cupon);
			logResult.passLog("Validacion 3","Se valida texto descriptivo del cupón: " + cupon,driver,nombreClase);
			
			//continuar a listado de productos
			AgregarABolsa.ContinuaComprando();
			Thread.sleep(1000);
			
			///************** VALIDACION 4 : Verificar que se encuentra en la página listado de productos
			String val4 = driver.getCurrentUrl();
			
			//System.out.println("CPA_16: Se valida Url de página listado de productos URL: "+val4);
		    //Esperar 2 Seg
			Thread.sleep(2000);
			// Validacion Visual
			//TakeScreenShot.takeScreenShot(driver, "CPA_16_val4_evidencia_OK_");
			logResult.passLog("Validacion 4","Carga de página listado de productos_OK",driver,nombreClase);
			
			// Cerrar Test
			driver.close();
			logResult.crearLog(nombreClase);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logResult.errorLog("Error Inesperado", "Error exception: "+e, driver, nombreClase);
			driver.close();
			logResult.crearLog(nombreClase);
			
		}
		  
	}


}