package modelo;

import java.sql.SQLException;

import dao.TrabajoDAO;

public class Electronica extends Reparacion{
	
	
	private String nombreIngeniero;
	private String nombreMaquina;
	static final int COSTE_MAQUINA = 30;
	static final int COSTE_INGENIERO = 70;
	private TrabajoDAO trabajoDAO;
	
	public Electronica(int id, String descripcion, String nombreIngeniero, String nombreMaquina) {
		super(id, descripcion);
		this.nombreIngeniero = nombreIngeniero;
		this.nombreMaquina = nombreMaquina;
		this.trabajoDAO = new TrabajoDAO();
	}
	
	public Electronica(String descripcion, String nombreIngeniero, String nombreMaquina) {
		super(descripcion);
		this.nombreIngeniero = nombreIngeniero;
		this.nombreMaquina = nombreMaquina;
		this.trabajoDAO = new TrabajoDAO();
		
	}
	
	public Electronica(String descripcion) {
		super(descripcion);
		this.trabajoDAO = new TrabajoDAO();
	}

	public Electronica(int id,String descripcion) {
		super(id,descripcion);
		this.trabajoDAO = new TrabajoDAO();
		
	}
	
	
	
	public TrabajoDAO getTrabajoDAO() {
		return trabajoDAO;
	}

	public void setTrabajoDAO(TrabajoDAO trabajoDAO) {
		this.trabajoDAO = trabajoDAO;
	}

	public String getNombreIngeniero() {
		return nombreIngeniero;
	}

	public void setNombreIngeniero(String nombreIngeniero) {
		this.nombreIngeniero = nombreIngeniero;
	}

	public String getNombreMaquina() {
		return nombreMaquina;
	}

	public void setNombreMaquina(String nombreMaquina) {
		this.nombreMaquina = nombreMaquina;
	}

	public static int getCosteMaquina() {
		return COSTE_MAQUINA;
	}

	public static int getCosteIngeniero() {
		return COSTE_INGENIERO;
	}
	
	public double calcularPrecio() {
		int id = this.getId();
		double precio = 0;
        try {
        	precio = trabajoDAO.obtenerPrecioPiezas(id) + COSTE_MAQUINA + COSTE_INGENIERO; 
        	trabajoDAO.actualizarPrecioTrabajo(id,precio);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return precio;
    }
	
	@Override
	public String toString() {
		String info = super.toString();
		return info + "\nPrecio del Trabajo de Electronica: " + calcularPrecio() + " €";
	}
	
}
