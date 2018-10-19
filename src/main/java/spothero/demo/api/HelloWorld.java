package spothero.demo.api;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import spothero.demo.model.Response;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/helloworld")
public class HelloWorld {

    @GET
    @Produces("text/plain")
    public String getClichedMessage() {
        return "Hello World";
    }

    @GET
    @Produces("application/json")
    public String getJSONMessage() throws JsonProcessingException {
        Response helloWorld = new Response("hello world");
        ObjectMapper jsonMapper = new ObjectMapper();

        return jsonMapper.writeValueAsString(helloWorld);
    }

    @GET
    @Produces("application/xml")
    public String getXMLMessage() throws JsonProcessingException {
        Response helloWorld = new Response("hello world");

        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.writeValueAsString(helloWorld);
    }

}
