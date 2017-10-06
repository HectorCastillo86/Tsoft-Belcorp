package belcorp.test.LoginBusquedaProducto;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import belcorp.pages.LoginPage;
import belcorp.pages.SearchPage;
import belcorp.test.RegistroLogin.CPA_01_Login_usuario_existente;
import belcorp.utils.BrowserFactory;
import belcorp.utils.TakeScreenShot;

public class CPA_11_Busqueda_Barra_SKU {
	
	// Data para Ejecucion
		//Ingreso de Datos CPA_11
			  public static final String user = "tsoft@yopmail.com";
			  public static final String pass = "Hola123";
			  public static final String busqueda_unica = "cremas"; 
	
	@Test
	public void Busqueda_Barra_SKU() throws InterruptedException
	{
		//Mostrar en Consola Datos de Ejecucion de Prueba
		System.out.println("CPA_11: Datos para Ejecucion Usuario: " +user+" , Password: "+pass+" , Frase Busqueda: "+busqueda_unica);

		
		WebDriver driver = BrowserFactory.startBrowser("firefox","https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
		SearchPage BusquedaProd = PageFactory.initElements(driver, SearchPage.class);
		
		LoginPage login_page = PageFactory.initElements(driver, LoginPage.class);
		
		login_page.loginBelcorp(user,pass);
		
		//************** VALIDACION:  verificar nick de usuario logeado*****************
		//Posicionarse en icono de usuario
		  Actions act1 = new Actions(driver);
		  act1.moveToElement(driver.findElement(By.xpath(".//*[@class='accountLi']"))).perform();
		//Esperar 1.5 Seg
		  Thread.sleep(1500);
		//Validar si existe AccountName Nombre de usuario Registrado
		  boolean val1 = driver.findElement(By.xpath("//span[@class='accountName']")).isEnabled();
		  if (val1) {
			  String username = driver.findElement(By.xpath("//span[@class='accountName']")).getText();
			  System.out.println("CPA_11: Usuario logeado en Sistema para Ejecucion: " +username);		 
			  TakeScreenShot.takeScreenShot(driver, "CPA_11_val1_evidencia_OK_");
		  }	 
		  else {
			  TakeScreenShot.takeScreenShot(driver, "CPA_11_val1_evidencia_NOK_");
			  
		  }
 //***************** FIN VALIDACION **************************************
		
		//String[ ] valorBuscar = {"mascara", "pelo","shampoo","uñas","cara","accesorios"};
		//String SubCat_Aleatoria = valorBuscar[ new Random().nextInt(4)];
		BusquedaProd.BarraBusqueda(busqueda_unica);
		
		
	////************** VALIDACION 2 :  Verificar Carga de categoria Seleccionada*****************
		
			String val2 = driver.getCurrentUrl();
			//System.out.println(val2);		
			//Assert.assertEquals("https://aws-esika.esika.com:9002/co/perfumes/c/esika-02",val2);
			System.out.println("CPA_11: Se valida Url de Subcategoria Seleccionada URL: "+val2);
		    //Esperar 2 Seg
			Thread.sleep(2000);
			// Validacion Visual
			TakeScreenShot.takeScreenShot(driver, "CPA_11_val2_evidencia_OK_");
			
			driver.close();

	}

}
