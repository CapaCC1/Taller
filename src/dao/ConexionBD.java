package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
		
		private static final String CONTROLADOR = "com.mysql.cj.jdbc.Driver";
		private static final String URL = "";
		private static final String USUARIO = "usuario";
		private static final String CLAVE = "usuario";
		
		static {
			try {
				Class.forName(CONTROLADOR);
				
			} catch (ClassNotFoundException e) {
				System.out.println("ERROR al cargar el controlador");
				e.printStackTrace();
				
			}
		}
		
		public Connection conectar() {
			Connection conexion = null;
			try {
				conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			
				
			}catch(SQLException e) {
				System.out.println("ERROR en la conexion");
				e.printStackTrace();
			}
			return conexion;
		}
		
	}

