package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Chapa_Pintura;
import modelo.Electronica;
import modelo.Mecanica;
import modelo.Revision;
import modelo.Trabajo;


public class TallerDAO {
	ConexionBD conexionbd;

	public TallerDAO(ConexionBD conexion) {
		this.conexionbd = new ConexionBD();
	}
	
	public TallerDAO() {
		this.conexionbd = new ConexionBD();
	}

	public ConexionBD getConexionbd() {
		return conexionbd;
	}

	public void setConexionbd(ConexionBD conexionbd) {
		this.conexionbd = conexionbd;
	}

	public int obtenerProximoId() throws SQLException {
	    String sql = "SELECT MAX(codigo_trabajo) FROM trabajo";
	    try (Connection conexion = conexionbd.conectar();
	    		PreparedStatement ps = conexionbd.conectar().prepareStatement(sql);
	         ResultSet rs = ps.executeQuery(sql)) {

	        if (rs.next()) {
	            int maxId = rs.getInt(1);
	            return maxId + 1;
	        } else {
	            // Si no hay registros en la tabla, el siguiente id es 1
	            return 1;
	        }
	    }
	}
	
	public int obtenerProximoIdR() throws SQLException {
	    String sql = "SELECT MAX(codigo_reparacion) FROM reparacion";
	    try (Connection conexion = conexionbd.conectar();
	    		PreparedStatement ps = conexionbd.conectar().prepareStatement(sql);
	         ResultSet rs = ps.executeQuery(sql)) {

	        if (rs.next()) {
	            int maxId = rs.getInt(1);
	            return maxId + 1;
	        } else {
	            // Si no hay registros en la tabla, el siguiente id es 1
	            return 1;
	        }
	    }
	}
	public int obtenerProximoIdRV() throws SQLException {
	    String sql = "SELECT MAX(codigo_revision) FROM revision";
	    try (Connection conexion = conexionbd.conectar();
	    		PreparedStatement ps = conexionbd.conectar().prepareStatement(sql);
	         ResultSet rs = ps.executeQuery(sql)) {

	        if (rs.next()) {
	            int maxId = rs.getInt(1);
	            return maxId + 1;
	        } else {
	            // Si no hay registros en la tabla, el siguiente id es 1
	            return 1;
	        }
	    }
	}
	
