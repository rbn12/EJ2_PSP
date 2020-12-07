import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.Semaphore;

public class LectorBBDD extends Thread{
private int num_Hilos=0;
private int contadorRegistros = 0;
private Semaphore sm = new Semaphore(5);
	public LectorBBDD(int numH) {
		this.num_Hilos=numH;
		}
	
		public void  leerRegistros(){
			long inicio = System.currentTimeMillis();
			Connection cn ;
	        try {
	            cn = DriverManager.getConnection("jdbc:mysql://localhost/bbdd_psp_1", "DAM2020_PSP", "DAM2020_PSP");
	            Statement registro = cn.createStatement();
	            ResultSet rs = registro.executeQuery("select * from empleados");
	            Object[] file = new Object[3];
	            	
	            int contadorIngresos = 0;
	            
	            while(rs.next()) {
	            		file[0]=rs.getInt(1);
	            		file[1]=rs.getString(2);
	            		file[2]=rs.getInt(3);
	            		System.out.println("ID: " +file[0]+"    Email: "+file[1]+ "    Ingresos "+ file[2]);
	            		contadorIngresos+=(int)file[2];
	            		contadorRegistros++;
	            	}
	            		System.out.println("Ingresos totales de la plantilla"+contadorIngresos);
	           
	            
	            
	            
	            System.out.println("BBDD leida :"+ contadorRegistros + " registros " );
	            
	            
	            cn.close();

	            long fin = System.currentTimeMillis();
	            double tiempo = (double) ((fin - inicio));
	            System.out.println("Se ha tardado "+ tiempo +"ms");
	        } catch (SQLException e) {
	            e.printStackTrace(); }
	    }
		public void  leerRegistros(int num_Hilos){
			long inicio = System.currentTimeMillis();
			Connection cn ;
	        try {
	            cn = DriverManager.getConnection("jdbc:mysql://localhost/bbdd_psp_1", "DAM2020_PSP", "DAM2020_PSP");
	            Statement registro = cn.createStatement();
	            ResultSet rs = registro.executeQuery("select * from empleados");
	            Object[] file = new Object[3];
	            int contadorIngresos = 0;
	            int contadorRegistros = 0;
	            sm.acquire(num_Hilos);
					
					
					
				
	            while(rs.next()) {
	            		file[0]=rs.getInt(1);
	            		file[1]=rs.getString(2);
	            		file[2]=rs.getInt(3);
	            		System.out.println("ID: " +file[0]+"    Email: "+file[1]+ "    Ingresos "+ file[2]);
	            		contadorIngresos+=(int)file[2];
	            		contadorRegistros++;
	            	}
	            		
	            
	            sm.release(num_Hilos);
	            System.out.println(contadorIngresos);
	           	            
	            System.out.println("BBDD leida :"+ contadorRegistros + " registros " );
	            
	            
	            cn.close();
	            
	            long fin = System.currentTimeMillis();
	            double tiempo = (double) ((fin - inicio));
	            System.out.println("Se ha tardado "+ tiempo +"ms");}
	        	catch (Exception e) {
					System.out.println("Excepcion encontrada");
				}
	        
	    }
	@Override
		public void run() {
			
			super.run();
			leerRegistros();
			leerRegistros(num_Hilos);
		}
}
