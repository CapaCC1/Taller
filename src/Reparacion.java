
public abstract class Reparacion extends Trabajo {
			
	private double precioPiezas;
	
	public Reparacion(int id, String descripcion) {
		super(id, descripcion);
		this.precioPiezas = 0;
		
	}
	public Reparacion(String descripcion) {
		super(descripcion);
		this.precioPiezas = 0;
	}

	public double getPrecioPiezas() {
		return precioPiezas;
	}

	public void setPrecioPiezas(double precioPiezas) {
		this.precioPiezas = precioPiezas;
	}

	public boolean aumentarCostePiezas(double costeAgregar) {
		
	    if (!isFinalizado()) {
	        precioPiezas += costeAgregar;
	        return true;
	    }
	    return false;
	}
	
	@Override
	public String toString() {
		String info = super.toString();
		return info;
	}
}
