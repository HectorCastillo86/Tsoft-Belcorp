/**
 * 
 */
package belcorp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author hector.castillo
 *
 */

public class BolsaCompraPage {
	
	WebDriver driver;
	String url="https://aws-esika.esika.com:9002/co/co/tratamiento-piel/c/esika-03";
	

	public BolsaCompraPage (WebDriver ldriver)
	{
		this.driver = ldriver;
		
	}
	
	@FindBy(how= How.XPATH, using =".//*[@id='navbar']/ul[2]/li[5]/a")
	@CacheLookup
	WebElement btnCarroCompra;
	
	@FindBy (how= How.XPATH, using = ".//*[@id='cantItemsCart']")
	@CacheLookup
	WebElement numCarroCompra;
									
	@FindBy(how= How.XPATH, using ="html/body/main/div[2]/div[2]/div[2]/div/ul/div/div/div")
	@CacheLookup
	WebElement ImagenProd;
	
	@FindBy(how= How.XPATH, using ="html/body/main/div[2]/div[3]/div[2]/div/div[2]/div[14]/div/div")
	@CacheLookup
	WebElement ImagenProdUnidad;
	
	@FindBy(how= How.XPATH, using ="//*[@id='addToCartButton']") 
	@CacheLookup
	WebElement btnAgregarItem;
	
	@FindBy(how= How.XPATH, using ="//div[3]/button")
	@CacheLookup
	WebElement btnContinuarComprando;
	
	@FindBy(how= How.XPATH, using=".//*[@id='addToCartLayer'] // a[contains(text(),'Continuar comprando')]")
	@CacheLookup
	WebElement btnContComprandoPopUp;
	
	@FindBy(how= How.CSS, using=".btn.btn-primary.btn-block.add-to-cart-button")
	@CacheLookup
	WebElement btnIrBolsa;

	@FindBy(how= How.XPATH, using=".//*[@id='cboxClose']")
	@CacheLookup
	WebElement btnCerrarPopUp;
	
	@FindBy(how= How.XPATH, using="html/body/main/div[1]/nav/div/div[2]/ul[2]/li[5]/a/span")
	@CacheLookup
	WebElement btnCantCarro;
	
	@FindBy(how= How.CSS, using=".btn-search-box")
	@CacheLookup
	WebElement btnBuscar;
	
	@FindBy(how= How.XPATH, using="(.//*[@id='js-site-search-input'][2])")
	//@CacheLookup
	WebElement agregarSKU;
	
	@FindBy(how= How.XPATH, using="html/body/main/div[1]/header/div[1]/div/div/div/div[3]/div/form/input[2]")
	//@CacheLookup
	WebElement agregarSKUB;
	
	@FindBy(how= How.CLASS_NAME, using="btn-search-box")
	@CacheLookup
	WebElement buscarSKU;
	
	@FindBy(how= How.XPATH, using ="//button[@class='btn btn-block checkoutButton continueCheckout']")
	@CacheLookup
	WebElement btnPagarCompra;
	
	@FindBy(how= How.XPATH, using ="html/body/main/div[2]/div[6]/div/div[2]/div[3]/div[1]")
	@CacheLookup
	WebElement btnCuponDescuento;
	
	@FindBy(how= How.ID, using ="voucherCode")
	@CacheLookup
	WebElement txtCupon;
	
	@FindBy(how= How.ID, using ="voucherButton")
	@CacheLookup
	WebElement btnCupon;
	
	@FindBy(how= How.ID, using ="removeEntry_0")
	@CacheLookup
	WebElement btnEliminarProd;
	
	@FindBy(how= How.ID, using ="releaseVoucherButton")
	@CacheLookup
	WebElement BorrarCupon;
	
	@FindBy(how= How.XPATH, using =".//*[@id='updateCartForm0']/div[1]/button[2]")
	//@CacheLookup
	WebElement incrementarProd;
	
	
	
