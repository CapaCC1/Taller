package modelo;

import java.sql.SQLException;
import dao.TrabajoDAO;

public class Chapa_Pintura extends Reparacion {

	static final double MULTIPLICADOR_CP = 1.3;
	static final int PRECIO_FIJO_CP= 30;
	private TrabajoDAO trabajoDAO;
	
	
	public Chapa_Pintura(String descripcion) {
		super(descripcion);
		this.trabajoDAO = new TrabajoDAO();
	}
	
	public Chapa_Pintura(int id,String descripcion) {
		super(id,descripcion);
		this.trabajoDAO = new TrabajoDAO();
	}
	

	public TrabajoDAO getTrabajoDAO() {
		return trabajoDAO;
	}

	public void setTrabajoDAO(TrabajoDAO trabajoDAO) {
		this.trabajoDAO = trabajoDAO;
	}

	public static double getPreciocp() {
		return MULTIPLICADOR_CP;
	}
	
	public double calcularPrecio() {
		int id = this.getId();
		double precio = 0;
        try {
        	precio = trabajoDAO.obtenerHorasTrabajo(id) * PRECIO_FIJO_CP + trabajoDAO.obtenerPrecioPiezas(id) * MULTIPLICADOR_CP; 
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
		return info + "\nPrecio Chapa y Pintura: " + calcularPrecio() + " €";
	}
	
}