import java.util.ArrayList;

public class Taller implements TallerInterface {
	
	private int contadorTrabajos;
	private ArrayList<Trabajo> trabajos;

	public Taller(ArrayList<Trabajo> trabajos, int contadorTrabajos) {
		this.trabajos = new ArrayList<>();
	}

	public Taller() {
		this.trabajos = new ArrayList<>();
	}
	public int getContadorTrabajos() {
		return contadorTrabajos;
	}

	public ArrayList<Trabajo> getTrabajos() {
		return trabajos;
	}
	
	public void setTrabajos(ArrayList<Trabajo> trabajos) {
		this.trabajos = trabajos;
	}
	
	 @Override
	    public int anadirTrabajo(String tipoTrabajo, String descripcion, String nombreIngeniero, String nombreMaquina) {
	        
	        Trabajo trabajo = null;
	        if(tipoTrabajo.equalsIgnoreCase("electronica") || tipoTrabajo.equalsIgnoreCase("el")) {
	        	trabajo = new Electronica(contadorTrabajos, descripcion, nombreIngeniero, nombreMaquina);
	        } else {
	            return -1;
	        }
	        trabajos.add(trabajo);
	        return contadorTrabajos++;
	    }

	 @Override
	    public int anadirTrabajoM(String tipoTrabajo, String descripcion) {
	        
	        Trabajo trabajo = null;
	        if (tipoTrabajo.equalsIgnoreCase("mecanica") || tipoTrabajo.equalsIgnoreCase("m")) {
	            trabajo = new Mecanica(contadorTrabajos, descripcion);
	        } else if (tipoTrabajo.equalsIgnoreCase("chapa y pintura") || tipoTrabajo.equalsIgnoreCase("chapa") || tipoTrabajo.equalsIgnoreCase("pintura") || tipoTrabajo.equalsIgnoreCase("cp")) {
	            trabajo = new Chapa_Pintura(contadorTrabajos, descripcion);
	        } else if (tipoTrabajo.equalsIgnoreCase("revision") || tipoTrabajo.equalsIgnoreCase("rv")) {
	            trabajo = new Revision(contadorTrabajos, descripcion);
	        }else if(tipoTrabajo.equalsIgnoreCase("electronica") || tipoTrabajo.equalsIgnoreCase("el")) {
	        	trabajo = new Electronica(contadorTrabajos, descripcion);
	        } else {
	            return -1;
	        }
	        trabajos.add(trabajo);
	        return contadorTrabajos++;
	    }
	 
	 public Trabajo buscarTrabajo(int id) {
		 
		 for (Trabajo trabajo : trabajos) {
			if(trabajo.getId() == id) {
				return trabajo;
			}
		}
		 return null;
	 }	 
	 
	 public int borrarTrabajo(int id) {
		 
		 Trabajo trabajo = buscarTrabajo(id);
		 
		 if(trabajo == null) {
			 return -1;
		 }
		 
		 trabajos.remove(id);
		 return 0;
	 }
	 
	 public int aumentarHoras(int id, int horas) {
		    Trabajo trabajo = buscarTrabajo(id); // buscar trabajo por su id
		    
		    if (trabajo == null) {
		        return -1; 
		    }
		    if (trabajo.isFinalizado()) {
		        return -2;
		    }
		    if(horas < 0) {
		    	return 0;
		    }
		    trabajo.aumentarHoras(horas);
		    return 1;
		}

	@Override
	public int aumentarCostePiezas(int id, double precio) {
		Trabajo trabajo = buscarTrabajo(id);
		
		if(trabajo == null) {
			return -3;
		}if(trabajo.isFinalizado()) {
			return -2;
		}if(precio < 0) {
			return 0;
		}
		
		if(trabajo instanceof Reparacion) {
			Reparacion reparacion = (Reparacion) trabajo;
			if(!reparacion.isFinalizado()) {
				reparacion.aumentarCostePiezas(precio);
				return 1;
			}
		}else {	
		}
		
		return -1;
	}

	@Override
	public int finalizarTrabajo(int id) {
		Trabajo trabajo = buscarTrabajo(id);
		
		if(trabajo == null) {
			return -1;
		}if(trabajo.isFinalizado()) {
			return 0;
		}if(!trabajo.isFinalizado()) {
			trabajo.setFinalizado(true);
		}
		return 1;
	}

	@Override
	public String muestraTrabajo(int id) {
		String resultado = "";
		Trabajo trabajo = buscarTrabajo(id);
		if(trabajo != null)
		{
			resultado = trabajo.toString();
		}else {
			resultado += "Trabajo Inexistente";
		}
		return resultado;
		
	}
	@Override
	public String toString() {
		return trabajos.toString();
	}
	
	
}
