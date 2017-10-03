package belcorp.test.MiCuentaOpciones;

import org.testng.annotations.Test;
import belcorp.pages.LoginPage;
import belcorp.pages.MiCuentaMisPedidosPage;
import belcorp.utils.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CPA_05_MiCuenta_Mis_Pedidos_Realizados {
	
	@Test
	public void CPA_01() {
		
		WebDriver driver = BrowserFactory.startBrowser("firefox","https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
		MiCuentaMisPedidosPage misPedidos = PageFactory.initElements(driver, MiCuentaMisPedidosPage.class);
		LoginPage login_page = PageFactory.initElements(driver, LoginPage.class);
		login_page.loginBelcorp("tsoft@yopmail.com","Tsoft123");
		
		misPedidos.MisPedidos();
		
	}
}
