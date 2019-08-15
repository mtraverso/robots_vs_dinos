package ar.com.nubank.configuration;

import ar.com.nubank.rest.DinosaurRestService;
import ar.com.nubank.rest.GridRestService;
import ar.com.nubank.rest.RobotRestService;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig(){
        register(GridRestService.class);
        register(RobotRestService.class);
        register(DinosaurRestService.class);
    }
}
