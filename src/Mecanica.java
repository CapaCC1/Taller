
public class Mecanica extends Reparacion {

	static final double PRECIO_R = 1.1;
	
	public Mecanica(int id, String descripcion) {
		super(id, descripcion);	
	}
	
	public Mecanica(String descripcion) {
		super(descripcion);
	}
	
	public static double getPrecioR() {
		return PRECIO_R;
	}

	public double calcularPrecio() {
        double precio = super.getHoras() * 30 + super.getPrecioPiezas() * PRECIO_R; 
        return precio;
    }
	
	@Override
	public String toString() {
		String info = super.toString();
		return info + "\nPrecio del Trabajo: " + calcularPrecio() + " €";
	}
}
