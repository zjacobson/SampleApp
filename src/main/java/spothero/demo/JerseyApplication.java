package spothero.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jaxrs.Jaxrs2TypesModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

public class JerseyApplication extends ResourceConfig {
    public JerseyApplication() {

        packages("spothero.demo");

        JacksonJaxbJsonProvider jacksonJaxbJsonProvider = new JacksonJaxbJsonProvider();
        jacksonJaxbJsonProvider.setMapper(buildObjectMapper());
        register(jacksonJaxbJsonProvider);
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

    private ObjectMapper buildObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jaxrs2TypesModule());
        mapper.registerModule(new ParameterNamesModule());
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }
}
