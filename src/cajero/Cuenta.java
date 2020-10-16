package cajero;

public class Cuenta {
	int saldo;
	Cuenta(int saldo){
		this.saldo=saldo;
	}
	
	
	public int getSaldo() {
		return saldo;
	}


	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}


	public int consultarSaldo() {
		return saldo;
	}
	public int retirarDinero(int valor) {
		return this.saldo-valor;
	}
	public boolean transferir(String cedula, int valor) {
		return true;
	}
	public boolean pagoServicios(int idServicio, int valor) {
		return true;
	}
	public boolean consignar(int valor) {
		this.saldo +=valor;
		return true;
	}

}
