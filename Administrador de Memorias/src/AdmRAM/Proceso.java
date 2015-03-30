package AdmRAM;

public class Proceso {
	private int size;
	private boolean wait;
	private boolean error;
	private byte priority;
	/**
	 * Procces Pointer: Apunta a al comienzo de su direccion de memoria
	 */
	private int proccesPt;
	
	
	/**
	 * Crea un proceso solo con su tamaño
	 * @param size 
	 */
	public Proceso(int size) {
		this.size = size;
	}


	/**
	 * Crea un proceso y le indica si esta esperando y donde se ubica en memoria
	 * @param size
	 * @param wait estado del proceso
	 * @param proccesPt direccion en memoria
	 */
	public Proceso(int size, boolean wait, int proccesPt) {
		this.size = size;
		this.wait = wait;
		this.proccesPt = proccesPt;
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


	public int getProccesPt() {
		return proccesPt;
	}


	public void setProccesPt(int proccesPt) {
		this.proccesPt = proccesPt;
	}
	
	

}
