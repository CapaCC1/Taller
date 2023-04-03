package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrabajoDAO {
	private ConexionBD conexionbd;

	public TrabajoDAO(ConexionBD conexionbd) {
		this.conexionbd = new ConexionBD();
	}
	public TrabajoDAO() {
		this.conexionbd = new ConexionBD();
	}
	

	public ConexionBD getConexionbd() {
		return conexionbd;
	}

	public void setConexionbd(ConexionBD conexionbd) {
		this.conexionbd = conexionbd;
	}
	
	
	
	public double obtenerHorasTrabajo(int id) throws SQLException {
	    double horas = 0.0;
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    
	    try {
	        conn = conexionbd.conectar(); // Método que devuelve una conexión a la base de datos
	        String consulta = "SELECT horas FROM trabajo WHERE codigo_trabajo = ?";
	        stmt = conn.prepareStatement(consulta);
	        stmt.setInt(1, id);
	        rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            horas = rs.getDouble("horas");
	        }
	    } finally {
	        if (rs != null) {
	            rs.close();
	        }
	        
	        if (stmt != null) {
	            stmt.close();
	        }
	        
	        if (conn != null) {
	            conn.close();
	        }
	    }
	    
	    return horas;
	}
	
	public double obtenerPrecioPiezas(int id) throws SQLException {
	    double horas = 0.0;
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    
	    try {
	        conn = conexionbd.conectar(); // Método que devuelve una conexión a la base de datos
	        String consulta = "SELECT precio_piezas FROM reparacion WHERE codigo_trabajo = ?";
	        stmt = conn.prepareStatement(consulta);
	        stmt.setInt(1, id);
	        rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            horas = rs.getDouble("precio_piezas");
	        }
	    } finally {
	        if (rs != null) {
	            rs.close();
	        }
	        
	        if (stmt != null) {
	            stmt.close();
	        }
	        
	        if (conn != null) {
	            conn.close();
	        }
	    }
	    
	    return horas;
	}
	
	public void actualizarPrecioTrabajo(int codigoTrabajo,double precio) throws SQLException {

	    String consulta = "UPDATE trabajo SET precio_trabajo = ? WHERE codigo_trabajo = ?";
	    PreparedStatement ps = conexionbd.conectar().prepareStatement(consulta);
	    ps.setDouble(1, precio);
	    ps.setInt(2, codigoTrabajo);
	    ps.executeUpdate();
	}
	
	
	
}

