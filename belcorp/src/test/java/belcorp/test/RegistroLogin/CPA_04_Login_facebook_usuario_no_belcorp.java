package belcorp.test.RegistroLogin;

import org.testng.Assert;
import org.testng.annotations.Test;
import belcorp.utils.TakeScreenShot;
import belcorp.pages.LoginPage;
import belcorp.utils.BrowserFactory;
import belcorp.utils.LogResult;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class CPA_04_Login_facebook_usuario_no_belcorp {

	// DATOS CPA
	String user = "john_fdtbmaw_dos@tfbnw.net";
	String pass = "Belcorp2017";

	@Test
	public void Login_usuario_facebook() throws IOException {
		// Inicializar CARGA URL Y PAGE
		WebDriver driver = BrowserFactory.startBrowser("firefox",
				"https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
		LoginPage login_page = PageFactory.initElements(driver, LoginPage.class);

		// Inicializacion de Reporte
		String nombreClase = getClass().getSimpleName();
		LogResult logResult = new LogResult();
		logResult.InicioScript(driver);

		// Mostrar en Consola Datos de Ejecucion de Prueba
		System.out.println("CPA_04: Datos para Ejecucion User: " + user + " , Password: " + pass);

		try {
			// FUNCION CPA
			logResult = login_page.loginFacebook(user, pass, nombreClase);
			// Esperar 2 Seg
			Thread.sleep(2000);

			// *******************VALIDACION 2: verificar Registro de usuario
			// logeado****************

			// Validar si existe AccountName Nombre de usuario Registrado
			boolean val2 = !(driver.findElements(By.xpath("//span[@class='accountName']")).size() == 0); // Existe
			Actions act1 = new Actions(driver); // elemento?
			if (val2) {

				act1.moveToElement(driver.findElement(By.xpath(".//*[@class='accountLi']"))).perform();
				// Esperar 2 Seg
				Thread.sleep(2000);
				String username = driver.findElement(By.xpath("//span[@class='accountName']")).getText();
				System.out.println("CPA_04: Ingreso exitoso con la cuenta de facebook: " + user + " " + username);
				logResult.passLog("Validacion2", "Ingreso exitoso con la cuenta de facebook: " + user, driver,
						nombreClase);

			} else {

				System.out.println("CPA_04: Ingreso erroneo con la cuenta de facebook: " + user);
				logResult.errorLog("Validacion2", "Ingreso erroneo con la cuenta de facebook: " + user, driver,
						nombreClase);
				login_page.close();
				logResult.crearLog(nombreClase);
				Assert.assertTrue(val2, "Ingreso erroneo con la cuenta de facebook: " + user);
			}

			// CERRAR DRIVER
			login_page.close();
			logResult.crearLog(nombreClase);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logResult.errorLog("Error Inesperado", "Error exception: " + e, driver, nombreClase);
			login_page.close();
			logResult.crearLog(nombreClase);
		}
	}
}
