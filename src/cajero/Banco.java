package cajero;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Banco {

	public static void main(String[] args) throws IOException {
		String cedula = "12345";
		String clave = "12344";
		if(login(cedula,clave)) {
            Scanner myInput = new Scanner( System.in );
			Cuenta cuenta = getCuenta(cedula,clave);
	        Properties datos = new Properties( );
            FileInputStream input;
            input = new FileInputStream( "data/config.properties" );
	        datos.load( input );
			do {
	            showMenu(datos);
	            String opcion;
	            opcion = myInput.next();
	            switch (opcion) {
	            case "s":
	    			System.out.print(cuenta.getSaldo());
	            	break;
	            case "r":
	            	boolean saldoReducido = Boolean.parseBoolean(datos.getProperty( "saldo_reducido" ));
	    			System.out.print("Ingrese valor a retirar:\n");
	    			int saldoActual = cuenta.retirarDinero(Integer.parseInt(myInput.next()),saldoReducido);
	    			if(saldoActual < 0) {
		    			System.out.print("No se puede realizar esta operación\n");
	    			}else {
		    			System.out.print("Ahora su saldo es:"+saldoActual+"\n");
	    			}
	            	break;
	            case "t":
	    			System.out.print("Ingrese cedula y  valor a transferir:\n");
	    			String cedulaDestinatario = myInput.next();
	    			cuenta.transferir(cedulaDestinatario, myInput.nextInt());
	            	break;
	            case "c":
	    			System.out.print("Ingrese valor a consignar:\n");
	    			cuenta.transferir(cedula, myInput.nextInt());
	            	break;
	            case "p":
	    			System.out.print("Ingrese servicio y valor a pagar: \n");
	    			cuenta.pagoServicios(myInput.nextInt(), myInput.nextInt());
	            	break;
	            }	
    			System.out.print("\nDesea continuar? S/N:");
			}while(myInput.next().equals("S"));
		}
	}
	public static boolean login(String cedula, String clave) {
		return true;
	}
	public static Cuenta getCuenta(String cedula, String clave) {
		return new Cuenta(450000);
	}
	public static void showMenu( Properties datos ) {
		System.out.print("s. Consultar saldo \nr. Retirar dinero \n");
		if (Boolean.parseBoolean(datos.getProperty( "transferencias" ))) {
			System.out.print("t. Realizar transferencias \n");
		}
		if (Boolean.parseBoolean(datos.getProperty( "consignaciones" ))) {
			System.out.print("c. Realizar consignaciones \n");
		}
		if (Boolean.parseBoolean(datos.getProperty( "pago_servicios" ))) {
			System.out.print("p. Realizar pago de servicios \n");
		}
	}

}
