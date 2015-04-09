package AdmRAM;

import aux_classes.input_output.Print;

public class Proceso {
	private int size;
	private boolean wait;
	
	
	
	/**
	 * Crea un Proceso 
	 * @param size capacidad del proceso
	 */
	public Proceso(int size) {
		this.size = size;
	}
	
	public void info(){
		Print.outSln("Capacidad: " + size);
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
	
	
	

}
