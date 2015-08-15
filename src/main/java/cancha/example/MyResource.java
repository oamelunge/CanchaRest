package cancha.example;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("myresource")
public class MyResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Hello, Heroku!";
    }
    
    @Path("roundTrip")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)  
    public ReservaDTO roundTrip(ReservaDTO reservaDTO) {
    	InsideRest ir = new InsideRest();
    	return  ir.agregarReserva(reservaDTO);
    }
        
}
