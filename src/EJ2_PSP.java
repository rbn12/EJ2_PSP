import java.util.Scanner;

public class EJ2_PSP {
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			System.out.println("PASO 1 ");
			LectorBBDD nwReader = new LectorBBDD(1);
			nwReader.leerRegistros();
			System.out.println("");
			Scanner sc = new Scanner(System.in);
			System.out.println("PASO 2 (PRESIONA ENTER)");
			sc.nextLine();
			
			LectorBBDD nwReader2 = new LectorBBDD(5);
			nwReader2.leerRegistros(5);
			
	}

}
