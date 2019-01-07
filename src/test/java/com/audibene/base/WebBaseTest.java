package com.audibene.base;

import com.audibene.AudibeneApplication;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.logging.LoggingFeature;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles({"test"})
@ContextConfiguration(classes = {AudibeneApplication.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class WebBaseTest {

    private final static String LOCALHOST = "http://localhost";


    @LocalServerPort
    protected int serverPort;
    protected WebTarget webTarget;
    private Client client;

    @Before
    public void setup() {
        ClientConfig clientConfig = new ClientConfig()
                .register(JacksonJaxbJsonProvider.class)
                .property(ClientProperties.SUPPRESS_HTTP_COMPLIANCE_VALIDATION, true);

        client = ClientBuilder.newClient(clientConfig);
        client.register(new LoggingFeature(Logger.getLogger("com.audibene"), Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, 100000));
        webTarget = client.target(UriBuilder.fromUri(LOCALHOST).port(serverPort).build());
    }

    @After
    public void cleanup() {
        Optional.ofNullable(client).ifPresent(Client::close);
    }
}
