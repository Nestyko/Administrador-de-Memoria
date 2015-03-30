package AdmRAM;

import aux_classes.input_output.Print;

public class Proceso {
	private String name = "";
	protected int size;
	private boolean wait;
	private boolean error;
	private byte priority;
	/**
	 * Procces Pointer: Apunta a al comienzo de su direccion de memoria
	 */
	protected int memoryPT;
	
	
	/**
	 * Crea un proceso solo con su tamaño y su nombre
	 * @param size 
	 * @param name es el nombre del proceso
	 */
	public Proceso(int size , String name) {
		this.size = size;
		this.name = name;
	}

	
	/**
	 * Crea un proceso especificamente para un segmento de memoria
	 * @param size tamaño del proceso
	 * @param memoryPT direccion en memoria donde empieza el proceso
	 */
	protected Proceso(int size, int memoryPT){
		this.size = size;
		this.memoryPT = memoryPT;
		this.wait = false;
	}
	
	/**
	 * Crea un proceso
	 * @param size tamaño del proceso
	 * @param name es el nombre del proceso
	 */
	protected Proceso(int size, int memoryPT, String name){
		this.size = size;
		this.memoryPT = memoryPT;
		this.wait = false;
		this.name = name;
	}
	
	public void info(){
		Print.outSln("Proceso: " + name);
		Print.outSln("Tamaño: ");
	}


	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public boolean isWait() {
		return wait;
	}


	public void setWait(boolean wait) {
		this.wait = wait;
	}


	public boolean isError() {
		return error;
	}


	public void setError(boolean error) {
		this.error = error;
	}


	public byte getPriority() {
		return priority;
	}


	public void setPriority(byte priority) {
		this.priority = priority;
	}


	public int getMemoryPt() {
		return memoryPT;
	}


	public void setMemoryPT(int proccesPt) {
		this.memoryPT = proccesPt;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	
	

}
