
package belcorp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conector {
	public Connection con=null;

	public Connection getConexion(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String usuario="root";
			String clave="sofia2008";
			con=DriverManager.getConnection("jdbc:mysql://10.136.12.103/:3306/log", usuario ,clave);
			System.out.println("Conectado");
			return con;
			
		}catch(ClassNotFoundException e){
			System.out.println("Error en classname:"+e);
			return null;
		}catch(SQLException e){
			System.out.println("error sql:"+e);
			return null;	
		}
		
	}
	
	}

