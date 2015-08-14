package canchatest.example;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.time.LocalDate;
import org.junit.Test;

import cancha.example.model.*;

public class TestReserva {
	private HoraReserva _horaInicio;
	private HoraReserva _horaFin;
	private LocalDate _fechaReserva;
	private Reserva _reserva;
	private String _descripcion;
	private Runnable _codigo;

	@Test
	public void testReservaDebeTenerUnaHoradeInicioyUnaHoraFin() {
		inicializarReserva(8,30,9,30);
		assertEquals(_horaInicio,_reserva.obtenerHoraInicio());
		assertEquals(_horaFin,_reserva.obtenerHoraFin());
	}
	
	@Test
	public void testHorarioDeHoraDeInicioDebeSerMenorAHorarioHoraFin() {
		
		_codigo = ( ) -> inicializarReserva(9,30,8,30);
		correrExcepcion(_codigo,"Hora de inicio no puede ser mayor a hora de fin");
	}

	@Test
	public void testHorarioDeHoraDeInicioNoDebeSerIgualAHorarioHoraFin() {

		_codigo = ( ) -> inicializarReserva(9,30,9,30);
		correrExcepcion(_codigo,"Hora de inicio no puede ser igual a hora de fin");
	}

	@Test
	public void testReservaDebeTenerUnaDescriptiocn() {
	
		inicializarReserva(22,30,23,30);
		assertEquals(_descripcion,_reserva.obtenerDescripcion());
		
	}
	
	@Test
	public void testReservaDebeTenerUnaFechaDeReserva()
	{
		inicializarReserva(22,30,23,30);		
		assertEquals(_fechaReserva,_reserva.obtenerFechaReserva());
	}

	private void inicializarReserva(int horaInicio, int minutoInicio, int horaFin, int minutoFin ) {
		_horaInicio = new HoraReserva(horaInicio,minutoInicio);
		_horaFin = new HoraReserva(horaFin,minutoFin);
		_descripcion = "Oscar Amelunge 75520286";
		_fechaReserva = LocalDate.now();
		_reserva = new Reserva(_fechaReserva,_horaInicio, _horaFin, _descripcion);
		
	}
	
	public void correrExcepcion(Runnable codigo, String mensaje){	
		try {	
				codigo.run();
				fail();
		} catch (Exception e) {
			assertEquals(mensaje,e.getMessage());
		}
	}
}