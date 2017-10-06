package belcorp.test.LoginBusquedaProducto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import belcorp.pages.LoginPage;
import belcorp.pages.SearchPage;
import belcorp.test.RegistroLogin.CPA_01_Login_usuario_existente;
import belcorp.utils.BrowserFactory;
import belcorp.utils.TakeScreenShot;

import java.util.Random;

public class CPA_09_Busqueda_Categoria {
	
	// Datos CPA_09
	  public static final String user = "tsoft@yopmail.com";
	  public static final String pass = "Hola123";
	  public static final String categoria_unica = "maquillaje"; // Categorias : "maquillaje", "perfume","piel","cuidado"
	
	@Test
	public void Busqueda_Categoria()
	{
		//Mostrar en Consola Datos de Ejecucion de Prueba
		System.out.println("CPA_09: Datos para Ejecucion Usuario: " +user+" , Password: "+pass+" , Categoria: "+categoria_unica);

		try {
		WebDriver driver = BrowserFactory.startBrowser("firefox","https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
		SearchPage BusquedaProd = PageFactory.initElements(driver, SearchPage.class);
		LoginPage login_page = PageFactory.initElements(driver, LoginPage.class);
		
		//Metodo Login
		login_page.loginBelcorp(user, pass);
		
		
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
			  System.out.println("CPA_09: Usuario logeado en Sistema para Ejecucion: " +username);		 
			  TakeScreenShot.takeScreenShot(driver, "CPA_09_val1_evidencia_OK_");
		  }	 
		  else {
			  TakeScreenShot.takeScreenShot(driver, "CPA_09_val1_evidencia_NOK_");		  		  
		  }
		  //***************** FIN VALIDACION **************************************
				
		
		String[ ] Categoria = {"maquillaje", "perfume","piel","cuidado","oferta"};
		//String Cat_Aleatoria = Categoria[ new Random().nextInt(4)];
		System.out.println("CPA_09: Categoria utilizada en ejecucion: " + categoria_unica);
		BusquedaProd.BuscarCategoria(categoria_unica);
		
		//************** VALIDACION 2 :  Verificar Carga de categoria Seleccionada*****************
		
		String val2 = driver.getCurrentUrl();
		//System.out.println(val2);		
		//Assert.assertEquals("https://aws-esika.esika.com:9002/co/perfumes/c/esika-02",val2);
		System.out.println("CPA_09: Se valida Url de categoria: " + categoria_unica + ". URL: "+val2);
	    //Esperar 2 Seg
		Thread.sleep(2000);
		// Validacion Visual
		TakeScreenShot.takeScreenShot(driver, "CPA_09_val2_evidencia_OK_");
	
		driver.close();
		
		} 
		catch (InterruptedException e)
		{
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}

}
