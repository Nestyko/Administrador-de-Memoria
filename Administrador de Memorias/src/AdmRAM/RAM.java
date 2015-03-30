package AdmRAM;
import java.util.ArrayList;
import aux_classes.input_output.*;

public class RAM{
	public static int memory_size;
	public static final Proceso OS_PROCESS = new Proceso(256,"Sistema Operativo");
	
	public static boolean memory_exists = false;
	
	public static ArrayList<Proceso> procesos = new ArrayList<Proceso>();
	public static ArrayList<Segment> memory = new ArrayList<Segment>();
	
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
					do{
						memory_size = C.unsigned(C.in_int(("Cuanta memoria desea: ")));
						if(memory_size < OS_PROCESS.getSize()){
							Print.errorCen("La memoria no tiene espacio suficinete para almacenar el SO");
						}
					}while(memory_size < OS_PROCESS.getSize());
					Segment OS = new Segment(OS_PROCESS, (memory_size-OS_PROCESS.getSize()));
					memory.add(OS);
					memory.add(new Segment(memory_size-OS_PROCESS.getSize(),0));
					
					
					memory_exists = true;	
					opc = 0;
					continue;
				}//case 1
				case 2:{
					int pSize = C.unsigned(C.in_int("Ingrese el tamaño del proceso: "));
					String name = C.in_String("Ingrese el nombre del Proceso: ");
					Proceso nuevo = new Proceso(pSize, name);
					assingProcessFM(nuevo);
					
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
		"2.- Ingresar un Proceso",
		"9.- Generar Procesos aleatorios"
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
				Print.outSln("1.- Ir a comprar Memoria RAM");
				if(memory_exists){
					for (int i = 0; i < opciones.length; i++) {
						Print.outSln(opciones[i]);
					}
				}	
				Print.endl(2);
				Print.outSln("10.- Acerca del Programa");
				Print.endl(1);
				opc = C.in_byte("Seleccione una opcion: [  ]\b\b\b");
		return opc;
							

	}//menu
	/**
	 * Asigna un proceso en memoria y Verifica que el proceso quepa en la memoria
	 * @param process es el proceso a asignar en memoria
	 */
	public static void assingProcessFM(Proceso process){
		for (Segment segment : memory) {
			if((!segment.isBusy())&&(segment.getSize() < process.getSize())){
				divideSegment(segment, process);
				break;
			}
		}
	}
	
	/**
	 * Divide un segmento del ArrayList memory en dos partes una para el proceso y 
	 * otra para el espacio libre si es que lo hay.
	 * @param segment es el segmento a dividir
	 * @param process es el segmento a introducir en el segmento
	 */
	private static void divideSegment(Segment segment, Proceso process){
		int index = memory.indexOf(segment);
		if(index != -1){
			//memory.add(new Segment(process.size(),));
			memory.get(index).setProceso(process);
		}
		cleanMemory();
	}
	
	/**
	 * Remueve los segmentos que tengan un tamaño = 0
	 */
	private static void cleanMemory(){
		for (Segment segment : memory) {
			if(segment.getSize() == 0){
				memory.remove(segment);
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