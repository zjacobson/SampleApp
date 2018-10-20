package spothero.demo;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;

class JerseyApplication extends ResourceConfig {
    JerseyApplication() {

        packages("spothero.demo", "spothero.demo.api", "spothero.demo.model");
        register(LoggingFeature.class);
//        JacksonJaxbJsonProvider jacksonJaxbJsonProvider = new JacksonJaxbJsonProvider();
//        jacksonJaxbJsonProvider.setMapper(buildObjectMapper());
//        register(jacksonJaxbJsonProvider);
        register(new AbstractBinder() {
            @Override
            protected void configure() {

                // Shop to manually bind objects, in the case that the Jersey Auto-scan isn't working
                // e.g. bind(x.class).to(y.class).in(Singleton.class);
                // e.g. bind(x.class).to(y.class);
                //
                // note: if the object is generic, use TypeLiteral
                // e.g. bind(x.class).to(new TypeLiteral&lt;InjectionResolver&gt;(){});
                //
            }
        });
    }

//    private ObjectMapper buildObjectMapper() {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.registerModule(new Jaxrs2TypesModule());
//        mapper.registerModule(new ParameterNamesModule());
//        mapper.registerModule(new JavaTimeModule());
//        return mapper;
//    }
}
