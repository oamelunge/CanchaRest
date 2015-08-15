package cancha.example;

public class ReservaDTO {

	public String horaInicio ;
	public String horaFin;
    public String descripcion;
	public String fechaReserva;
	
	public ReservaDTO()
	{
		
	}
	
	public ReservaDTO(String horaInicio, String horaFin, String descripcion,
			String fechaReserva) {
		super();
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.descripcion = descripcion;
		this.fechaReserva = fechaReserva;
	}
	public String getHoraInicio() {
		return horaInicio;
	}
	public String getHoraFin() {
		return horaFin;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public String getFechaReserva() {
		return fechaReserva;
	}
}
