package cancha.example;

import cancha.example.model.Reserva;
import cancha.example.testEnviroment.Environment;

public class InsideRest {

	private CancheroSystem cancheroSystem;
	private DBSystem erpSystem;
	
	public InsideRest()
	{
		erpSystem = Environment.current().createDBSystem();		
		erpSystem.beginTransaction();
		cancheroSystem = erpSystem.cancheroSystem();
	}
	
	public Reserva agregarReserva(Reserva nuevaReserva) {
		 return cancheroSystem.agregarReserva(nuevaReserva);
	}

}
