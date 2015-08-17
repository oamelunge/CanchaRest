package cancha.example;



import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import cancha.example.model.*;


public class CancheroPersistence implements CancheroSystem {

	private static final String VERIFICAR_RESERVAS_ENTRE_HORARIOS = "from Reserva r where r.fechaReserva=:fechaReserva  and CAST(r.horaFinBD AS integer) >:horaInicio and CAST(r.horaInicioBD AS integer)<=:horaInicio";
	private static final String VERIFICAR_RESERVAS_EN_MISMO_HORARIO = "from Reserva r where r.fechaReserva=:fechaReserva and CAST(r.horaInicioBD AS integer)=:horaInicio";
	private String querysParaVerificarExistencia[]={VERIFICAR_RESERVAS_ENTRE_HORARIOS,VERIFICAR_RESERVAS_EN_MISMO_HORARIO};
	private static final String ERROR_HORA_RESERVA_OCUPADA = "La hora de reserva ya esta ocupada";
	private PersistentDBSystem parentSystem;

	public CancheroPersistence(PersistentDBSystem parentSystem) {
		this.parentSystem = parentSystem;
	}
	@Override
	public Reserva agregarReserva(Reserva reserva) {
		
		validar(reserva);
		session().save(reserva);
		return reserva;
	}
	private void validar(Reserva reserva) {
		for(String query: querysParaVerificarExistencia)
			this.verificarSiExistenReservasPrevias(query,reserva);
	}
	
	private void verificarSiExistenReservasPrevias(String queryString,Reserva reserva)
	{
		@SuppressWarnings("unchecked")
		List<Reserva> selectedReservas=session().createQuery(queryString)
				.setParameter("fechaReserva", reserva.obtenerFechaReserva())
				.setParameter("horaInicio", reserva.getHoraInicio().horaMilitar()).list();
		
		if (selectedReservas.size()>0)
			throw new RuntimeException(ERROR_HORA_RESERVA_OCUPADA);
	}
	

	@Override
	public boolean contains(Reserva reserva) {
		
		return session().contains(reserva);
	}
	
	private Session session() {
		return parentSystem.session();
	}
	@Override
	public Reserva obtenerReserva(long id) {
		@SuppressWarnings("unchecked")
		List<Reserva> selectedReservas = (List<Reserva>) session().createCriteria(Reserva.class)
			.add(Restrictions.eq("id",id))
			.list();
		
		if (selectedReservas.size()!=1)
			throw new RuntimeException();
		else
			return selectedReservas.get(0);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Reserva> obtenerReservasParaUnaFecha(LocalDate fechaReservas) {
		return session().createQuery("from Reserva r where r.fechaReserva=:fechaReserva")
				.setParameter("fechaReserva", fechaReservas)
				.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Reserva> obtenerReservasEnUnRangoDeFecha(LocalDate fechaDesde,
			LocalDate fechaHasta) {
		return session().createQuery("from Reserva r where r.fechaReserva>=:fechaDesde and r.fechaReserva<=:fechaHasta")
				.setParameter("fechaDesde", fechaDesde)
				.setParameter("fechaHasta", fechaHasta)
				.list();
	}

}
