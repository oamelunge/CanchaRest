package canchatest.example;


import javax.ws.rs.client.Entity;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import cancha.example.MyResource;
import cancha.example.ReservaDTO;



public class MyResourceTest extends JerseyTest {

	
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
        ReservaDTO r = inicializarReserva();
    	final ReservaDTO reserva = target().path("myresource/roundTrip")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(r, MediaType.APPLICATION_JSON_TYPE), ReservaDTO.class);
        assertEquals(r.descripcion, reserva.getDescripcion());
    }
   
	private ReservaDTO inicializarReserva( ) {
		
		return new ReservaDTO("8:30","9:30","Oscar Amelunge 75520286","15-08-2015");
		
	}
}
