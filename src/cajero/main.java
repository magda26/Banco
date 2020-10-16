package cajero;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class main {

	public static void main(String[] args) throws IOException {
		String cedula = "12345";
		String clave = "12344";
		if(isValid(cedula,clave)) {
            Scanner myInput = new Scanner( System.in );
			do {
				Cuenta cuenta = getCuenta(cedula,clave);
		        Properties datos = new Properties( );
	            FileInputStream input;
	            input = new FileInputStream( "data/config.properties" );
		        datos.load( input );
	            showMenu(datos);
	            String opcion;
	            opcion = myInput.next();
	            switch (opcion) {
	            case "s":
	    			System.out.print(cuenta.consultarSaldo());
	            	break;
	            case "r":
	    			System.out.print("Ingrese valor a retirar:");
	    			cuenta.retirarDinero(Integer.parseInt(myInput.next()));
	            	break;
	            case "t":
	    			System.out.print("Ingrese cedula y  valor a transferir:");
	    			String cedulaDestinatario = myInput.next();
	    			cuenta.transferir(cedulaDestinatario, myInput.nextInt());
	            	break;
	            case "c":
	    			System.out.print("Ingrese valor a consignar:");
	    			cuenta.transferir(cedula, myInput.nextInt());
	            	break;
	            case "p":
	    			System.out.print("Ingrese servicio y valor a pagar:");
	    			cuenta.pagoServicios(myInput.nextInt(), myInput.nextInt());
	            	break;
	            }	
    			System.out.print("\nDesea continuar? S/N:");
			}while(myInput.next().equals("S"));
		}
	}
	public static boolean isValid(String cedula, String clave) {
		return true;
	}
	public static Cuenta getCuenta(String cedula, String clave) {
		return new Cuenta(4500000);
	}
	public static void showMenu( Properties datos ) {
		System.out.print("s. Consultar saldo \nr. Retirar dinero \n");
		if (Boolean.parseBoolean(datos.getProperty( "transferencias" ))) {
			System.out.print("t.Realizar transferencias \n");
		}
		if (Boolean.parseBoolean(datos.getProperty( "consignaciones" ))) {
			System.out.print("c.Realizar consignaciones \n");
		}
		if (Boolean.parseBoolean(datos.getProperty( "pago_servicios" ))) {
			System.out.print("p.Realizar pago de servicios \n");
		}
	}

}