	public void incrementarProducto()
	{
		try {

				Thread.sleep(500);
				incrementarProd.click();
				incrementarProd.sendKeys(org.openqa.selenium.Keys.ENTER);
				Thread.sleep(1000);
			

			
			//WebElement webElement = driver.findElement(By.xpath(""));
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void agregarUnArticulo()
	{
			try {
				ImagenProdUnidad.click();
				btnAgregarItem.click();
				Thread.sleep(1000);
				btnIrBolsa.click();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void buscarProducto(String sku)
	{
		try {
			Thread.sleep(2000);
			agregarSKUB.clear();
			agregarSKUB.sendKeys(sku);
			agregarSKUB.sendKeys(org.openqa.selenium.Keys.ENTER);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void AgregarArticuloPorSKUB(String sku) throws InterruptedException {
		try {
			Thread.sleep(2000);
			agregarSKUB.sendKeys(sku);
			agregarSKUB.submit();
			if (!driver.findElement(By.cssSelector(".headline")).isDisplayed())
			{
			
				Actions act = new Actions(driver);
				act.moveToElement(ImagenProd).perform();
				Thread.sleep(500);
				btnAgregarItem.submit();
			
			}
			Thread.sleep(1000);
			driver.navigate().back();
			agregarSKUB.clear();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void posicionarCarro()
	{
		try {
			Thread.sleep(1000);
			Actions act = new Actions(driver);
			act.moveToElement(numCarroCompra).perform();
			Thread.sleep(1000);
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void AgregarArticuloPorSKU(String sku) throws InterruptedException {
		try {
			Thread.sleep(2000);
			agregarSKU.sendKeys(sku);
			agregarSKU.submit();
			if (!driver.findElement(By.cssSelector(".headline")).isDisplayed())
			{
			
				Actions act = new Actions(driver);
				act.moveToElement(ImagenProd).perform();
				Thread.sleep(500);
				btnAgregarItem.submit();
			
			}
			Thread.sleep(1000);
			driver.navigate().back();
			agregarSKU.clear();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void NoAgregarArticulo()
	{
		try {
			Thread.sleep(500);
			driver.navigate().to("https://aws-compra.esika.com:9002/co/cart");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}

	public void UbicadoBolsaDeCompra()
	{
		try {
			Thread.sleep(1000);
			driver.navigate().to("https://aws-compra.esika.com:9002/co/cart");
			btnContinuarComprando.click();
			

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void irABolsaCompra()
	{
		try {
			Thread.sleep(500);
			btnCarroCompra.click();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void BotonIrAPagar()
	{
		try {
			Thread.sleep(500);
			Actions act = new Actions(driver);
			act.moveToElement(btnPagarCompra).perform();
			Thread.sleep(500);
			btnPagarCompra.click();
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	

		
	public void CuponValido(String cupon)
	{
		try {
			Thread.sleep(2000);
			if(driver.findElement(By.xpath(".//*[@id='releaseVoucherButton']")).isDisplayed())
			{
				BorrarCupon.click();
				Thread.sleep(2000);
				txtCupon.click();
				txtCupon.sendKeys(cupon);
				btnCupon.click();
				Thread.sleep(500);
				
			}else 
			{
				txtCupon.click();
				txtCupon.sendKeys(cupon);
				btnCupon.click();
				Thread.sleep(500);
				
			}			
			

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}

	public void ContinuaComprando()
	{
		try {
			
			Thread.sleep(2000);
			btnContinuarComprando.click();
			Thread.sleep(1000);					

		} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}				
		
	}
	
	public void EliminarProductoBolsa()
	{
		try {
			Thread.sleep(500);
			btnEliminarProd.click();
			Thread.sleep(2000);
			if(driver.findElement(By.xpath(".//*[@id='releaseVoucherButton']")).isDisplayed())
			{
				BorrarCupon.click();
				Thread.sleep(2000);
				txtCupon.click();
				txtCupon.sendKeys("VPR-T38H-MAC8-C3F4-S");
				btnCupon.click();
				Thread.sleep(500);
				btnContinuarComprando.click();
			}else 
			{
				txtCupon.click();
				txtCupon.sendKeys("VPR-T38H-MAC8-C3F4-S");
				btnCupon.click();
				Thread.sleep(500);
				btnContinuarComprando.click();
			}
		} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void EliminaProdContComprando()
	{
		try {
			Thread.sleep(500);
			btnEliminarProd.click();
			Thread.sleep(500);

		} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

}
