package com.audibene.rest;

import com.audibene.rest.dto.ClientDto;
import org.springframework.data.domain.Page;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/v1/clients")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ClientResource {

    @POST
    ClientDto create(ClientDto client);

    @GET
    @Path("{id}")
    ClientDto find(@PathParam("id") Long id);

    @PUT
    ClientDto edit(ClientDto client);

    @GET
    Page<ClientDto> findAll(
            @DefaultValue("0") @QueryParam("page") Integer page,
            @DefaultValue("10") @QueryParam("size") Integer size
    );
}

