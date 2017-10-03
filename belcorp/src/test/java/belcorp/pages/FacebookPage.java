/**
 * 
 */
package belcorp.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * @author gino.telenta
 * @see P�gina de Facebook "https://www.facebook.com/" 
 */
public class FacebookPage {
	
	WebDriver driver;
	private String title="Facebook - Inicia sesi�n o reg�strate";
	
	@FindBy(how=How.ID, using="email")
	@CacheLookup
	WebElement txtUser;
	
	@FindBy(how=How.ID, using="pass")
	@CacheLookup
	WebElement txtPassword;
	
	@FindBy(how=How.ID, using="loginbutton")
	@CacheLookup
	WebElement btnLogin;
	
	/*
	 * Constructor que recibe inicializa el driver y verifica que la p�gina cargada es la esperada.
	 * @param driver Tipo driver que corresponde al navegador.
	 */
	public FacebookPage(WebDriver driver) {
		this.driver=driver;
		System.out.println(driver.getTitle());
		if(!title.equals(driver.getTitle())) {
			throw new IllegalStateException("Esta no es la p�gina esperada: "+driver.getTitle());
		}
	}
	
	/*
	 * M�todo tipo void que permite ingresar las credenciales del usuario Facebook 
	 * para luego permitir el logeo por Facebook en la p�gina de BELCORP.
	 * @param usuario Tipo String que contiene cadena de texto con el usuario
	 * @param password Tipo String que contiene cadena de texto con la contrase�a
	 */
	public void LoginFB(String usuario, String password) {
		try {
			txtUser.sendKeys(usuario);
			txtPassword.sendKeys(password);
			Thread.sleep(300);
			btnLogin.click();
			Thread.sleep(500);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
} 
 