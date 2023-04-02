
public interface TallerInterface {
	
	/*
	 * Devuelve: 
	 * - Codigo del trabajo que se crea (de modo autom�tico). 
	 * - 1 en caso de que el codigo del trabajo sea incorrecto y no se haya podido a�adir el trabajo.
	 */
	public int anadirTrabajo(String tipoTrabajo, String descripcion, String nombreIngeniero, String nombreMaquina);
	
	public int anadirTrabajoM(String tipoTrabajo, String descripcion);
	/* Valores devueltos: 
		 * 		0 El numero de horas es negativo 
		 * 		-1 No existe un trabajo con ese Id 
		 * 		-2 El trabajo ya esta finalizado 
		 * 		1 Horas aumentadas de manera satisfactoria
		 */
	public int aumentarHoras(int id, int horas);
	
	/*
	 * Valores devueltos: 
	 * 		-1 No es una reparacion 
	 * 		-2 Esta finalizado 
	 * 		-3 No existe un trabajo con ese id. 
	 * 		0 Coste de piezas es negativo
	 * 		1 Coste de piezas aumentado satisfactoriamente
	 */
	public int aumentarCostePiezas(int id, double precio);
	
	/*
	 * Valores devueltos: 
	 * 		-1 No existe ningun trabajo con ese codigo 
	 * 		0 El trabajo ya esta finalizado 
	 * 		1 El trabajo ha podido ser finalizado satisfactoriamente
	 */
	public int finalizarTrabajo(int id);
	
	public String muestraTrabajo(int id);

}