	public void aniadirTrabajo(String tipoTrabajo, String descripcion, String nombreIngeniero, String nombreMaquina) {
	String consulta = "INSERT INTO trabajo (codigo_trabajo, tipo_trabajo, fecha_inicio, fecha_fin, descripcion, horas,finalizado) VALUES (?, ?, ?, ?, ?, ?, ?)";
	String fecha_inicio = "22-01-22";
	String fecha_fin = "25-03-22";
	boolean finalizado = false;
	
	int contador = 0;
    try {
    	contador = obtenerProximoId();
	
        PreparedStatement ps = conexionbd.conectar().prepareStatement(consulta);
        ps.setInt(1, contador++);
        ps.setString(2, tipoTrabajo);
        ps.setString(3, fecha_inicio);
        ps.setString(4, fecha_fin);
        ps.setString(5, descripcion);
        ps.setInt(6, 0);
        ps.setBoolean(7, finalizado);
        ps.executeUpdate();
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
    aniadirElectronica(tipoTrabajo, descripcion, nombreIngeniero, nombreMaquina);
}	
	
	public void aniadirTrabajoM(String tipoTrabajo, String descripcion) {
		String consulta = "INSERT INTO trabajo (codigo_trabajo, tipo_trabajo, fecha_inicio, fecha_fin, descripcion, horas,finalizado) VALUES (?, ?, ?, ?, ?, ?, ?)";
		String fecha_inicio = "22-01-22";
		String fecha_fin = "25-03-22";
		boolean finalizado = false;
		int contador = 0;
	    try {
	    	contador = obtenerProximoId();
	    	
	        PreparedStatement ps = conexionbd.conectar().prepareStatement(consulta);
	        ps.setInt(1, contador++);
	        ps.setString(2, tipoTrabajo);
	        ps.setString(3, fecha_inicio);
	        ps.setString(4, fecha_fin);
	        ps.setString(5, descripcion);
	        ps.setInt(6, 0);
	        ps.setBoolean(7, finalizado);
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    aniadirReparacion(tipoTrabajo, descripcion);
	}
	
	public Trabajo buscarTrabajoPorId(int id) {
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    Trabajo trabajo = null;

	    try {
	        conn = conexionbd.conectar();
	        stmt = conn.prepareStatement("SELECT * FROM trabajo WHERE codigo_trabajo = ?");
	        stmt.setInt(1, id);
	        rs = stmt.executeQuery();

	        if (rs.next()) {
	            String descripcion = rs.getString("descripcion");
	            if (rs.getString("tipo_trabajo").equalsIgnoreCase("Mecanica") || rs.getString("tipo_trabajo").equalsIgnoreCase("m") ) {
	                trabajo = new Mecanica(id, descripcion);
	            } else if (rs.getString("tipo_trabajo").equalsIgnoreCase("Chapa y pintura") || rs.getString("tipo_trabajo").equalsIgnoreCase("cp")) {
	                trabajo = new Chapa_Pintura(id, descripcion);
	            }else if(rs.getString("tipo_trabajo").equalsIgnoreCase("Electronica") || rs.getString("tipo_trabajo").equalsIgnoreCase("el")) {
	            	trabajo = new Electronica(id,descripcion);
	            }else if(rs.getString("tipo_trabajo").equalsIgnoreCase("Revision") || rs.getString("tipo_trabajo").equalsIgnoreCase("rs")) {
	            	trabajo = new Revision(id,descripcion);
	            }
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	    return trabajo;
	}
	
	public void aumentarHoras(int id,int horas) {

		    String sql = "UPDATE trabajo SET horas = ? WHERE codigo_trabajo = ?";
		    try (Connection conn = conexionbd.conectar();
		         PreparedStatement pstmt = conn.prepareStatement(sql)) {
		        pstmt.setInt(1, horas);
		        pstmt.setInt(2, id);
		        pstmt.executeUpdate();
		    } catch (SQLException e) {

		    }
		    
		}
	
	public void aumentarCostePiezas(int id,double precio) {

	    String sql = "UPDATE reparacion SET precio_piezas = ? WHERE codigo_trabajo = ?";
	    try (Connection conn = conexionbd.conectar();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setDouble(1, precio);
	        pstmt.setInt(2, id);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {

	    }
	    
	}
	
	public void aniadirReparacion(String tipoTrabajo, String descripcion) {
		int contador2;
		int contador1;
		int contador;
		try {
			contador2 = obtenerProximoIdRV();
			contador1 = obtenerProximoIdR();
			contador = obtenerProximoId();
    	if (tipoTrabajo.equalsIgnoreCase("mecanica") || tipoTrabajo.equalsIgnoreCase("m") || (tipoTrabajo.equalsIgnoreCase("chapa y pintura") || tipoTrabajo.equalsIgnoreCase("chapa") || tipoTrabajo.equalsIgnoreCase("pintura") || tipoTrabajo.equalsIgnoreCase("cp"))) {
            String consulta1 = "INSERT INTO reparacion (codigo_reparacion, codigo_trabajo, precio_piezas, nombre_ingeniero, nombre_maquina) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps1 = conexionbd.conectar().prepareStatement(consulta1);
            ps1.setInt(1,contador1);
            ps1.setInt(2, contador-1);
            ps1.setDouble(3, 0.0);
            ps1.setString(4, "NULL");
            ps1.setString(5, "NULL");
            ps1.executeUpdate(); 
        } else if (tipoTrabajo.equalsIgnoreCase("revision") || tipoTrabajo.equalsIgnoreCase("rv")) {
        	String consulta1 = "INSERT INTO revision (codigo_revision, codigo_trabajo, necesita_mantenimiento) VALUES (?, ?, ?)";
            PreparedStatement ps1 = conexionbd.conectar().prepareStatement(consulta1);
            ps1.setInt(1,contador2);
            ps1.setInt(2, contador-1);
            ps1.setBoolean(3, false);
            ps1.executeUpdate(); 
        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void aniadirElectronica(String tipoTrabajo, String descripcion, String nombreIngeniero, String nombreMaquina) {
		int contador;
		int contador1;
		
		try {
		contador1 = obtenerProximoIdR();
		contador = obtenerProximoId();
		if(tipoTrabajo.equalsIgnoreCase("electronica") || tipoTrabajo.equalsIgnoreCase("el")) {
			String consulta1 = "INSERT INTO reparacion (codigo_reparacion, codigo_trabajo, precio_piezas, nombre_ingeniero, nombre_maquina) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement ps1 = conexionbd.conectar().prepareStatement(consulta1);
            ps1.setInt(1,contador1);
            ps1.setInt(2, contador-1);
            ps1.setDouble(3, 0.0);
            ps1.setString(4, nombreIngeniero);
            ps1.setString(5, nombreMaquina);
            ps1.executeUpdate(); 
        }
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void finalizarTrabajo(int id) throws SQLException {
	    String query = "UPDATE trabajo SET finalizado = true WHERE codigo_trabajo = ?";
	    try (PreparedStatement statement = conexionbd.conectar().prepareStatement(query)) {
	        statement.setInt(1, id);
	        statement.executeUpdate();
	    }
	}
	
	public void borrarTrabajo(int id) throws SQLException {
		borrarReparacionPorTrabajo(id);
		String sql = "DELETE FROM trabajo WHERE codigo_trabajo = ?";
		try (PreparedStatement pstmt = conexionbd.conectar().prepareStatement(sql)) {
	        pstmt.setInt(1, id);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	       e.printStackTrace();
	    }
	}
	
	public void borrarReparacionPorTrabajo(int idTrabajo) throws SQLException {
	    String tabla = ""; // Esta variable se utilizará para almacenar el nombre de la tabla a la que hay que hacer la consulta

	    // Primero se comprueba si existen registros en la tabla "reparacion" asociados al trabajo a borrar
	    PreparedStatement stmt = conexionbd.conectar().prepareStatement("SELECT COUNT(*) FROM reparacion WHERE codigo_trabajo = ?");
	    stmt.setInt(1, idTrabajo);
	    ResultSet rs = stmt.executeQuery();
	    rs.next();
	    int numReparaciones = rs.getInt(1);
	    rs.close();
	    stmt.close();

	    if (numReparaciones > 0) {
	        tabla = "reparacion";
	    } else {
	        // Si no hay registros en la tabla "reparacion", se comprueba en la tabla "revision"
	        stmt = conexionbd.conectar().prepareStatement("SELECT COUNT(*) FROM revision WHERE codigo_trabajo = ?");
	        stmt.setInt(1, idTrabajo);
	        rs = stmt.executeQuery();
	        rs.next();
	        int numRevisiones = rs.getInt(1);
	        rs.close();
	        stmt.close();

	        if (numRevisiones > 0) {
	            tabla = "revision";
	        }
	    }

	    // Si se ha encontrado una tabla asociada al trabajo a borrar, se borran los registros correspondientes
	    if (!tabla.isEmpty()) {
	        stmt = conexionbd.conectar().prepareStatement("DELETE FROM " + tabla + " WHERE codigo_trabajo = ?");
	        stmt.setInt(1, idTrabajo);
	        stmt.executeUpdate();
	        stmt.close();
	    }
	}
	
	
}