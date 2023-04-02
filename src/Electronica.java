
public class Electronica extends Reparacion{
	
	
	private String nombreIngeniero;
	private String nombreMaquina;
	static final int COSTE_MAQUINA = 30;
	static final int COSTE_INGENIERO = 70;
	
	public Electronica(int id, String descripcion, String nombreIngeniero, String nombreMaquina) {
		super(id, descripcion);
		this.nombreIngeniero = nombreIngeniero;
		this.nombreMaquina = nombreMaquina;
	}
	
	public Electronica(int id, String descripcion) {
		super(id, descripcion);
		
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
        double precio = super.getPrecioPiezas() + COSTE_MAQUINA + COSTE_INGENIERO; 
        return precio;
    }
	
	@Override
	public String toString() {
		String info = super.toString();
		return info + "\nPrecio del Trabajo de Electronica: " + calcularPrecio() + " €";
	}
	
}
