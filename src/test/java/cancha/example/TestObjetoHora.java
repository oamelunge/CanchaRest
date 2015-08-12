package cancha.example;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import cancha.example.model.HoraReserva;

public class TestObjetoHora {
	
	private HoraReserva _horaReserva;
	
	@Test
	public void testHoradeReservaDeveSerMayor5()
	{		
		correrExcepcion(5,0,"No se aceptan horas de reservas menores a 6:00");
	}
	
	@Test
	public void testHoradeReservaDeveSerMenorA24()
	{
		correrExcepcion(24,0,"No se aceptan horas de reservas mayores a 23:00");
	}
	
	@Test
	public void testMinutosDeReservaDebenSer0()
	{
		correrExcepcion(7,3,"Minuto de reserva debe ser 0 o 30");
	}
	
	@Test
	public void testHoraReservaDebeSerAsignada()
	{
		_horaReserva = new HoraReserva(7,0);
		assertEquals(7,_horaReserva.obtenerHora());
	}
	
	@Test
	public void testMinutosDeReservaDebenSer30()
	{
		_horaReserva = new HoraReserva(7,30);
		assertEquals(30,_horaReserva.obtenerMinuto());
	}
	
	@Test
	public void testUnaHoraReservaDebeSaberResponderSuHoraMilitar()
	{
		_horaReserva = new HoraReserva(7,30);
		assertEquals(730,_horaReserva.horaMilitar());
		_horaReserva = new HoraReserva(23,0);
		assertEquals(2300,_horaReserva.horaMilitar());
	}
	
	public void correrExcepcion(int hora, int minuto, String mensaje)
	{
		try {
			_horaReserva = new HoraReserva(hora,minuto);
			fail();
		} catch (Exception e) {
			assertEquals(mensaje,e.getMessage());
		}
	}
}