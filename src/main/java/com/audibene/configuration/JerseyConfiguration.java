package com.audibene.configuration;

import com.audibene.provider.OffsetDateTimeProvider;
import com.audibene.rest.*;
import com.fasterxml.jackson.jaxrs.base.JsonParseExceptionMapper;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@ApplicationPath("api")
public class JerseyConfiguration extends ResourceConfig {

    public JerseyConfiguration() {
        property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, true);
        register(DoctorClientResource.class);
        register(DoctorAppointmentResource.class);
        register(ClientAppointmentResource.class);
        register(AppointmentResource.class);
        register(ClientResource.class);
        register(DoctorResource.class);
        register(new LoggingFeature(Logger.getLogger("com.audibene.rest"), Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, 1000));
        register(JsonParseExceptionMapper.class);
        register(OffsetDateTimeProvider.class);
    }

}
