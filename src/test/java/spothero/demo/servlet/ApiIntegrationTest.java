package spothero.demo.servlet;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import spothero.demo.JerseyApplication;
import spothero.demo.model.Rate;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ApiIntegrationTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new JerseyApplication();
    }

    @Test
    public void testSaturdayRate() {
        Response response = target("/park").
                queryParam("start", "2018-10-20T10:00:00Z").
                queryParam("end", "2018-10-20T13:00:00Z").
                request().
                get();

        assertEquals(200, response.getStatus());
        Rate entity = response.readEntity(Rate.class);
        assertEquals("2000", entity.getPrice());
    }

    @Test
    public void testInvalidInput() {
        Response response = target("/park").
                queryParam("start", "2018-10-320T10:00:00Z").
                queryParam("end", "2018-13-20T13:00:00Z").
                request().
                get();

        assertEquals(422, response.getStatus());
        String message = response.getStatusInfo().getReasonPhrase();

        assertThat(message, startsWith("Invalid format"));
    }

    @Test
    public void testRangeSpanningDays() {
        Response response = target("/park").
                queryParam("start", "2018-10-20T10:00:00Z").
                queryParam("end", "2018-10-21T13:00:00Z").
                request().
                get();

        assertEquals(200, response.getStatus());
        Rate entity = response.readEntity(Rate.class);
        assertEquals(Rate.Unavailable, entity);
    }

    @Test
    public void testUnavailableTime() {
        Response response = target("/park").
                queryParam("start", "2018-10-20T05:01:00Z").
                queryParam("end", "2018-10-21T09:00:00Z").
                request().
                get();

        assertEquals(200, response.getStatus());
        Rate entity = response.readEntity(Rate.class);
        assertEquals(Rate.Unavailable, entity);
    }

    @Test
    public void xml_unavailable() {
        Response response = target("/park").
                queryParam("start", "2018-10-20T05:01:00Z").
                queryParam("end", "2018-10-21T09:00:00Z").
                request().
                accept(MediaType.APPLICATION_XML).
                get();

        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rate><days>unavailable</days><times>0000-2400</times><price>0</price></rate>", response.readEntity(String.class));
    }

    @Test
    public void json_unavailable() {
        Response response = target("/park").
                queryParam("start", "2018-10-20T05:01:00Z").
                queryParam("end", "2018-10-21T09:00:00Z").
                request().
                accept(MediaType.APPLICATION_JSON).
                get();

        assertEquals("{\"days\":\"unavailable\",\"times\":\"0000-2400\",\"price\":\"0\"}", response.readEntity(String.class));
    }

    @Test
    public void xml_available() {
        Response response = target("/park").
                queryParam("start", "2018-10-20T10:00:00Z").
                queryParam("end", "2018-10-20T13:00:00Z").
                request().
                accept(MediaType.APPLICATION_XML).
                get();

        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><rate><days>fri,sat,sun</days><times>0900-2100</times><price>2000</price></rate>", response.readEntity(String.class));
    }

    @Test
    public void json_available() {
        Response response = target("/park").
                queryParam("start", "2018-10-20T10:00:00Z").
                queryParam("end", "2018-10-20T13:00:00Z").
                request().
                accept(MediaType.APPLICATION_JSON).
                get();

        assertEquals("{\"days\":\"fri,sat,sun\",\"times\":\"0900-2100\",\"price\":\"2000\"}", response.readEntity(String.class));
    }
}
