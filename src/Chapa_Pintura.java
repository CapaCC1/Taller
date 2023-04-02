
public class Chapa_Pintura extends Reparacion {

	static final double PRECIO_CP = 1.3;
	
	
	public Chapa_Pintura(int id, String descripcion) {
		super(id, descripcion);
	}

	public static double getPreciocp() {
		return PRECIO_CP;
	}
	
	public double calcularPrecio() {
        double precio = super.getHoras() * 30 + super.getPrecioPiezas() * PRECIO_CP; 
        return precio;
    }

	@Override
	public String toString() {
		String info = super.toString();
		return info + "\nPrecio Chapa y Pintura: " + calcularPrecio() + " €";
	}
	
}