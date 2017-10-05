package belcorp.test.Checkout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import belcorp.pages.BolsaCompraPage;
import belcorp.pages.CheckOutPage;
import belcorp.pages.LoginPage;
import belcorp.pages.ProcedToCheckOutPage;
import belcorp.pages.RegistroPage;
import belcorp.test.RegistroLogin.CPA_01_Login_usuario_existente;
import belcorp.utils.BrowserFactory;

public class CPA_024_Registrar_AgregarNuevaDireccion_Comprar {

	@Test
	public void CPA_024_Registrar_AgregarNuevaDireccion_Comprar() {
		WebDriver driver = BrowserFactory.startBrowser("firefox","https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03");
		LoginPage login_page = PageFactory.initElements(driver, LoginPage.class);
		BolsaCompraPage AgregarABolsa = PageFactory.initElements(driver, BolsaCompraPage.class);
		CheckOutPage CkeckOutPagar = PageFactory.initElements(driver, CheckOutPage.class);
		ProcedToCheckOutPage ContRegistrar = PageFactory.initElements(driver, ProcedToCheckOutPage.class);
		RegistroPage registro = PageFactory.initElements(driver, RegistroPage.class);
		
		AgregarABolsa.agregarUnArticulo();
		AgregarABolsa.BotonIrAPagar();
		ContRegistrar.Registrarse();
    	// DATOS CPA
    	String nombre = "juan";
    	String apellido= "juanes";
    	String email = "carlos.juanes9@yopmail.com";
    	String pwd = "Tsoft123";
    	String tipoDoc = "Cédula de extranjería"; // valores "Cédula de identidad" ; "Cédula de extranjería"; "NIT" ; "Pasaporte"
    	String checkNumDoc= "174372008";
    	
		registro.NuevoRegistro(nombre, apellido, email, pwd, tipoDoc, checkNumDoc);
		
		String ciudad = "BOGOTA";
		String pais = "Colombia";
		String depar = "BOGOTA";
		String direccion = "Las perdices";
		String nrodep = "1604-B";
		String infoAdicional = "Metro Tobalaba";
		String telefono = "091919191";
		
		CkeckOutPagar.DireccionCheckOut(pais, depar, ciudad, direccion, nrodep, infoAdicional, telefono);
		CkeckOutPagar.ContinuarMetodoPago();
		CkeckOutPagar.MetodoPagoEfectivo();
	}

}
