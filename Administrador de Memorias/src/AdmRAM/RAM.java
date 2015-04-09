package AdmRAM;
import java.util.ArrayList;
import aux_classes.input_output.*;

public class RAM{
	
	public static int busy_memory;
	public static boolean memory_exists = false;
	public static int memory_size;
	public static int maximum_available;
	public static final Proceso OS = new Proceso(200);
	public static ArrayList<Proceso> procesos = new ArrayList<Proceso>();
	public static ArrayList<Segment> memory = new ArrayList<>();
	public static byte method = 0;

	public static void main(String[] args){

			byte opc;
			do{
			Print.cls();
			opc = menu();
			Print.cls();
			switch(opc){
				case 0:{
					opc = 1;
					continue;

					}
				case 1:{
					if(memory_exists){
						Print.errorCen("La memoria ya ha sido asignada y no puede ser modificada");
						opc = 0;
						break;
					}
					memory_size = C.unsigned(C.in_int(("Cuanta memoria desea: ")));
					while(memory_size <= 256){
						Print.errorCen("El sistema operativo necesita por lo menos 256 para funcionar");
						memory_size = C.unsigned(C.in_int(("Cuanta memoria desea: ")));
					}
					while(memory_size > 1024){
						Print.errorCen("La maxima cantidad de memoria es 1024");
						memory_size = C.unsigned(C.in_int(("Cuanta memoria desea: ")));
					}
					Segment OS_seg = new Segment(OS, 0);
					memory.add(OS_seg);
					Segment free = new Segment((memory_size-OS.getSize()), OS.getSize());
					maximum_available = free.getSize();
					memory.add(free);
					
					memory_exists = true;	
					opc = 0;
					continue;
				}//case 1
				case 2:{
					if((method == 0) || (method == 1)){
						method = 1;
						int pSize = C.unsigned(C.in_int("Ingrese el tama�o del proceso: "));
						Proceso nuevo = new Proceso(pSize);
						if(assingProcessFM(nuevo)){
							break;
						}else{
							nuevo.setWait(true);
							procesos.add(nuevo);
						}
					}else{
						Print.errorCen("          El metodo MEJOR AJUSTE ya fue establecido."
								+ "\n          Por favor vuelva al menu y presione 3 para seguir ingresando");
					}
					//procesos.add(nuevo);
					break;
				}
				case 3:{
					if((method == 0) || (method == 2)){
						int pSize = C.unsigned(C.in_int("Ingrese el tama�o del proceso: "));
						Proceso nuevo = new Proceso(pSize);
						assingProcessBM(nuevo);
					}else{
						Print.errorCen("El metodo PRIMER AJUSTE ya fue establecido."
								+ "\n          Por favor vuelva al menu y presione 2 para seguir ingresando");
					}
					
					
					break;
					
				}
				case 4:{
					//new GMemory(memory);
					RAMinfo();
					Print.endl(1);
					Print.outSln("Procesos en Espera: ");
					processWaitInfo();
					Print.pausa("PRESIONE ENTER PARA CONTINUAR");
					break;
				}
				
				case 10:{
					acerca_de();
					Print.pausa("PRESIONE ENTER PARA CONTINUAR");
					opc = 0;
					continue;
					}//case 10
				default:{
					Print.errorCen("Seleccion Invalida");
					opc = 0;
					continue;
				}//default
			}//switch
			
			//C.espacio(10);
			//opc = C.in_byte("Si desea Salir del programa Presione 1: ");

		}while(opc != 1);


	}//main


public static byte menu(){
	
	String[] opciones = {
		"2.- Ingresar un Proceso y Asignarlo por Primer Ajuste",
		"3.- Ingresar un Proceso y Asignarlo por Mejor Ajuste",
		"4.- Mostrar la memoria RAM"
	};
   byte opc;
			Print.separador();
				Print.outCenln("Administrador de Memoria");
				Print.endl(1);
				Print.separador();
				Print.espacio(40);
				Print.outln("Cantidad de procesos: " + procesos.size());
				Print.endl(1);
				Print.outSln("0.- Salir del Programa");
				
				if(memory_exists){
					if(method == 0){
						Print.outSln("2.- Ingresar un Proceso y Asignarlo por Primer Ajuste");
						Print.outSln("3.- Ingresar un Proceso y Asignarlo por Mejor Ajuste");
					}else if(method == 1){
						Print.outSln("2.- Ingresar un Proceso y Asignarlo por Primer Ajuste");
					}else if(method == 2){
						Print.outSln("3.- Ingresar un Proceso y Asignarlo por Mejor Ajuste");
					}
					Print.outSln("4.- Mostrar la memoria RAM");
				}else{
					Print.outSln("1.- Ir a comprar Memoria RAM");
				}
				Print.endl(2);
				Print.outSln("10.- Acerca del Programa");
				Print.endl(1);
				opc = C.in_byte("Seleccione una opcion: [  ]\b\b\b");
		return opc;
							

	}//menu
	
	public static void processWaitInfo(){
		for(Proceso process: procesos){
			if(process.isWait()){
				Print.outSln("Proceso " + procesos.indexOf(process));
				process.info();
			}
		}
	}

