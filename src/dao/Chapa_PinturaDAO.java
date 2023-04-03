package dao;

public class Chapa_PinturaDAO {
	private ConexionBD conexionbd;

	public Chapa_PinturaDAO(ConexionBD conexionbd) {
		this.conexionbd = new ConexionBD();
	}
	
	public Chapa_PinturaDAO() {
		this.conexionbd = new ConexionBD();
	}

	public ConexionBD getConexionbd() {
		return conexionbd;
	}

	public void setConexionbd(ConexionBD conexionbd) {
		this.conexionbd = conexionbd;
	}
	
}
