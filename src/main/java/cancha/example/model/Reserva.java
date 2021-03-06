package cancha.example.model;

import java.time.LocalDate;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;




@XmlRootElement
@Entity
@Table( name = "RESERVA" )
public class Reserva {

	@Id
	@GeneratedValue
	protected long id;
	@Transient
	private HoraReserva horaInicio;
	@Transient
	private HoraReserva horaFin;
	private String descripcion;
	@Convert(converter = LocalDatePersistenceConverter.class)
	private LocalDate fechaReserva;
	private String horaInicioBD;
	private String horaFinBD;
	
	public Reserva()
	{
		
	}

	public Reserva(LocalDate _fechaReserva, HoraReserva _horaInicio, HoraReserva _horaFin, String _descripcion) {
		validarHorarios(_horaInicio, _horaFin);
		this.horaInicio=_horaInicio;
		this.horaFin=_horaFin;
		this.descripcion=_descripcion;
		this.fechaReserva=_fechaReserva;
		this.horaInicioBD=this.getHoraInicioBD();
		this.horaFinBD=this.getHoraFinBD();
		this.id=99;
	}



	private void validarHorarios(HoraReserva _horaInicio, HoraReserva _horaFin) {
		if(_horaInicio.obtenerHora()>_horaFin.obtenerHora())
			throw new RuntimeException("Hora de inicio no puede ser mayor a hora de fin");
		if(_horaInicio.obtenerHora()==_horaFin.obtenerHora())
			throw new RuntimeException("Hora de inicio no puede ser igual a hora de fin");
	}

	public long getId() {
		return this.id;
	}

	
	public String getHoraInicioBD() {
		return Integer.toString(this.getHoraInicio().horaMilitar());
	}

	public String getHoraFinBD() {
		return Integer.toString(this.getHoraFin().horaMilitar());
	}

	public String obtenerDescripcion() {
		return this.descripcion;
	}

	public LocalDate obtenerFechaReserva() {
		return this.fechaReserva;
	}
	
	public HoraReserva getHoraInicio() {
		return horaInicio;
	}

	public HoraReserva getHoraFin() {
		return horaFin;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public LocalDate getFechaReserva() {
		return fechaReserva;
	}

}
