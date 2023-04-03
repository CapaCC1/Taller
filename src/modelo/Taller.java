package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.TallerDAO;

public class Taller implements TallerInterface {
	
	private int contadorTrabajos;
	private ArrayList<Trabajo> trabajos;
	private TallerDAO tallerDAO;

	public Taller(ArrayList<Trabajo> trabajos, int contadorTrabajos) {
		this.trabajos = new ArrayList<>();
		
	}

	public Taller() {
		this.trabajos = new ArrayList<>();
		this.tallerDAO = new TallerDAO();
		this.contadorTrabajos = retornarID();
	}
	
	public TallerDAO getTallerDAO() {
		return tallerDAO;
	}

	public void setTallerDAO(TallerDAO tallerDAO) {
		this.tallerDAO = tallerDAO;
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
	
	public int retornarID() {
		try {
			contadorTrabajos = tallerDAO.obtenerProximoId();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contadorTrabajos;
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
	        tallerDAO.aniadirTrabajo(tipoTrabajo, descripcion, nombreIngeniero, nombreMaquina);
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
	        tallerDAO.aniadirTrabajoM(tipoTrabajo, descripcion);
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
		 
		 Trabajo trabajo = tallerDAO.buscarTrabajoPorId(id);
		 
		 try {
		 if(trabajo == null) {
			 return -1;
		 }
		 
			tallerDAO.borrarTrabajo(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return 0;
	 }
	 
	 public int aumentarHoras(int id, int horas) {
		    Trabajo trabajo = tallerDAO.buscarTrabajoPorId(id); // buscar trabajo por su id
		    
		    if (trabajo == null) {
		        return -1; 
		    }
		    if (trabajo.isFinalizado()) {
		        return -2;
		    }
		    if(horas < 0) {
		    	return 0;
		    }
		    tallerDAO.aumentarHoras(id, horas);
		    return 1;
		}

	@Override
	public int aumentarCostePiezas(int id, double precio) {
		Trabajo trabajo = tallerDAO.buscarTrabajoPorId(id);
		
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
				tallerDAO.aumentarCostePiezas(id, precio);
				return 1;
			}
		}else {	
		}
		
		return -1;
	}

	@Override
	public int finalizarTrabajo(int id) {
		Trabajo trabajo = tallerDAO.buscarTrabajoPorId(id);
		try {
		
		if(trabajo == null) {
			return -1;
		}if(trabajo.isFinalizado()) {
			return 0;
		}if(!trabajo.isFinalizado()) {
			trabajo.setFinalizado(true);
				tallerDAO.finalizarTrabajo(id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public String muestraTrabajo(int id) {
		String resultado = "";
		Trabajo trabajillo = tallerDAO.buscarTrabajoPorId(id);
		
		if(trabajillo != null)
		{
			resultado = trabajillo.toString();
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