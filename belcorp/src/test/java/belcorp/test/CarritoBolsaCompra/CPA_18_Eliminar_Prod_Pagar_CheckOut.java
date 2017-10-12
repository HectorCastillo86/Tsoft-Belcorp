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

public class CPA_18_Eliminar_Prod_Pagar_CheckOut {
	
	  //Ingreso de Datos CPA_01
	  public static final String user = "tsoft@yopmail.com";
	  public static final String pass = "Hola123";

	@Test
	public void CPA_18() throws InterruptedException
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
			
			//Funci�n para agregar un art�culo. 
			AgregarABolsa.agregarUnArticulo();
			
			////************** VALIDACION 2 :  Verificar Carga de p�gina de bolsa de compras*****************
			
			String val2 = driver.getCurrentUrl();
			
			//System.out.println("CPA_16: Se valida Url de Bolsa de Compra URL: "+val2);
		    //Esperar 2 Seg
			Thread.sleep(2000);
			// Validacion Visual
			//TakeScreenShot.takeScreenShot(driver, "CPA_16_val2_evidencia_OK_");
			logResult.passLog("Validacion 2","Carga de p�gina de bolsa de compras_OK",driver,nombreClase);
			
			//Ingresar cup�n v�lido
			String cupon = "VPR-T38H-MAC8-C3F4-S";
			AgregarABolsa.CuponValido(cupon);
			
			
			   ////************** VALIDACION 3 :  Verificar que producto a eliminar se encuentra en la bolsa de compras*****************
				//No aplica esta validaci�n, ya que anteriormente agregamos un producto el cual
				//aparece en la bolsa de compras para poder ser eliminado.
				
				//Eliminamos el producto
				AgregarABolsa.EliminaProdContComprando();
				
				   ///************** VALIDACION 4 : Verificar mensaje "Se elimin� el producto de tu bolsa"
				String texto = driver.findElement(By.xpath("html/body/main/div[2]/div[1]/div")).getText();
				Thread.sleep(2000);
				// Validacion Visual
				//TakeScreenShot.takeScreenShot(driver, "CPA_17_val4_evidencia_OK_");
				logResult.passLog("Validacion 4","Verificar mensaje: "+texto+"_OK",driver,nombreClase);
				Thread.sleep(1000);
				// Validaci�n del texto
				//System.out.println("CPA_17: Se valida texto descriptivo de eliminaci�n de producto: " + texto);
				
				///************* VALiDACION 5 : Verificar que no exista el producto eliminado
				//Recargar p�gina y verificar que no exista el producto eliminado anteriormente
				driver.navigate().refresh();
			    //Esperar 2 Seg
				Thread.sleep(2000);
				// Validacion Visual
				//TakeScreenShot.takeScreenShot(driver, "CPA_17_val5_evidencia_OK_");
				logResult.passLog("Validacion 5","Verificar: No existe el producto eliminado_OK",driver,nombreClase);
				

				///************** VALIDACION 6 : Verificar que se encuentra en la p�gina listado de productos
				String val6 = driver.getCurrentUrl();
				//Continuar comprando
				driver.findElement(By.xpath("html/body/main/div[2]/div[6]/div/div/div/div[2]/div[3]/button")).click();
				
				//System.out.println("CPA_17: Se valida Url de p�gina listado de productos URL: "+val6);
			    //Esperar 2 Seg
				Thread.sleep(2000);
				// Validacion Visual
				//TakeScreenShot.takeScreenShot(driver, "CPA_17_val6_evidencia_OK_");
				logResult.passLog("Validacion 6","Carga de p�gina listado de productos_OK",driver,nombreClase);

				//continuar a listado de CheckOut
				Thread.sleep(1000);
				AgregarABolsa.irABolsaCompra();
				Thread.sleep(500);
				driver.navigate().refresh();
				Thread.sleep(2000);
				if(!driver.findElement(By.className("pageBagSubTitle")).getText().equals("No tienes productos en tu bolsa"))
				{
					Thread.sleep(2000);
					driver.findElement(By.xpath("//button[@class='btn btn-block checkoutButton continueCheckout']")).click();
					Thread.sleep(1000);
					
					///************** VALIDACION 7 : Verificar que se encuentra en la p�gina CheckOut
					String val7 = driver.getCurrentUrl();
					
					//System.out.println("CPA_18: Se valida Url de p�gina CheckOut URL: "+val6);
				    //Esperar 2 Seg
					Thread.sleep(2000);
					// Validacion Visual
					//TakeScreenShot.takeScreenShot(driver, "CPA_18_val7_evidencia_OK_");
					logResult.passLog("Validacion 7","Carga de p�gina CheckOut_OK",driver,nombreClase);
				}
				else 
				{
					//Esperar 2 Seg
					Thread.sleep(2000);
					logResult.errorLog("Validacion 7","Carga de p�gina CheckOut_NOK",driver,nombreClase);
					
				
				}

				
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