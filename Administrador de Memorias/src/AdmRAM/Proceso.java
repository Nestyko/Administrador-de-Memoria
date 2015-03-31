package AdmRAM;

import aux_classes.input_output.Print;

public class Proceso {
	private int size;
	private String name;
	private boolean wait;
	
	
	
	/**
	 * Crea un Proceso 
	 * @param size tamaño del proceso
	 * @param name nombre del proceso
	 */
	public Proceso(int size, String name) {
		this.size = size;
		this.name = name;
	}
	
	public void info(){
		Print.outSln("Nombre: " + name);
		Print.outSln("Tamaño:_" + size);
	}
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isWait() {
		return wait;
	}
	public void setWait(boolean wait) {
		this.wait = wait;
	}
	
	
	

}
