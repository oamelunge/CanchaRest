package cancha.example;

import java.time.LocalDate;
import java.util.List;

import cancha.example.model.*;



public interface CancheroSystem {

	public abstract Reserva agregarReserva(Reserva reserva);
	public abstract boolean contains(Reserva reserva);
	public abstract Reserva obtenerReserva(long id);
	public abstract List<Reserva> obtenerReservasParaUnaFecha(LocalDate fechaReservas);
	public abstract List<Reserva> obtenerReservasEnUnRangoDeFecha(
			LocalDate fechaDesde, LocalDate fechaHasta);
	

}