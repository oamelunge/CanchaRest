package cancha.example;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.JSONP;

import cancha.example.heroku.AppException;


@Path("myresource")
public class MyResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Hello, Heroku!";
    }
    
   
    @POST
    @Path("roundTrip")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)  
    public ReservaDTO roundTrip(ReservaDTO reservaDTO) throws AppException {
    	InsideRest ir = new InsideRest();
    	return  ir.agregarReserva(reservaDTO);
    }
    
    @Path("test")
    @GET
    @Produces({"application/json", "application/javascript"})
    @JSONP(callback = "eval", queryParam = "jsonpCallback")
    public ReservaDTO getReservaDTO() throws AppException{
    	InsideRest ir = new InsideRest();
    	return  ir.agregarReserva( new ReservaDTO(0,"8:30","9:30","Oscar Amelunge 75520286","15-08-2015"));       
    }
        
}
