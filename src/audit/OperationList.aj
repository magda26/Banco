package audit;

import cajero.Cuenta;

public aspect OperationList {
	
	pointcut operaciones() : call( * cajero.Cuenta..*(..));
	
	after(): operaciones() {
		System.out.println("Guardando log ");
		System.out.println("\t m�todo     : " + thisJoinPoint.getSignature());
		System.out.println("\t de la cuenta  : " + thisJoinPoint.getTarget());
	}

}
