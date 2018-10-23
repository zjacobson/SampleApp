package spothero.demo;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

import java.io.IOException;
import java.net.URI;

public class Main {
    public static void main(String[] args) throws IOException {
        String BASE_URI = "http://localhost:8080/pricing/";
        ServiceLocator locator = ServiceLocatorUtilities.createAndPopulateServiceLocator();

        HttpServer httpServer = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), new JerseyApplication(), locator);

        httpServer.start();

        System.out.println(String.format("Jersey app started. Listening on %s. \nHit enter to stop it...", BASE_URI));
        System.in.read();

    }
}
