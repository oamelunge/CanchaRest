package canchatest.example.testdb;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import cancha.example.InsideRest;
import cancha.example.model.HoraReserva;
import cancha.example.model.Reserva;

public class TestInsideRest {

	private HoraReserva _horaInicio;
	private HoraReserva _horaFin;
	private LocalDate _fechaReserva;
	private String _descripcion;
	
	@Test
	public void testCrearHorarioATravezDeInterfaceRest(){
	
		InsideRest inside = new InsideRest();		
		assertTrue(inside.agregarReserva(inicializarReserva(8,30,9,30)).getId()>0);
		
	}
	
	private Reserva inicializarReserva(int horaInicio, int minutoInicio, int horaFin, int minutoFin ) {
		_horaInicio = new HoraReserva(horaInicio,minutoInicio);
		_horaFin = new HoraReserva(horaFin,minutoFin);
		_descripcion = "Oscar Amelunge 75520286";
		_fechaReserva = LocalDate.now();
		return new Reserva(_fechaReserva,_horaInicio, _horaFin, _descripcion);
		
	}
}
