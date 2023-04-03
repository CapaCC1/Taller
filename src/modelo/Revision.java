package modelo;

import java.sql.SQLException;

import dao.TrabajoDAO;

public class Revision extends Trabajo {
	
	private boolean necesitaMantenimiento;
	private TrabajoDAO trabajoDAO;
	
	public Revision(int id, String descripcion) {
		super(id, descripcion);
		this.necesitaMantenimiento = false;
		this.trabajoDAO = new TrabajoDAO();
	}
	
	public Revision(String descripcion) {
		super(descripcion);
		this.necesitaMantenimiento = false;
		this.trabajoDAO = new TrabajoDAO();
	}
	
	public boolean isNecesitaMantenimiento() {
		return necesitaMantenimiento;
	}

	public void setNecesitaMantenimiento(boolean necesitaMantenimiento) {
		this.necesitaMantenimiento = necesitaMantenimiento;
	}
	
	public double calcularPrecio() {
		int id = this.getId();
		double precio = 0;
        try {
        	precio = trabajoDAO.obtenerHorasTrabajo(id) * 30 + 20; 
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
		return info + "\nPrecio Revision: " + calcularPrecio() + " €";
	}
	
	
	
}
