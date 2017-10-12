/**
 * 
 */
package belcorp.pages;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * @author eduardo.araya
 *
 */
public class SearchPage {
	
	WebDriver driver;
	
	public SearchPage (WebDriver ldriver)
	{
		this.driver = ldriver;
		
	}
	
	//Barra Busqueda
	@FindBy (how = How.XPATH, using =".//*[@id='js-site-search-input']")
	@CacheLookup
	WebElement barra_busqueda;
	
	//Boton Barra Busqueda
	@FindBy (how = How.CLASS_NAME, using ="btn-search-box")
	@CacheLookup
	WebElement search_button;
	
	//Busquedas Categorias 1-5
	@FindBy (how = How.ID, using ="EsikaMaquillajeNavNode")
	@CacheLookup
	WebElement cat_maquillaje;
	
	@FindBy (how = How.ID, using ="EsikaPerfumesNavNode")
	@CacheLookup
	WebElement cat_perfume;
	
	@FindBy (how = How.ID, using ="EsikaTratamientoPielNavNode")
	@CacheLookup
	WebElement cat_piel;
	
	@FindBy (how = How.ID, using ="EsikaCuidadoPersonalNavNode")
	@CacheLookup
	WebElement cat_cuidado;
	
	@FindBy (how = How.ID, using ="EsikaSalesNavNode")
	@CacheLookup
	WebElement cat_oferta;
	
	//Busqueda Sub-Categorias
	//Maquillaje
	@FindBy (how  = How.ID, using="lnk-pri-labios")
	@CacheLookup
	WebElement subc_Labios;

	@FindBy (how  = How.ID, using="lnk-pri-ojos")
	@CacheLookup
	WebElement subc_Ojos;
	
	@FindBy (how  = How.ID, using="lnk-pri-uñas")
	@CacheLookup
	WebElement subc_Uñas;
	
	@FindBy (how = How.ID, using="lnk-pri-accesorios")
	@CacheLookup
	WebElement subc_accesorios;
	
	@FindBy (how = How.ID, using="id='lnk-pri-rostro")
	@CacheLookup
	WebElement subc_rostro;
	//Perfumes
	@FindBy (how = How.ID, using="lnk-pri-mujer")
	@CacheLookup
	WebElement subc_mujer;
	
	@FindBy (how = How.ID, using="lnk-pri-hombre")
	@CacheLookup
	WebElement subc_hombre;
	//Piel
	@FindBy (how = How.ID, using= "lnk-pri-limpiadorasydesmaquilladoras")
	@CacheLookup
	WebElement subc_limpDesmaquilla;
	
	@FindBy(how = How.ID, using= "lnk-pri-cuidadoespecializado")
	@CacheLookup
	WebElement subc_cuidadoEspecial;
	//Cuidado Personal
	@FindBy (how = How.ID, using ="lnk-pri-cuerpo")
	@CacheLookup
	WebElement subc_cuerpo;
	
	@FindBy (how = How.ID, using ="lnk-pri-cabello")
	@CacheLookup 
	WebElement subc_cabello;
	
	@FindBy (how = How.ID, using ="lnk-pri-niñosybebé")
	@CacheLookup 
	WebElement subc_NiñosBebe;
	
	@FindBy (how = How.ID, using ="lnk-pri-manosypies")
	@CacheLookup
	WebElement subc_ManosPies;
	
