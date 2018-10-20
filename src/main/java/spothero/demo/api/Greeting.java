package spothero.demo.api;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("greeting")
public class Greeting {

    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response greeting() {
        spothero.demo.model.Greeting greeting = new spothero.demo.model.Greeting();
        greeting.setMessage("Hello World!");

        return Response.status(javax.ws.rs.core.Response.Status.OK).entity(greeting).build();

    }
}
