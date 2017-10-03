package belcorp.test.LoginBusquedaProducto;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import belcorp.pages.LoginPage;
import belcorp.pages.SearchPage;
import belcorp.test.T01_LoginBelcorp;
import belcorp.utils.BrowserFactory;

public class CPA_10_Busqueda_Cat_SubCategoria {

	@Test
	public void CPA_10()
	{
		WebDriver driver = BrowserFactory.startBrowser("firefox","https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
		SearchPage BusquedaProd = PageFactory.initElements(driver, SearchPage.class);
		
		LoginPage login_page = PageFactory.initElements(driver, LoginPage.class);
		
		login_page.loginBelcorp(T01_LoginBelcorp.user, T01_LoginBelcorp.pass);
		String[ ] Categoria = {"maquillaje", "perfume","piel","cuidado","oferta"};
		String Cat_Aleatoria = Categoria[ new Random().nextInt(4)];
		
		BusquedaProd.BuscarSubCategoria(Cat_Aleatoria);
		

	}
	
}
