package modelo;

import java.sql.SQLException;

import dao.TrabajoDAO;

public class Mecanica extends Reparacion {

	static final double MULTIPLICADOR_MC = 1.1;
	static final int PRECIO_FIJO_MC = 30;
	private TrabajoDAO trabajoDAO;
	
	public Mecanica(int id, String descripcion) {
		super(id, descripcion);	
		this.trabajoDAO = new TrabajoDAO();
	}
	
	public Mecanica(String descripcion) {
		super(descripcion);
		this.trabajoDAO = new TrabajoDAO();
	}
	

	public TrabajoDAO getTrabajoDAO() {
		return trabajoDAO;
	}

	public void setTrabajoDAO(TrabajoDAO trabajoDAO) {
		this.trabajoDAO = trabajoDAO;
	}

	public static double getPrecioR() {
		return MULTIPLICADOR_MC;
	}	 
	
	public double calcularPrecio() {
		int id = this.getId();
		double precio = 0;
        try {
        	precio = trabajoDAO.obtenerHorasTrabajo(id) * PRECIO_FIJO_MC + trabajoDAO.obtenerPrecioPiezas(id) * MULTIPLICADOR_MC; 
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
		
		return info + "\nPrecio del Trabajo: " +calcularPrecio() + " €";
	}
}
