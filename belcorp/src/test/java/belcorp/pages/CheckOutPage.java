/**
 * 
 */
package belcorp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import belcorp.pages.AgregarDireccionPage;

/**
 * @author gino.telenta
 * @see P�gina inicio "https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03"
 * @see Direcci�n de env�o "https://aws-compra.esika.com:9002/co/checkout/multi/delivery-address/add"
 * @see Opciones de env�o "https://aws-compra.esika.com:9002/co/checkout/multi/delivery-method/choose"
 * @see M�todo de pago "https://aws-compra.esika.com:9002/co/checkout/multi/payment-method/add"
 * @see Revisi�n de pedido "https://aws-compra.esika.com:9002/co/checkout/multi/summary/view"
 */

public class CheckOutPage {
	
	WebDriver driver;
	private String title="Checkout | Belcorp Site Colombia";
	
	public CheckOutPage (WebDriver ldriver)
	{
		this.driver = ldriver;
		
	}

	
	@FindBy(how=How.XPATH, using="html/body/main/div[2]/div[2]/div[2]/div/div[3]/div[1]")
	@CacheLookup
	WebElement btnbtnAddCupon;

	@FindBy(how=How.ID, using="voucherCode")
	@CacheLookup
	WebElement txtCupon;

	@FindBy(how=How.ID, using="voucherButton")
	@CacheLookup
	WebElement btnSendCupon;
	
	@FindBy(how=How.ID, using="address.country")
	@CacheLookup
	WebElement dListCountry;
	
	@FindBy(how=How.ID, using="address.regionIsoParent1")
	@CacheLookup
	WebElement dListRegion;
	
	@FindBy(how=How.ID, using="address.regionIso")
	@CacheLookup
	WebElement dListCity;
	
	@FindBy(how=How.ID, using="address.line1")
	@CacheLookup
	WebElement txtAdress;
	
	@FindBy(how=How.ID, using="address.line2")
	@CacheLookup
	WebElement txtStreet;
	
	@FindBy(how=How.ID, using="address.referencia")
	@CacheLookup
	WebElement txtMoreInfo;
	
	@FindBy(how=How.ID, using="address.phone")
	@CacheLookup
	WebElement txtPhone;
	
	@FindBy(how=How.ID, using="addressSubmit")
	@CacheLookup
	WebElement btnContDelivery;
	
	@FindBy(how=How.XPATH, using="html/body/main/div[2]/div[2]/div[1]/div[4]/div/button")
	@CacheLookup
	WebElement btnNewAddress;
	
	@FindBy(how=How.XPATH, using="html/body/main/div[2]/div[1]/div[1]/div[3]/a[1]")
	@CacheLookup
	WebElement btnReturnAddress;
	
	@FindBy(how=How.ID, using="delivery_method")
	@CacheLookup
	WebElement dListOpciones;
	
	@FindBy(how=How.ID, using="deliveryMethodSubmit")
	@CacheLookup
	WebElement btnMethodPay;
	
	@FindBy(how=How.ID, using="CREDIT_CARD_A")
	@CacheLookup
	WebElement dListCreditCard;
	
	@FindBy(how=How.ID, using="CASH_AGENCY_A")
	@CacheLookup
	WebElement dListAgency;
	
	@FindBy(how=How.XPATH, using="html/body/main/div[2]/div[1]/div[1]/div[3]/a[2]")
	@CacheLookup
	WebElement btnReturnMethodDeliv;
	
	@FindBy(how=How.ID, using="paymentMethodBtn")
	@CacheLookup
	WebElement btnReviewOrder;
	
	@FindBy(how=How.XPATH, using="html/body/main/div[2]/div[1]/div[1]/div[3]/a[3]")
	@CacheLookup
	WebElement btnReturnMethodPay;

	@FindBy(how=How.CSS, using=".checkbox>label")
	@CacheLookup
	WebElement optionTermns;
	
	@FindBy(how=How.ID, using="btnPlaceOrder")
	@CacheLookup
	WebElement btnEndShop;
	
	@FindBy(how=How.XPATH, using=".//*[@id='cashPaymentForm']/div[2]/ul/li[2]/label")
	@CacheLookup
	WebElement pagoEfectivo;	
	
	@FindBy (how = How.CSS, using="#cboxLoadedContent > form:nth-child(3) > button:nth-child(7)")
	@CacheLookup
	WebElement btnGuardarDireccion;
	
	@FindBy (how = How.XPATH, using="html/body/main/div[2]/div[4]/div/div[2]/div[2]/div")
	@CacheLookup
	WebElement direccion;
	
	@FindBy (how = How.ID, using="addressSubmit")
	@CacheLookup
	WebElement btnContinuarEnvio;
	
	
	

