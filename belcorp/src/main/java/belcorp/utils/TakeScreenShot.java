package belcorp.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class TakeScreenShot {
	
	public static void takeScreenShot(WebDriver driver, String imageName) {
	    
		  Calendar calendar = Calendar.getInstance();
	      SimpleDateFormat formater = new SimpleDateFormat("ddMMyyyy_hhmmss");
	      imageName = (String)imageName+formater.format(calendar.getTime());
		
		  //Directorio donde quedaran las imagenes guardadas
	      File directory = new File("C:\\Belcorp\\Resultados\\");
	      
	 
	      try {
	         if (directory.isDirectory()) {
	            //Toma la captura de imagen
	            File imagen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	            //Mueve el archivo a la carga especificada con el respectivo nombre
	            FileUtils.copyFile(imagen, new File(directory.getAbsolutePath()   + "\\" + imageName + ".png"));
	         } else {
	            //Se lanza la excepcion cuando no encuentre el directorio
	            throw new IOException("ERROR : La ruta especificada no es un directorio!");
	         }
	      } catch (IOException e) {
	         //Impresion de Excepciones
	         e.printStackTrace();
	      }
	   }
	
}
