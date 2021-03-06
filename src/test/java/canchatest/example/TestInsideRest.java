package canchatest.example;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import cancha.example.InsideRest;
import cancha.example.ReservaDTO;
import cancha.example.heroku.AppException;
import cancha.example.model.HoraReserva;
import cancha.example.model.Reserva;

public class TestInsideRest {

	
	@Test
	public void testCrearHorarioATravezDeInterfaceRest() throws AppException{
	
		InsideRest inside = new InsideRest();		
		assertEquals(inside.agregarReserva(inicializarReserva()).descripcion,inicializarReserva().getDescripcion());
		
	}
	
private ReservaDTO inicializarReserva( ) {
		
		return new ReservaDTO(0,"8:30","9:30","Oscar Amelunge 75520286","15-08-2015");
		
	}
}
