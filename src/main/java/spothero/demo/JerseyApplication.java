package spothero.demo;

import org.glassfish.jersey.server.ResourceConfig;

public class JerseyApplication extends ResourceConfig {
    public JerseyApplication() {
        packages("spothero.demo.api");
    }

}
