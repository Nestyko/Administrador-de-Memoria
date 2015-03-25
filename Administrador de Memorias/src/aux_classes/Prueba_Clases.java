package aux_classes;



import aux_classes.input_output.C;
import aux_classes.input_output.Print;
import aux_classes.strings.StrFunction;



public class Prueba_Clases{



	
	public static void main (String[] args){

		
		
		while (true) {
			String a = C.in_String("Ingrese una String; ");
			if (StrFunction.containsEspecials(a)) {
				Print.outSln("Contiene caracteres especiales");
			} else {
				Print.outSln("NO Contiene caracteres especiales");
			}
		}
		




		}//main
}//class