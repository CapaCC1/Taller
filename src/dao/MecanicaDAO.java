package dao;


public class MecanicaDAO {
	private ConexionBD conexionbd;

	public MecanicaDAO(ConexionBD conexionbd) {
		this.conexionbd = new ConexionBD();
	}

	public ConexionBD getConexionbd() {
		return conexionbd;
	}

	public void setConexionbd(ConexionBD conexionbd) {
		this.conexionbd = conexionbd;
	}

	}
	
	

