package spothero.demo.api;

import spothero.demo.model.ParkingRange;
import spothero.demo.model.ParkingRates;
import spothero.demo.model.Rate;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("park")
public class Park {

    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response park(@QueryParam("start") String startISO8601, @QueryParam("end") String end8601 ) {
        ParkingRange range = new ParkingRange(startISO8601, end8601);
        ParkingRates rates = ParkingRates.testData();
        ParkingComputer computer = new ParkingComputer(rates, range);
        try {
            Rate rate = computer.compute();
            return Response.status(javax.ws.rs.core.Response.Status.OK).entity(rate).build();
        } catch (IllegalArgumentException e) {
            return Response.status(422, sanitize(e.getMessage())).entity(Rate.Unavailable).build();
        }
    }

    private String sanitize(String message) {
        return message;
    }
}
