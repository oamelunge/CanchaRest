package cancha.example.model;



public class HoraReserva {

	
	private int hora;
	private int minuto;
	
	//Principio de encapsulamiento
	
	public HoraReserva(int _hora, int _minuto)
	{
		validarHoraReserva(_hora, _minuto);
		this.hora=_hora;
		this.minuto=_minuto;
	}


	private void validarHoraReserva(int _hora, int _minuto) {
		validarRangoDeHora(_hora);
		validarminuto(_minuto);
	}


	private void validarminuto(int _minuto) {
		if(_minuto!=0)
			if(_minuto!=30)
			throw new RuntimeException("Minuto de reserva debe ser 0 o 30");
	}
	

	public int obtenerHora() {
		return this.hora;
	}

	private void validarRangoDeHora(int hora) {
		if(hora<6)
			throw new RuntimeException("No se aceptan horas de reservas menores a 6:00");
		if(hora>23)
		    throw new RuntimeException("No se aceptan horas de reservas mayores a 23:00");
		this.hora=hora;
	}


	public int obtenerMinuto() {
		return this.minuto;
	}

	public int horaMilitar() {
		return this.hora*100+this.minuto;
	}
}