	public static void RAMinfo(){
		for (Segment segment : memory) {
			Print.outSln("Segmento " + memory.indexOf(segment));
			segment.info();
		}
	}
	
	
	/**
	 * Asigna el proceso por el metodo de Primer Ajuste
	 * @param process
	 * @return true si logra asignar el proceso en memoria
	 */
	public static boolean assingProcessFM(Proceso process){
		for (Segment segment : memory) {
			if((segment.isBusy() == false) && (process.getSize() <= segment.getSize())){
				int index = memory.indexOf(segment);
				int size = memory.get(index).getSize();
				segment.setProcess(process);
				if(segment.getSize() < size){
					Segment nuevo = new Segment(size-segment.getSize(),
							memory.get(index).getMemoryPT()+memory.get(index).getSize());
					memory.add(index+1, nuevo);
				}
				cleanMemory();
				return true;
			}
		}
		if(excessMemory(process, true)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Asigna el proceso por el metodo de mejor ajuste
	 * @param process
	 * @return true si logra asignar el proceso en memoria
	 */
	public static boolean assingProcessBM(Proceso process){
		boolean match_found = false;
		int matchs = 0;
		int i = 0;
		for(Segment segment : memory){
			if((segment.isBusy() == false) && (process.getSize() <= segment.getSize())){
				matchs++;
			}
		}
		if(matchs == 1){
			assingProcessFM(process);
			return true;
		}else{
		int[][] match = new int[matchs][2];
		for (Segment segment : memory) {
			if((segment.isBusy() == false) && (process.getSize() <= segment.getSize())){
				match_found = true;
				match[i][0] =segment.getSize()-process.getSize();
				match[i][1] = memory.indexOf(segment);
				i++;
				}
			}
		
		if(match_found){
			int winner_index = match[0][1];
			for(int s = 0; s < (matchs-1) ; s++){
				if(match[s][0] > match[s+1][0]){
					winner_index = match[s+1][1];
				}
			}
			int size = memory.get(winner_index).getSize();
			memory.get(winner_index).setProcess(process);
			if(memory.get(winner_index).getSize() < size){
				Segment nuevo = new Segment(size-memory.get(winner_index).getSize(),
						memory.get(winner_index).getMemoryPT()+memory.get(winner_index).getSize());
				memory.add((winner_index+1), nuevo);
				return true;
			}
		}else{
			if(excessMemory(process, false)){
				return true;
			}else{
				return false;
			}
		}
		}
		return true;
		
	}
	
	public static boolean excessMemory(Proceso process, boolean type){
		if(maximum_available < process.getSize()){
			Print.errorCen("El proceso es mas grande que la memoria");
			return false;
		}
		Print.errorCen("El proceso no cabe en la memoria");
		char ans = C.in_char("Desea poner en espera algun proceso de la RAM para liberar espacio?  y/n" );
		if((ans == 'y') || (ans == 'Y')){
			RAMinfo();
			byte seg = C.in_byte("Seleccione el numero del segmento a poner en espera: ");
			while((seg < 1) || (seg > memory.size())){
				Print.errorCen("Segmneto invalido vuelva a intentar");
				Print.cls();
				RAMinfo();
				seg = C.in_byte("Seleccione el numero del segmento a poner en espera: ");
			}
			dealocateProcess(seg);
			if(type){
				if(assingProcessFM(process)){
				return true;
			}else{
				return false;
			}
			}else{
				if(assingProcessBM(process)){
					return true;
				}else{
					return false;
				}
			}
			
		}
		return false;
	}
	
	public static boolean enoughMemory(Proceso process){
		for (Segment segment : memory) {
			if((segment.isBusy() == false) && (process.getSize() <= segment.getSize())){
				return true;
			}
		}
		return false;
	}
	
	public static void dealocateProcess(int segment_position){
		Proceso retrieve = memory.get(segment_position).getProcess();
		retrieve.setWait(true);
		memory.get(segment_position).setProcess(null);
		procesos.add(retrieve);
		cleanMemory();
	}
	
	public static void cleanMemory(){
		for (int i = 0; i < (memory.size()-1); i++) {
			if((!memory.get(i).isBusy()) && (!memory.get(i+1).isBusy())){
				memory.get(i).setSize(memory.get(i).getSize()+memory.get(i+1).getSize());
				memory.remove(i+1);
				i--;
			}
		}
	}
	
	public static final void acerca_de(){
	for(int i = 0;i<15;i++){
		System.out.println("\n\n");
		}
	System.out.print("          ");
	System.out.println("Administrador de Procesos");
	System.out.println("\n\n");
	System.out.print("          ");
	System.out.println("Programa realizado por: Nestor Luis Tobon Arrieta");
	System.out.print("          ");
	System.out.println("Cedula de Identidad: 23.863.118");
	System.out.print("          ");
	System.out.println("Seccion N-511");
	System.out.println("\n\n");
	System.out.print("          ");
	System.out.println("Repositorios de mis otros programas disponibles en:\nURL: https://github.com/Nestyko");
	System.out.print("\n");
	
	}//acerca_de

}