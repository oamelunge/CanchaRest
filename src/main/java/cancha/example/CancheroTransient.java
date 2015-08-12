package cancha.example;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cancha.example.model.*;


public class CancheroTransient implements CancheroSystem {
    
	private List<Reserva> calendario = new ArrayList<Reserva>();
	
	@SuppressWarnings("unused")
	private TransientDBSystem parentSystem;
	
	public CancheroTransient(TransientDBSystem parentSystem)	{
		this.parentSystem = parentSystem;
	}
	
	@Override
	public void agregarReserva(Reserva reserva) {
		if(existeAlgunaReservaEnLaMismaFecha(reserva))
			validarHorarioDeNuevaReserva(reserva);
		calendario.add(reserva);
	}

	private boolean existeAlgunaReservaEnLaMismaFecha(Reserva reserva) {
		return calendario.stream().anyMatch(r-> r.obtenerFechaReserva().equals(reserva.obtenerFechaReserva()));
	}

	private void validarHorarioDeNuevaReserva(Reserva reserva) {
		if(seIntentaReservarEnUnHorarioOcupado(reserva))
			throw new RuntimeException("La hora de reserva ya esta ocupada");
		if(seIntentaReservarAMitadDeUnHorarioOcupado(reserva))
			throw new RuntimeException("La hora de reserva ya esta ocupada");
	}

	private boolean seIntentaReservarAMitadDeUnHorarioOcupado(Reserva reserva) {
		return calendario.stream().anyMatch(r-> reserva.obtenerHoraInicio().horaMilitar() >= r.obtenerHoraInicio().horaMilitar()
				                      && reserva.obtenerHoraInicio().horaMilitar() < r.obtenerHoraFin().horaMilitar());
	}

	private boolean seIntentaReservarEnUnHorarioOcupado(Reserva reserva) {
		return calendario.stream().anyMatch(r->r.obtenerHoraInicio().horaMilitar()==reserva.obtenerHoraInicio().horaMilitar());
	}

	@Override
	public boolean contains(Reserva reserva) {
		return calendario.contains(reserva);
	}

	@Override
	public Reserva obtenerReserva(long id) {
		throw new RuntimeException("Metodo No Implementado");
	}

	@Override
	public List<Reserva> obtenerReservasParaUnaFecha(LocalDate fechaReservas) {
		return (List<Reserva>) calendario.stream()
					.filter(r-> r.obtenerFechaReserva()==fechaReservas)
						.collect(Collectors.toList());
	}

	@Override
	public List<Reserva> obtenerReservasEnUnRangoDeFecha(LocalDate fechaDesde,
			LocalDate fechaHasta) {
		return (List<Reserva>) calendario.stream()
				.filter(r-> r.obtenerFechaReserva().toEpochDay()>=fechaDesde.toEpochDay() &&
				            r.obtenerFechaReserva().toEpochDay()<=fechaHasta.toEpochDay())
					.collect(Collectors.toList());
	}

}
