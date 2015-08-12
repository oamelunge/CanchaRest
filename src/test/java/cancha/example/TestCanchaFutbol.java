package cancha.example;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cancha.example.model.*;
import cancha.example.testEnviroment.Environment;


public class TestCanchaFutbol{

	/*Como cliente de la cancha de la cancha de futbol necesito agendar un 
	 * horario para poder jugar futbol
	 * */
	private DBSystem erpSystem;
	private HoraReserva _horaInicio;
	private HoraReserva _horaFin;
	private LocalDate _fechaReserva = LocalDate.of(2015, 8, 10);	
	private Reserva _reserva1;
	private Reserva _reserva2;
	private String _descripcion;
	private CancheroSystem _canchero;

	@Before
	public void setUp() {
		erpSystem = Environment.current().createDBSystem();		
		erpSystem.beginTransaction();
		_canchero = erpSystem.cancheroSystem();
	}
	
	@After
	public void tearDown() {
		erpSystem.close();
	}

	
	@Test
	public void testCalendarioDebeAgregarUnaReserva() {
		_reserva1=inicializarReserva(8,30,9,30);	
		_canchero.agregarReserva(_reserva1);
		assertTrue(_canchero.contains(_reserva1));
		erpSystem.commit();
			
	}
	
	
	public void reservaConcurrente()
	{
		_reserva2=inicializarReserva(8,30,9,30);		
		_canchero.agregarReserva(_reserva1);
		try {
			
			_canchero.agregarReserva(_reserva2);
			fail();

		} catch (Exception e) {		
			assertEquals("La hora de reserva ya esta ocupada", e.getMessage());
		}finally{
			erpSystem.commit();
		}
		
	}
	
	@Test
	public void testCalendarioNoDebePermitirAgragarUnaReservaEnUnHorarioYaReservado()
	{
		_reserva1=inicializarReserva(8,30,9,30);
		reservaConcurrente();
		
	}
	
	@Test
	public void testCalendarioNoDebePermitirAgragarUnaReservaALaMitadDeUnHorariorReservado()
	{
		_reserva1=inicializarReserva(8,0,9,0);
		reservaConcurrente();
	}
	
	@Test
	public void testCalendarioDebeDevolverLasReservasEnUnDiaEspecifico()
	{
		_reserva1=inicializarReserva(18,0,19,0);
		_reserva2=inicializarReserva(19,0,20,0);
		Reserva reserva3 =inicializarReserva(20,0,21,0);		
		Reserva reserva4 =inicializarReserva(21,0,22,0);
		Reserva reserva5 =inicializarReserva(22,0,23,0);
	
		_canchero.agregarReserva(_reserva1);
		_canchero.agregarReserva(_reserva2);
		_canchero.agregarReserva(reserva3);
		_canchero.agregarReserva(reserva4);
		_canchero.agregarReserva(reserva5);
		
		
		List<Reserva> reservasHoy=_canchero.obtenerReservasParaUnaFecha(_fechaReserva);
		assertEquals(5,reservasHoy.size());
		
		erpSystem.commit();
	}
	@Test
	public void testCalendariodebeDevolverLasReservasEnUnRangoDeFechas(){
		_reserva1=inicializarReserva(18,0,19,0);
		_reserva2=inicializarReserva(19,0,20,0);
		Reserva reserva3 =inicializarReserva(20,0,21,0);
		LocalDate fechaDesde=_fechaReserva;
		_fechaReserva = LocalDate.of(2015, 8, 11);
		LocalDate fechaHasta=_fechaReserva;
		Reserva reserva4 =inicializarReserva(21,0,22,0);
		Reserva reserva5 =inicializarReserva(22,0,23,0);
	
		_canchero.agregarReserva(_reserva1);
		_canchero.agregarReserva(_reserva2);
		_canchero.agregarReserva(reserva3);
		_canchero.agregarReserva(reserva4);
		_canchero.agregarReserva(reserva5);
		
		List<Reserva> reservasHoy=_canchero.obtenerReservasEnUnRangoDeFecha(fechaDesde,fechaHasta);
		assertEquals(5,reservasHoy.size());
		erpSystem.commit();
	}
	
	private Reserva inicializarReserva(int horaInicio, int minutoInicio, int horaFin, int minutoFin ) {
		_horaInicio = new HoraReserva(horaInicio,minutoInicio);
		_horaFin = new HoraReserva(horaFin,minutoFin);
		_descripcion = "Oscar Amelunge 75520286";
		return new Reserva(_fechaReserva,_horaInicio, _horaFin, _descripcion);
		
	}
	
	


}
