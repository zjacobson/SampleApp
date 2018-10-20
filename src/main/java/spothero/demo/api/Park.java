package spothero.demo.api;

import spothero.demo.model.ParkingRange;

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
        return Response.status(javax.ws.rs.core.Response.Status.OK).entity(computer.compute()).build();
    }
}
