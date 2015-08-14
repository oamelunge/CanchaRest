package cancha.example;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;





import cancha.example.model.Reserva;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Hello, Heroku!";
    }
    
    @Path("roundTrip")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
   // @Produces("application/json")
   // @Consumes("application/json")
    public Reserva roundTrip(Reserva s) {
        return new InsideRest().agregarReserva(s);
    }
    
    @Path("roundTrip2")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getItNow() {
        return "Hello, Bitch!";
    }
    
}