	/*
	 * M�todo tipo void que Ingresa la direcci�n de env�o para un cliente
	 * @param pais Tipo String que refiere al pa�s a donde se enviar� el pedido.
	 * @param depar Tipo String que refiere al departamento del pa�s a donde se enviar� el pedido.
	 * @param ciudad Tipo String que refiere a la ciudad del departamento a donde se enviar� el pedido.
	 * @param direccion Tipo String que se refiere al ubigeo de la ciudad a donde se enviar� el pedido.
	 * @param nrodep Tipo String que se refiere al n�mero de la direcci�n donde se enviar� el pedido.
	 * @param infoAdicional Tipo String que se refiere a alg�n detalle que requiera a�adir el usuario sobre el pedido.
	 * @param telefono Tipo String que se refiere al n�mero telef�nico de contacto para el pedido.
	 */
	public void IngresarDireccionEnvio(String pais, String depar, String ciudad, String direccion, String nrodep, String infoAdicional, String telefono) {
		Actions act = new Actions(driver);
		try {
			Thread.sleep(500);
			dListCountry.submit();
			Thread.sleep(500);
			Select dropdown = new Select(driver.findElement(By.id("address.regionIsoParent1")));
			dropdown.selectByVisibleText("BOLIVAR");
			Thread.sleep(500);
			Select dropdownB = new Select(driver.findElement(By.id("address.regionIso")));
			dropdownB.selectByVisibleText("CALAMAR");
			Thread.sleep(500);
			txtAdress.sendKeys(direccion);
			txtStreet.sendKeys(nrodep);
			txtMoreInfo.sendKeys(infoAdicional);
			txtPhone.sendKeys(telefono);
			act.moveToElement(btnGuardarDireccion).perform();
			btnGuardarDireccion.click();
			Thread.sleep(500);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void DireccionCheckOut(String pais, String depar, String ciudad, String direccion, String nrodep, String infoAdicional, String telefono)
	{
		Actions act = new Actions(driver);
		try {
			
			Thread.sleep(2000);
			driver.navigate().refresh();
			dListCountry.submit();
			Thread.sleep(500);
			Select dropdown = new Select(driver.findElement(By.id("address.regionIsoParent1")));
			dropdown.selectByVisibleText("BOLIVAR");
			Thread.sleep(500);
			Select dropdownB = new Select(driver.findElement(By.id("address.regionIso")));
			dropdownB.selectByVisibleText("CALAMAR");
			Thread.sleep(500);
			txtAdress.sendKeys(direccion);
			Thread.sleep(500);
			txtStreet.sendKeys(nrodep);
			Thread.sleep(500);
			txtMoreInfo.sendKeys(infoAdicional);
			Thread.sleep(500);
			txtPhone.sendKeys(telefono);
			Thread.sleep(500);
			act.moveToElement(btnContinuarEnvio).perform();
			btnContinuarEnvio.click();
			Thread.sleep(500);
			
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*
	 * M�todo tipo void que despu�s de llenar la direcci�n de env�o, elige el tipo de env�o y click en pagar
	 * @param opcionEnvio Tipo String que se refiere a la opci�n de env�o  elegir.
	 */
	public void ElegirEnvioYPagar(String opcionEnvio){
		try {
			Select dDownOpcionEnv=new Select(dListOpciones);
			dDownOpcionEnv.selectByVisibleText(opcionEnvio);
			Thread.sleep(300);
			btnMethodPay.click();
			Thread.sleep(500);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}	
	}
	
	/*
	 * M�todo tipo void que despu�s de elegir el tipo de env�o, elige M�todo de pago.
	 * @param formaPago Tipo String que se refiere a opciones de pago en efectivo que se muestran en la p�gina.
	 */
	public void MetodoPagoEfectivo() {
		try {
			Thread.sleep(500);
			dListAgency.click();
			Thread.sleep(500);
			pagoEfectivo.click();
			Thread.sleep(300);
			btnReviewOrder.click();
			Thread.sleep(500);
			optionTermns.click();
			Thread.sleep(300);
			btnEndShop.click();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	//public void MetodoPagoTarjeta() {}
	//public void MetodoPagoAgencia() {}
	
	/*
	 * M�todo tipo void que muestra el resumen del pedido antes de aceptarlo formalmente, 
	 * se deben leer y aceptar los t�rminos y condiciones.
	 */
	public void RevisarPedido() {
		try{
			btnReviewOrder.click();
			Thread.sleep(500);
			btnEndShop.click();//Finaliza la compra
			Thread.sleep(500);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * M�todo tipo void que dentro del CheckOut permite regresar a la opci�n de "Direcci�n de Env�o".
	 */
	public void RegresarDireccionEnvio() {
		try {
			btnReturnAddress.click();
			Thread.sleep(500);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*
	 * M�todo tipo void que dentro del CheckOut permite regresar a la opci�n de "Opciones de Env�o".
	 */
	public void RegresarOpcionesEnvio() {
		try {
			btnReturnMethodDeliv.click();
			Thread.sleep(500);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * M�todo tipo void que dentro del CheckOut permite regresar a la opci�n de "M�todos de Pago".
	 */
	public void RegresarMetodoPago() {
		try {
			btnReturnMethodPay.click();
			Thread.sleep(500);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * M�todo tipo void que despliega el campo del cup�n para agregarlo y obtener un descuento.
	 * @param cupon Tipo String que representa el c�digo del cup�n a ingresar.
	 */
	public void AgregarCupon(String cupon) {
		try {
			btnbtnAddCupon.click();
			Thread.sleep(300);
			txtCupon.sendKeys(cupon);
			btnSendCupon.click();
			Thread.sleep(500);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void ContinuarOpcionesEnvio()
	{
		try {
			btnContDelivery.click();
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ContinuarMetodoPago()
	{
		try {
			Thread.sleep(500);
			btnMethodPay.click();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void presionarAgregarDireccion()
	{
		try {
			driver.navigate().refresh();
			Thread.sleep(1000);
			btnNewAddress.click();
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void DireDefecto(String texto) 
	{
		try {
			Thread.sleep(500);
			
			driver.findElement(By.xpath(".//*[contains(text(),'"+texto+"')]")).click();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

} 