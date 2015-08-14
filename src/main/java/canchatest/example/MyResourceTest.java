package canchatest.example;

import java.time.LocalDate;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import cancha.example.MyResource;
import cancha.example.model.HoraReserva;
import cancha.example.model.Reserva;

public class MyResourceTest extends JerseyTest {

	
	private HoraReserva _horaInicio;
	private HoraReserva _horaFin;
	private LocalDate _fechaReserva;
	private String _descripcion;
	
    @Override
    protected Application configure() {
        return new ResourceConfig(MyResource.class);
    }

    @Test
    public void testGetIt() {
        final String responseMsg = target().path("myresource").request().get(String.class);

        assertEquals("Hello, Heroku!", responseMsg);
    }
    
    @Test
    public void testReserva() {
        //final String responseMsg = target().path("myresource").request().get(String.class);
        final Reserva reserva = target().path("myresource/roundTrip")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(inicializarReserva(8,30,9,30), MediaType.APPLICATION_JSON_TYPE), Reserva.class);
        assertEquals(reserva, inicializarReserva(8,30,9,30));
    }
    
	private Reserva inicializarReserva(int horaInicio, int minutoInicio, int horaFin, int minutoFin ) {
		_horaInicio = new HoraReserva(horaInicio,minutoInicio);
		_horaFin = new HoraReserva(horaFin,minutoFin);
		_descripcion = "Oscar Amelunge 75520286";
		_fechaReserva = LocalDate.now();
		return new Reserva(_fechaReserva,_horaInicio, _horaFin, _descripcion);
		
	}
}
