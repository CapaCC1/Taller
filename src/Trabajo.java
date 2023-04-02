
public abstract class Trabajo {
	
	private int id;
	private String descripcion;
	private int horas;
	private boolean finalizado;
	
	public Trabajo(int id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
		this.horas = 0;
		this.finalizado = false;
	}
	
	public Trabajo(int id, String descripcion, int horas) {
		this.id = id;
		this.descripcion = descripcion;
		this.horas = 0;
		this.finalizado = false;
	}
	
	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	public Trabajo(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}
	
	public boolean aumentarHoras(int horasAniadir) {
	    if (!finalizado) {
	        horas += horasAniadir;
	        return true;
	    }
	    return false;
	}
	
	@Override
	public String toString() {
		return "ID Trabajo: " + id + "\nDescripcion: " + descripcion + "\nDuracion: " + horas + " horas";
	}
	
	
	
}