	public void BuscarCategoria(String categoria)
	{
		try {
			Thread.sleep(1000);
			switch(categoria)
			{
			case "maquillaje":
				cat_maquillaje.click();
				break;
			case "perfume":
				cat_perfume.click();
				break;
			case "piel":
				cat_piel.click();
				break;
			case "cuidado":
				cat_cuidado.click();
				break;
			case "":
				cat_oferta.click();
				break;
			default:
				break;
			}
			Thread.sleep(500);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			
	}

	public void BuscarSubCategoria(String categoria)
	{
		Actions act = new Actions(driver);
		
		try {
			Thread.sleep(1000);
			switch(categoria)
			{
			case "maquillaje":
				//arreglo de subcategoria -- maquillaje
				WebElement[ ] subCat_Maquillaje = {subc_Labios, subc_Ojos,subc_Uñas,subc_accesorios,subc_rostro};
				WebElement subCat_Aleatoria = subCat_Maquillaje[ new Random().nextInt(4)];
				
				act.moveToElement(cat_maquillaje).perform();
				act.moveToElement(subCat_Aleatoria).perform();
				subCat_Aleatoria.click();
				System.out.println("SubCategoria utilizada en ejecucion: " + subCat_Aleatoria);
				Thread.sleep(500);
				break;
			case "perfume":
				//arreglo de subcategoria -- perfume
				WebElement[ ] subCat_perfume = {subc_mujer, subc_hombre};
				WebElement subCat_AleatoriaB = subCat_perfume[ new Random().nextInt(1)];
				
				act.moveToElement(cat_perfume).perform();
				act.moveToElement(subCat_AleatoriaB).perform();
				subCat_AleatoriaB.click();
				System.out.println("SubCategoria utilizada en ejecucion: " + subCat_AleatoriaB);
				Thread.sleep(500);
				break;
			case "piel":
				//arreglo de subcategoria -- piel
				WebElement[ ] subCat_piel = {subc_limpDesmaquilla, subc_cuidadoEspecial};
				WebElement subCat_AleatoriaC = subCat_piel[ new Random().nextInt(1)];
				
				act.moveToElement(cat_piel).perform();
				act.moveToElement(subCat_AleatoriaC).perform();
				subCat_AleatoriaC.click();
				System.out.println("SubCategoria utilizada en ejecucion: " + subCat_AleatoriaC);
				Thread.sleep(500);
				break;
			case "cuidado":
				//arreglo de subcategoria -- cuidado personal
				WebElement[ ] subCat_CuidadoPersonal = {subc_cuerpo, subc_cabello,subc_NiñosBebe,subc_ManosPies};
				WebElement subCat_AleatoriaD = subCat_CuidadoPersonal[ new Random().nextInt(3)];
				
				act.moveToElement(cat_cuidado).perform();
				act.moveToElement(subCat_AleatoriaD).perform();
				subCat_AleatoriaD.click();
				System.out.println("SubCategoria utilizada en ejecucion: " + subCat_AleatoriaD);
				Thread.sleep(500);
				break;
			case "":
				cat_oferta.click();
				break;
			default:
				break;
			}
			Thread.sleep(500);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String BuscarSubCategoriaStr(String categoria)
	{
		Actions act = new Actions(driver);
		WebElement subCat_Aleatoria = null;
		String text = null;
		
		try {
			Thread.sleep(1000);
			switch(categoria)
			{
			case "maquillaje":
				//arreglo de subcategoria -- maquillaje
				WebElement[ ] subCat_Maquillaje = {subc_Labios, subc_Ojos,subc_Uñas,subc_accesorios,subc_rostro};
				subCat_Aleatoria = subCat_Maquillaje[ new Random().nextInt(4)];
				text = subCat_Aleatoria.getAttribute("data-title");
				act.moveToElement(cat_maquillaje).perform();
				act.moveToElement(subCat_Aleatoria).perform();
				subCat_Aleatoria.click();
				System.out.println("SubCategoria utilizada en ejecucion: " + text);
				Thread.sleep(500);
				break;
			case "perfume":
				//arreglo de subcategoria -- perfume
				WebElement[ ] subCat_perfume = {subc_mujer, subc_hombre};
				subCat_Aleatoria = subCat_perfume[ new Random().nextInt(1)];
				text = subCat_Aleatoria.getAttribute("data-title");
				act.moveToElement(cat_perfume).perform();
				act.moveToElement(subCat_Aleatoria).perform();
				subCat_Aleatoria.click();
				System.out.println("SubCategoria utilizada en ejecucion: " + text);
				Thread.sleep(500);
				break;
			case "piel":
				//arreglo de subcategoria -- piel
				WebElement[ ] subCat_piel = {subc_limpDesmaquilla, subc_cuidadoEspecial};
				subCat_Aleatoria = subCat_piel[ new Random().nextInt(1)];
				text = subCat_Aleatoria.getAttribute("data-title");
				act.moveToElement(cat_piel).perform();
				act.moveToElement(subCat_Aleatoria).perform();
				subCat_Aleatoria.click();
				System.out.println("SubCategoria utilizada en ejecucion: " + text);
				Thread.sleep(500);
				break;
			case "cuidado":
				//arreglo de subcategoria -- cuidado personal
				WebElement[ ] subCat_CuidadoPersonal = {subc_cuerpo, subc_cabello,subc_NiñosBebe,subc_ManosPies};
				subCat_Aleatoria = subCat_CuidadoPersonal[ new Random().nextInt(3)];
				text = subCat_Aleatoria.getAttribute("data-title");
				act.moveToElement(cat_cuidado).perform();
				act.moveToElement(subCat_Aleatoria).perform();
				subCat_Aleatoria.click();
				System.out.println("SubCategoria utilizada en ejecucion: " + text);
				Thread.sleep(500);
				break;
			case "":
				cat_oferta.click();
				break;
			default:
				break;
			}
			Thread.sleep(500);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return text;
		
	}
	
	public void BarraBusqueda(String subcategoria) 
	{
		try {
				Thread.sleep(1000);
				barra_busqueda.click();
				barra_busqueda.sendKeys(subcategoria);
				search_button.click();
				Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	
		
}
