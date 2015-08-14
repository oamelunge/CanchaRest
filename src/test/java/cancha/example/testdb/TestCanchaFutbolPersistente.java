package cancha.example.testdb;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cancha.example.testEnviroment.Environment;

import cancha.example.CancheroSystem;
import cancha.example.DBSystem;
import cancha.example.model.HoraReserva;
import cancha.example.model.Reserva;



public class TestCanchaFutbolPersistente {

	private CancheroSystem cancheroSystem;
	private DBSystem erpSystem;
	private HoraReserva _horaInicio;
	private HoraReserva _horaFin;
	private LocalDate _fechaReserva;
	private Reserva _reserva1;
	private String _descripcion;
	private CancheroSystem _canchero; 
	
	@Before
	public void setUp() {
		erpSystem = Environment.current().createDBSystem();	
		erpSystem.beginTransaction();
		cancheroSystem = erpSystem.cancheroSystem();
	}

	@After
	public void tearDown() {
		erpSystem.close();
	}
	
	@Test
	public void testCalendarioDebeAgregarUnaReserva() {
		_reserva1=inicializarReserva(8,30,9,30);
		_canchero = cancheroSystem;	
		_canchero.agregarReserva(_reserva1);
		assertTrue(_canchero.contains(_reserva1));
		erpSystem.commit();
		
	}
	
	@Test
	public void testCalendarioDebeEncontrarReserva() {
		_reserva1=inicializarReserva(8,30,9,30);
		_canchero = cancheroSystem;	
		_canchero.agregarReserva(_reserva1);		
		erpSystem.commit();
		Reserva oReserva=_canchero.obtenerReserva(_reserva1.getId());
		assertEquals(_reserva1.obtenerDescripcion(),oReserva.obtenerDescripcion());
		assertEquals(_reserva1.obtenerHoraInicio(),oReserva.obtenerHoraInicio());
		
	}
	
	private Reserva inicializarReserva(int horaInicio, int minutoInicio, int horaFin, int minutoFin ) {
		_horaInicio = new HoraReserva(horaInicio,minutoInicio);
		_horaFin = new HoraReserva(horaFin,minutoFin);
		_descripcion = "Oscar Amelunge 75520286";
		_fechaReserva = LocalDate.now();
		return new Reserva(_fechaReserva,_horaInicio, _horaFin, _descripcion);
		
	}
}
