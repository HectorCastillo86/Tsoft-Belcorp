package belcorp.test.LoginBusquedaProducto;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import belcorp.pages.LoginPage;
import belcorp.pages.SearchPage;
import belcorp.test.RegistroLogin.CPA_01_Login_usuario_existente;
import belcorp.utils.BrowserFactory;

public class CPA_11_Busqueda_Barra_SKU {
	
	@Test
	public void CPA_11()
	{
		WebDriver driver = BrowserFactory.startBrowser("firefox","https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
		SearchPage BusquedaProd = PageFactory.initElements(driver, SearchPage.class);
		
		LoginPage login_page = PageFactory.initElements(driver, LoginPage.class);
		
		login_page.loginBelcorp(CPA_01_Login_usuario_existente.user, CPA_01_Login_usuario_existente.pass);
		
		String[ ] valorBuscar = {"mascara", "pelo","shampoo","uñas","cara","accesorios"};
		String SubCat_Aleatoria = valorBuscar[ new Random().nextInt(4)];
		BusquedaProd.BarraBusqueda(SubCat_Aleatoria);

	}

}
