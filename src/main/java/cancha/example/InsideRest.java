package cancha.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.ws.rs.core.Response;


import cancha.example.Enviroment.Environment;
import cancha.example.heroku.AppException;
import cancha.example.model.HoraReserva;
import cancha.example.model.Reserva;


public class InsideRest {

	private CancheroSystem cancheroSystem;
	private DBSystem erpSystem;
	
	public InsideRest()
	{
		erpSystem = Environment.current().createDBSystem();		
		erpSystem.beginTransaction();
		cancheroSystem = erpSystem.cancheroSystem();
	}
	
	public ReservaDTO agregarReserva(ReservaDTO reservaDTO) throws AppException {
		try {
		
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate fechaReserva = LocalDate.parse(reservaDTO.fechaReserva, formatter);
			
			String[]  aHoraInicioReserva = reservaDTO.horaInicio.split(":");
			HoraReserva horaInicio = new HoraReserva(Integer.valueOf(aHoraInicioReserva[0]),Integer.valueOf(aHoraInicioReserva[1])); 
			
			String[]  aHoraFinReserva = reservaDTO.horaFin.split(":");
			HoraReserva horaFin = new HoraReserva(Integer.valueOf(aHoraFinReserva[0]),Integer.valueOf(aHoraFinReserva[1]));
			
			String descripcion = reservaDTO.descripcion;
			
			Reserva r = new Reserva(fechaReserva,horaInicio,horaFin,descripcion);
			cancheroSystem.agregarReserva(r);
			ReservaDTO rdto= new ReservaDTO(r.getId(),r.getHoraInicioBD(), r.getHoraFinBD(), r.getDescripcion(), r.getFechaReserva().toString());
			return rdto;
		} catch (Exception e) {
			throw new AppException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),500,"Error Al Agregar reserva",e.getStackTrace().toString(),"aa");
		}
		finally{
			erpSystem.commit();
			erpSystem.close();
		}
		
	}

}
