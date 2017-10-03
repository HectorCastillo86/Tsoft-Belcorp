package belcorp.test.LoginBusquedaProducto;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import belcorp.pages.LoginPage;
import belcorp.pages.SearchPage;
import belcorp.test.T01_LoginBelcorp;
import belcorp.utils.BrowserFactory;
import java.util.Random;

public class CPA_09_Busqueda_Categoria {
	
	@Test
	public void CPA_09()
	{
		WebDriver driver = BrowserFactory.startBrowser("firefox","https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
		SearchPage BusquedaProd = PageFactory.initElements(driver, SearchPage.class);
		LoginPage login_page = PageFactory.initElements(driver, LoginPage.class);
		
		login_page.loginBelcorp(T01_LoginBelcorp.user, T01_LoginBelcorp.pass);
		
		String[ ] Categoria = {"maquillaje", "perfume","piel","cuidado","oferta"};
		String Cat_Aleatoria = Categoria[ new Random().nextInt(4)];
		BusquedaProd.BuscarCategoria(Cat_Aleatoria);
		
	}

}
