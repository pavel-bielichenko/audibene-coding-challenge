package com.audibene.rest;

import com.audibene.rest.dto.AppointmentDto;
import org.springframework.data.domain.Page;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/v1/appointments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface AppointmentResource {

    @POST
    AppointmentDto create(AppointmentDto appointment);

    @GET
    @Path("{id}")
    AppointmentDto find(@PathParam("id") Long id);

    @PUT
    AppointmentDto edit(AppointmentDto appointment);

    @GET
    Page<AppointmentDto> findAll(
            @DefaultValue("0") @QueryParam("page") Integer page,
            @DefaultValue("10") @QueryParam("size") Integer size
    );
}
