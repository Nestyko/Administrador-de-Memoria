package AdmRAM;

import aux_classes.input_output.Print;



public class Segment extends Proceso {
	
	/**
	 * Memory Pointer
	 */
	private boolean busy;
	private Proceso process;
	
	
	public Segment(int size, int memoryPT){
		super(size, memoryPT);
		busy = false;
		process = null;
	}
	
	public Segment(Proceso process, int memoryPT){
		super(process.getSize(), memoryPT, process.getName());
		this.busy = true;
		super.setWait(false);
	}
	
	
	/**
	 * Asigna un segmento a la memoria
	 * @param size Es el tamaño del segmento
	 * @param memoryPT es el lugar en memoria donde comienza el segmento
	 * @param busy es el estado del segmento true: ocupado por un proceso
	 * @param name es el nombre del proceso
	 */
	public Segment(int size, int memoryPT, Proceso process, String name) {
		
		super(size, memoryPT, name);
		if(process != null){
			busy = true;
			super.setWait(false);
		}else{
			busy = false;
			
		}
		this.process = process;
	}
	
	/**
	 * Asigna un segmento de memoria exclusivamente para un proceso
	 * @param memoryPT es le 
	 * @param newProcess es el proceso a agregar
	 */
	public Segment(int memoryPT, Proceso newProcess) {
		
		super(newProcess.size, memoryPT);
		busy = true;
		this.process = newProcess;
		super.setWait(false);
	}
	
	public void info(){
		Print.outSln("Capacidad: " + size);
		Print.outSln("Ocupado: ");
		Print.out(busy ? "Si":"No");
		if(busy){
			super.info();
		}
		
	}


	public final Proceso getProceso(){
		return this.process;
	}
	
	public void setProceso(Proceso process){
		if(!busy){
			this.process = process;
			super.setName(process.getName());
			super.setSize(process.getSize());
			this.busy = true;
		}
		else{
			System.err.println("El Proceso esta ocupado, por lo tanto no se le puede asignar un nuevo proceso");
		}
			
	}
	
	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public int getMemoryPT() {
		return memoryPT;
	}


	public void setMemoryPT(int memoryPT) {
		this.memoryPT = memoryPT;
	}


	public boolean isBusy() {
		return busy;
	}

	
	
	
	

}
