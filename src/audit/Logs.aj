package audit;

import java.io.FileInputStream;
import java.util.Properties;

public aspect Logs {
	pointcut login() : call( * cajero.Banco.login(..));
	
	after() returning(boolean login): login() {

		if(login) {
	        Properties datos = new Properties( );
            FileInputStream input;
            try {
            	input = new FileInputStream( "data/config.properties" );
		        datos.load( input );
		        if(Boolean.parseBoolean(datos.getProperty("custom_logs"))) {
					System.out.println("Se guarda la fecha y la hora de ingreso del usuario en el custom log");
		        }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	pointcut transaccionesCustom() : call( * cajero.Cuenta..*(..));

	after(): transaccionesCustom() {
        Properties datos = new Properties( );
        FileInputStream input;
        try {
			input = new FileInputStream( "data/config.properties" );
	        datos.load( input );
		} catch (Exception e) {
			e.printStackTrace();
		}
        if(Boolean.parseBoolean(datos.getProperty("custom_logs"))) {
    		System.out.println("Guardando cada transacción del usuario custom ");
    		System.out.println("\t método     : " + thisJoinPoint.getSignature());
    		System.out.println("\t de la cuenta  : " + thisJoinPoint.getTarget());        
    	}
	}
}
