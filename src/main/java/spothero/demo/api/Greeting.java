package spothero.demo.api;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("greeting")
public class Greeting {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public spothero.demo.client.model.Greeting greeting() {
        spothero.demo.client.model.Greeting greeting = new spothero.demo.client.model.Greeting();
        greeting.setResponse("Hello!");
        return greeting;
    }
}
