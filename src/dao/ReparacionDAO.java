package dao;


public class ReparacionDAO {
	private ConexionBD conexionbd;

	public ReparacionDAO(ConexionBD conexionbd) {
		super();
		this.conexionbd = new ConexionBD();
	}

	public ConexionBD getConexionbd() {
		return conexionbd;
	}

	public void setConexionbd(ConexionBD conexionbd) {
		this.conexionbd = conexionbd;
	}

	
	
}
