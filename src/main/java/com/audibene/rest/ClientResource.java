package com.audibene.rest;

import com.audibene.rest.dto.ClientDto;
import org.springframework.data.domain.Page;

import javax.ws.rs.*;
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

