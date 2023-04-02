import java.util.Scanner;

public class PruebaTaller {	
	
	
	
	
	public static void main(String[] args) {
		
		try (Scanner in1 = new Scanner(System.in)) {
			Taller taller = new Taller();
			while(true) {
				
			System.out.println("\n##-PRUEBA TALLER-##\n");
			System.out.println("------------------------\n");
			System.out.println("1. Registra Trabajo");
			System.out.println("2. Aumenta Horas");
			System.out.println("3. Aumenta Coste Piezas");
			System.out.println("4. Finaliza Trabajo");
			System.out.println("5. Muestra Trabajo");
			System.out.println("6. Borrar Trabajo");
			System.out.println("\n------------------------\n");
			System.out.print("Introduce una opcion: ");
			int posicion = in1.nextInt();
			switch(posicion) {
			case 1:
				System.out.print("Introduce el tipo de trabajo (Mecanica ,Chapa y Pintura, Revision o Electronica)-> ");
				in1.nextLine();
				String tipoTrabajo = in1.nextLine();
				System.out.print("Introduce una descripcion-> ");
				String descripcion = in1.nextLine();
				if(tipoTrabajo.equalsIgnoreCase("el") || tipoTrabajo.equalsIgnoreCase("electronica")) {
					System.out.print("Introduce el Nombre del Ingeniero-> ");
					String nombreIngeniero = in1.nextLine();
					System.out.print("Introduce el Nombre de la Maquina de Diagnostico-> ");
					String nombreMaquina = in1.nextLine();
					int idGenerado = taller.anadirTrabajo(tipoTrabajo, descripcion, nombreIngeniero, nombreMaquina);
					System.out.println("\nEl ID del trabajo añadido es: " + idGenerado);
				}else {		
					int idGenerado = taller.anadirTrabajoM(tipoTrabajo, descripcion);
				if(idGenerado == -1) {
					System.out.println("ERROR, Trabajo Inexistente!");
				}else {
					System.out.println("\nTrabajo añadido correctamente");
					System.out.println("\nEl ID del trabajo añadido es: " + idGenerado);
				}
				}
				break;
			
			case 2:
				System.out.print("Introduce el ID del trabajo-> ");
				int id = in1.nextInt();
				System.out.print("Introduce las horas a agregar-> ");
				int horas = in1.nextInt();
				int idError = taller.aumentarHoras(id, horas);
				if(idError == -1) {
					System.out.println("\nERROR, Trabajo Inexistente!");
				}else if(idError == -2) {
					System.out.println("\nERROR, El trabajo ha sido FINALIZADO!");
				}else if(idError == 1) {
					System.out.println("\nHoras aumentadas con EXITO!");
				}else if(idError == 0){
					System.out.println("ERROR, El numero de horas es negativo!");
				}
				break;
			case 3:
				System.out.print("Introduce el ID del trabajo-> ");
				id = in1.nextInt();
				System.out.print("Introduce el coste de las piezas-> ");
				int coste = in1.nextInt();
				idError = taller.aumentarCostePiezas(id, coste);
				if(idError == -1) {
					System.out.println("\nERROR, No es una REPARACION!");
				}else if(idError == -2) {
					System.out.println("\nERROR, El trabajo ha sido FINALIZADO!");
				}else if(idError == -3) {
					System.out.println("\nERROR, Trabajo Inexistente!");
				}else if(idError == 1) {
					System.out.println("\nCoste de piezas aumentado con EXITO!");
				}else if(idError == 0){
					System.out.println("ERROR, El numero de horas es negativo!");
				}
				break;
				
			case 4:
				System.out.print("Introduce el ID del trabajo-> ");
				id = in1.nextInt();
				idError = taller.finalizarTrabajo(id);
				if(idError == -1) {
					System.out.println("\nERROR, Trabajo Inexistente!");
				}else if(idError == 1) {
					System.out.println("\nTrabajo finalizado con EXITO!");
				}else if(idError == 0){
					System.out.println("ERROR, El trabajo ya ha sido finalizado!");
				}
				break;
			case 5:
				System.out.print("\nIntroduce el ID del trabajo que quieras ver: ");
				int idBuscar = in1.nextInt();
				System.out.println("");
				System.out.println(taller.muestraTrabajo(idBuscar));
				break;
			case 6:
				System.out.print("\nIntroduce el ID del trabajo que quieres BORRAR: ");
				id = in1.nextInt();
				idError = taller.borrarTrabajo(id);
				if(idError == -1) {
					System.out.println("\nERROR, Trabajo Inexistente!");
				}else if(idError == 0) {
					System.out.println("\nTrabajado Borrado con EXITO!");
				}
				break;
			}
			
			}
		}
		
	}
}
