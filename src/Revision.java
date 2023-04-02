
public class Revision extends Trabajo {
	
	private boolean necesitaMantenimiento;
	
	public Revision(int id, String descripcion) {
		super(id, descripcion);
		this.necesitaMantenimiento = false;
	}
	
	public Revision(String descripcion) {
		super(descripcion);
		this.necesitaMantenimiento = false;
	}
	
	public boolean isNecesitaMantenimiento() {
		return necesitaMantenimiento;
	}

	public void setNecesitaMantenimiento(boolean necesitaMantenimiento) {
		this.necesitaMantenimiento = necesitaMantenimiento;
	}

	public double calcularPrecio() {
        double precio = super.getHoras() * 30 + 20; 
        return precio;
    }

	@Override
	public String toString() {
		String info = super.toString();
		return info + "\nPrecio Revision: " + calcularPrecio() + " €";
	}
	
	
	
}
