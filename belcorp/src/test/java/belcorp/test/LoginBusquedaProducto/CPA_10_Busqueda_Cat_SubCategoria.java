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

public class CPA_10_Busqueda_Cat_SubCategoria {

	@Test
	public void CPA_10()
	{
		try {
		WebDriver driver = BrowserFactory.startBrowser("firefox","https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
		SearchPage BusquedaProd = PageFactory.initElements(driver, SearchPage.class);
		
		LoginPage login_page = PageFactory.initElements(driver, LoginPage.class);
		
		login_page.loginBelcorp(CPA_01_Login_usuario_existente.user, CPA_01_Login_usuario_existente.pass);
		
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
					  System.out.println("CPA_10: Usuario logeado en Sistema para Ejecucion: " +username);		 
					  TakeScreenShot.takeScreenShot(driver, "CPA_10_val1_evidencia_OK_");
				  }	 
				  else {
					  TakeScreenShot.takeScreenShot(driver, "CPA_10_val1_evidencia_NOK_");		  		  
				  }
		 //***************** FIN VALIDACION **************************************
		
		
		String[ ] Categoria = {"maquillaje", "perfume","piel","cuidado","oferta"};
		String Cat_Aleatoria = Categoria[ new Random().nextInt(4)];
		System.out.println("CPA_10: Categoria utilizada en ejecucion: " + Cat_Aleatoria);
		BusquedaProd.BuscarSubCategoria(Cat_Aleatoria);
	
		} 
		catch (InterruptedException e)
		{
		// TODO Auto-generated catch block
		e.printStackTrace();
		}

	}
	
}
