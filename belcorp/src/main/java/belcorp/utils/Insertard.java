package belcorp.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Insertard {
	
	//declaracion e iniciación de variables.
	int contador = 0;
	int contador2 = 0;
	int contador3 = 0;	
	int cant = 0;
	int cant2 = 0;
	int cant3 = 0;
	
	//Variable para conexión bd.
	Conector cone=new Conector(); 
		
		// valores extraidos de LogResultLbzk.
		public void insertacaso(int a , String b , int c , StringBuffer d , StringBuffer e , StringBuffer f , String g , String h , String i , String j , String l, int m ,int n) throws SQLException {
			
			Connection conn =cone.getConexion();
			
			// consulta para verificar si el caso se repite.
			PreparedStatement ps4 = conn.prepareStatement("SELECT * FROM caso where id_sistema=? and nombre_caso=? and id_subsistema=?");
			
			// remplazo de variables para prevenir  inyecciones SQL.
			ps4.setInt(1, a);
			ps4.setString(2, b);
			ps4.setInt(3, n);
			// ejecución de consulta
			ResultSet rs = ps4.executeQuery();
		
			while(rs.next())
				{
					cant=cant+1;
				}
			
	
			// consulta para verificar si el sistema existe y coincide con el idenficador de negocio.
			PreparedStatement ps5 = conn.prepareStatement("SELECT * FROM sistema where id_sistema=? and id_negocio=?");
			
			// remplazo de variables para prevenir  inyecciones SQL.
			ps5.setInt(1, a);
			ps5.setInt(2, m);
			// ejecución de consulta
			ResultSet rs2 = ps5.executeQuery();
		
			while(rs2.next())
				{
					cant2=cant2+1;
				}
			rs2.close();
			rs2.close();
			
			// consulta para verificar si el sistema existe y coincide con el idenficador de negocio.
			PreparedStatement ps6 = conn.prepareStatement("SELECT * FROM subsistema where id_subsistema=? and id_sistema=?");
						
			// remplazo de variables para prevenir  inyecciones SQL.
			ps6.setInt(1, n);
			ps6.setInt(2, a);
			// ejecución de consulta
			ResultSet rs3 = ps6.executeQuery();
					
			while(rs3.next())
				{
								cant3=cant3+1;
				}
				rs3.close();
				rs3.close();
			
			
			
			
			
			
			/* si el código de sistema no existe o bien no coincide con el identificador de negocio los valores no son insertados
			   y muestra mensaje de error de lo contrario es insertado en la bd.
			 */
			if(cant2 > 0 && cant3 > 0){
				
				
				/* si el nombre de caso y codigo de sistema coinciden con un registro en la bd los valores seran modificados de
					lo contrario ingresa un nuevo caso.
				 */
				if(cant == 0 ){
					
					// valores insertados en la bd.
					String seleccio = "INSERT INTO caso (id_sistema,nombre_caso,n_vp,aprobados,rechazados,alertas,h_inicio,f_inicio,h_fin,f_fin,f_h_ejecucion,id_subsistema) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
					PreparedStatement ps = conn.prepareStatement(seleccio);
						
							// las variables en formato StringBuffer son transformadas a valor entero.
							for(int i2 = 1;i2<=d.length();i2++)
							{
								contador = contador + 1;
							}
							for(int j2 = 1;j2<=e.length();j2++)
							{
								contador2 = contador2 + 1;
							}
							for(int x = 1;x<=f.length();x++)
							{
								contador3 = contador3 + 1;
							}
							
							// remplazo de variables para prevenir inyecciones SQL.
							ps.setInt(1, a);
							ps.setString(2, b);
							ps.setInt(3, c);
							ps.setInt(4, contador);
							ps.setInt(5, contador2);
							ps.setInt(6, contador3);
							ps.setString(7, g);
							ps.setString(8, h);
							ps.setString(9, i);
							ps.setString(10, j);
							ps.setString(11, l);
							ps.setInt(12, n);
							ps.executeUpdate();
							
					}
				else
					{
							
							//valores modificados en la bd.
							String seleccio = "UPDATE caso SET id_sistema=?,nombre_caso=?,n_vp=?,aprobados=?,rechazados=?,alertas=?,h_inicio=?,f_inicio=?,h_fin=?,f_fin=?,f_h_ejecucion=?,id_subsistema=? WHERE nombre_caso=? and id_sistema=? and id_subsistema=?";
							PreparedStatement ps = conn.prepareStatement(seleccio);
				
							// las variables en formato StringBuffer son transformadas a valor entero.
							for(int i2 = 1;i2<=d.length();i2++)
							{
								contador = contador + 1;
							}
							for(int j2 = 1;j2<=e.length();j2++)
							{
								contador2 = contador2 + 1;
							}
							for(int x = 1;x<=f.length();x++)
							{
								contador3 = contador3 + 1;
							}
							
							// remplazo de variables para prevenir  inyecciones SQL.
							ps.setInt(1, a);
							ps.setString(2, b);
							ps.setInt(3, c);
							ps.setInt(4, contador);
							ps.setInt(5, contador2);
							ps.setInt(6, contador3);
							ps.setString(7, g);
							ps.setString(8, h);
							ps.setString(9, i);
							ps.setString(10, j);
							ps.setString(11, l);
							ps.setInt(12, n);
							ps.setString(13, b);					
							ps.setInt(14, a);
							ps.setInt(15, n);
							ps.executeUpdate();
					}
				}
			else
				{
					System.out.println("El codigo de sistema, identificador de negocio o identificador de subsistema no existen en la bd o bien no coinciden entre si");
				}
	}
}