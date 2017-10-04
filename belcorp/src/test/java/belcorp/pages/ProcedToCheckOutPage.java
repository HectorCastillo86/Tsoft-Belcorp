/**
 * 
 */
package belcorp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

/**
 * @author gino.telenta
 * @see P�gina de CheckOut "https://aws-compra.esika.com:9002/co/login/checkout"
 */
public class ProcedToCheckOutPage {
	
	WebDriver driver;
	private String title=" Proceed to Checkout | Belcorp Site Colombia"; 
	
	@FindBy(how=How.XPATH, using="html/body/main/div[2]/div[1]/a")
	@CacheLookup 
	WebElement btnContShop;
	
	@FindBy(how=How.ID, using="j_username")
	@CacheLookup 
	WebElement txtUserEmail;
	
	@FindBy(how=How.ID, using="j_password")
	@CacheLookup 
	WebElement txtPassword;
	
	@FindBy(how=How.XPATH, using=".//*[@id='loginForm']/div[3]/button")
	@CacheLookup 
	WebElement btnLogAndCont;
	
	@FindBy(how=How.XPATH, using=".//*[@id='loginForm']/div[3]/div[3]")
	@CacheLookup 
	WebElement btnRegister;
	
	@FindBy(how=How.ID, using="guest.email")
	@CacheLookup 
	WebElement txtShopEmail;
	
	@FindBy(how=How.ID, using="guest.emailConfirm")
	@CacheLookup 
	WebElement txtShopEConfirm;
	
	@FindBy(how=How.ID, using="guest.firstName")
	@CacheLookup 
	WebElement txtShopName;
	
	@FindBy(how=How.ID, using="guest.lastName")
	@CacheLookup 
	WebElement txtShopLName;
	
	@FindBy(how=How.ID, using="guest.docType")
	@CacheLookup 
	WebElement dListShopTpDoc;
	
	@FindBy(how=How.ID, using="guest.docNumber")
	@CacheLookup 
	WebElement txtShopNmDoc;
	
	@FindBy(how=How.XPATH, using=".//*[@id='guestForm']/div[7]/button")
	@CacheLookup 
	WebElement btnShopContInvite;
	
	@FindBy(how=How.ID, using="loginFB")
	@CacheLookup 
	WebElement btnLoginFb;	
	
	/*
	 * Constructor de clase que recibe el driver del navegador para inicializarlo y comprobar que se carg� correctamente.
	 * @param driver Tipo WebDriver que contiene la informaci�n del navegador a utilizar.
	 */
	public ProcedToCheckOutPage(WebDriver ldriver) 
		{
			this.driver = ldriver;
			
		}
	
	/*
	 * M�todo tipo void que presiona bot�n seguir comprando.
	 */
	public void ContinuarComprando() {
		try {
			btnContShop.click();
			Thread.sleep(500);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}	
	}
	
	/*
	 * M�todo que permite logear y continuar con la compra empezada.
	 * @param usuario Tipo String que contiene cadena con el usuario.
	 * @param password Tipo String que contiene cadena con la contrase�a.
	 */
	public void IngresarContinuar(String usuario, String password) {
		try {
			
			txtUserEmail.sendKeys(usuario);
			txtPassword.sendKeys(password);
			Thread.sleep(300);
			btnLogAndCont.click();
			Thread.sleep(500);
			
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * M�todo tipo void que redirige a la p�gina de registrar nuevo usuario.
	 */
	public void Registrarse() {
		try {
			btnRegister.click();
			Thread.sleep(500);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * M�todo tipo void que permite ingresar datos de usuario b�sico y permite continuar con la compra sin logearse o registrarse.
	 * @param email Tipo String que contiene el correo del ususario
	 * @param email2 Tipo String que contiene el email de verificaci�n.
	 * @param nombre Tipo String que contiene el nombre del usuario, �ste no debe ser muy largo, de lo contrario la p�gina mostrar� un mensaje de error.
	 * @param apellido Tipo String que contiene el apellido del usuario, �ste no debe ser muy largo, de lo contrario la p�gina mostrar� un mensaje de error.
	 * @param tipoDoc Tipo String que corresponde a la opci�n elegida de la lista desplegable para el Tipo de Documento.
	 * @param nroDoc Tipo String corresponde al n�mero de documento elegiro en tipoDoc.
	 */
	public void ContinuarComoInvitado(String email, String email2, String nombre, String apellido, String tipoDoc, String nroDoc) {
		try {
			
			txtShopEmail.sendKeys(email);
			txtShopEConfirm.sendKeys(email2);
			txtShopName.sendKeys(nombre);
			txtShopLName.sendKeys(apellido);
			Select dropDown= new Select(dListShopTpDoc);
			dropDown.selectByVisibleText(tipoDoc);
			dListShopTpDoc.sendKeys(tipoDoc);
			txtShopNmDoc.sendKeys(nroDoc);
			Thread.sleep(300);
			btnShopContInvite.click();
			Thread.sleep(500);
			
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * M�todo tipo void que presiona el bot�n de logear con Facebook, para �sto uno debe estar logeado previamente.
	 */
	public void IngresarConFacebook() {
		try {
			btnLoginFb.click();
			Thread.sleep(500);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
