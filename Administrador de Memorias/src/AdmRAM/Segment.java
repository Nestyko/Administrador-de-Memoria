package AdmRAM;
import aux_classes.input_output.Print;

public class Segment {
	private int memoryPT;
	private int size;
	private Proceso process;
	private boolean busy;
	
	/**
	 * Crea un nuevo segmento en memoria para el proceso determinado
	 * @param memoryPT Memory Pointer: es la direccion de memoria en donde empieza el segmento
	 * @param process es el proceso que contiene ese segmento. Sera null si no continen ningun proceso
	 */
	public Segment(Proceso process, int memoryPT){
		this.process = process;
		this.size = process.getSize();
		this.memoryPT = memoryPT;
		busy = true;
		process.setWait(false);

	}
	
	/**
	 * Crea un segmento libre, es decir, sin proceso
	 * @param size es el tama�o del segmento
	 * @param memoryPT Memory Pointer: es la direccion de memoria en donde empieza el segmento
	 */
	public Segment(int size, int memoryPT){
		this.size = size;
		this.memoryPT = memoryPT;
		busy = false;
		process = null;
	}
	
	public void info(){
		if(busy){
			printHyphen(memoryPT+"");
			Print.outSln("| Nombre: " + process.getName());
			Print.outSln("| Tama�o: " + size);
			printHyphen((memoryPT+size)+"");
		}else{
			printHyphen(memoryPT+"");
			Print.outSln("| Segmento Libre");
			Print.outSln("| Tama�o: " + size);
			printHyphen((memoryPT+size)+"");
		}
	}
	
	public void printHyphen(String line){
		int length = 0;
		length = ((line)+"").length();
		length = 50-length;
		Print.outS(line+"");
		for(int i = 0;i< length;i++){
			Print.out("-");
		}
		System.out.println();
	}
	
	public int getMemoryPT(){
		return memoryPT;
	}
	
	public void setMemoryPT(int memoryPT){
		this.memoryPT = memoryPT;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Proceso getProcess() {
		return process;
	}

	public void setProcess(Proceso process) {
		this.process = process;
		if(process != null){
			busy = true;
			process.setWait(false);
			this.size = process.getSize();
		}else{
			busy= false;
		}
		
	}

	public boolean isBusy() {
		return busy;
	}
	
}
