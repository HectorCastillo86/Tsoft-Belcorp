package belcorp.test.RegistroLogin;

import org.testng.Assert;
import org.testng.annotations.Test;
import belcorp.utils.TakeScreenShot;
import belcorp.pages.LoginPage;
import belcorp.pages.RegistroPage;
import belcorp.utils.BrowserFactory;
import belcorp.utils.LogResult;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class CPA_02_Registro_nuevo_usuario_belcorp {

	// DATOS CPA_02
	String nombre = "TESTK";
	String apellido = "TestK";
	String email = "Test_00K@belcorp.biz";
	String pwd = "Tsoft1234";
	String tipoDoc = "Cédula de extranjería"; // valores "Cédula de identidad" ; "Cédula de extranjería"; "NIT" ;
	String checkNumDoc = "154450016";

	@Test
	public void Registro_nuevo_usuario_belcorp() throws IOException {

		// Inicializar Driver y Page
		WebDriver driver = BrowserFactory.startBrowser("firefox",
				"https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
		RegistroPage registro = PageFactory.initElements(driver, RegistroPage.class);

		// Inicializacion de Reporte
		String nombreClase = getClass().getSimpleName();
		LogResult logResult = new LogResult();
		logResult.InicioScript(driver);

		// Validacion 1: validar mail de belcorp como entrada inicial
		int intIndex = email.indexOf("@belcorp.biz");
		if (intIndex != -1) {
			System.out.println("Mail perteneciente a Belcorp");
			logResult.passLog("Validacion1", "mail belcorp: " + email, driver, nombreClase);
		} else {
			System.out.println("Mail no perteneciente a Belcorp");
			logResult.errorLog("Validacion1", "Mail no perteneciente a Belcorp: " + email, driver, nombreClase);
		}

		// Mostrar en Consola Datos de Ejecucion de Prueba
		System.out.println("CPA_02: Datos para Ejecucion Nombre: " + nombre + " , apellido: " + apellido + " , email: "
				+ email + " , Password: " + pwd + " , Tipo Documento: " + tipoDoc + " , Id Documento: " + checkNumDoc);

		try {
			// FUNCION CPA
			registro.RegistroBelcorp(nombre, apellido, email, pwd, tipoDoc, checkNumDoc);
			// Esperar 2 Seg
			Thread.sleep(2000);

			// *******************VALIDACION 2: verificar Registro de usuario
			// logeado****************

			// Validar si existe AccountName Nombre de usuario Registrado
			boolean val2 = !(driver.findElements(By.xpath("//span[@class='accountName']")).size() == 0); // Existe
			Actions act1 = new Actions(driver);																								// elemento?
			if (val2) {
				
				act1.moveToElement(driver.findElement(By.xpath(".//*[@class='accountLi']"))).perform();
				// Esperar 2 Seg
				Thread.sleep(2000);
				String username = driver.findElement(By.xpath("//span[@class='accountName']")).getText();
				System.out.println("CPA_02: Usuario Registrado en Sistema para Ejecucion: " + username);

				logResult.passLog("Validacion2", "Registro Exitoso: "+nombre+", "+pwd, driver, nombreClase);

			} else {

				System.out.println("CPA_02: Problema de Registro con los siguientes datos: " + email + ".");
				logResult.errorLog("Validacion2", "Registro No Exitoso: "+nombre+", "+pwd, driver, nombreClase);
				registro.close();
				Assert.assertTrue(val2, "CPA_02: Problema de Registro con los siguientes datos: " + email + ".");
			}
			
			
			// *******************VALIDACION 3: LogOut usuario creado****************
			//Cerrar Sesion Inicial
			act1.moveToElement(driver.findElement(By.xpath(".//*[@class='accountLi']"))).perform();
			driver.findElement(By.xpath(".//*[@id='logout']")).click();
			Thread.sleep(2000);
			act1.moveToElement(driver.findElement(By.xpath(".//*[@id='navbar']/ul[2]/li[3]/div/img"))).perform();
			
			
					
			boolean val3 = (driver.findElements(By.xpath("//span[@class='accountName']")).size() == 0);
			if (val3) {
				logResult.passLog("Validacion3", "Logout al Sistema OK", driver, nombreClase);			
			}else {
				logResult.errorLog("Validacion3", "Logout al Sistema NOK", driver, nombreClase);
				logResult.crearLog(nombreClase);
				registro.close();
				Assert.assertFalse(val3, "CPA_02: Problema de Registro con los siguientes datos: " + email + ".");
				
			}
			
			// *******************VALIDACION 4: re loguear usuario creado****************
			//Re-Login al Sistema con usuario creado 
			
			LoginPage login_page = PageFactory.initElements(driver, LoginPage.class);
			login_page.loginBelcorp(email, pwd);
			driver.navigate().refresh();
			// Esperar 2 Seg
			Thread.sleep(2000);

			// Validar si existe AccountName Nombre de usuario Registrado
			boolean val4 = !(driver.findElements(By.xpath("//span[@class='accountName']")).size() == 0); // Existe
																											// elemento?
			if (val4) {
				Actions act2 = new Actions(driver);
				act2.moveToElement(driver.findElement(By.xpath(".//*[@class='accountLi']"))).perform();
				// Esperar 2 Seg
				Thread.sleep(2000);
				String username = driver.findElement(By.xpath("//span[@class='accountName']")).getText();
				System.out.println("CPA_02: Usuario logeado en Sistema para Ejecucion: " + username);
				logResult.passLog("Validacion4", "Re-Login Exitoso: "+nombre+", "+pwd, driver, nombreClase);
				logResult.crearLog(nombreClase);

			} else {

				System.out.println("CPA_02: Problema de login con los siguientes datos: " + email + ".");
				logResult.errorLog("Validacion4", "Re-Login No Exitoso: "+nombre+", "+pwd, driver, nombreClase);
				login_page.close();
				logResult.crearLog(nombreClase);
				Assert.assertTrue(val4, "CPA_02: Problema de Re-login con los siguientes datos: " + email + ".");
			}

			// Cerrar Test
			login_page.close();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logResult.errorLog("Error Inesperado", "Error exception: "+e, driver, nombreClase);
			registro.close();
			logResult.crearLog(nombreClase);
		}

	}

}
