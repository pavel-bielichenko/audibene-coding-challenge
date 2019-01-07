package com.audibene.rest;

import com.audibene.rest.dto.DoctorDto;
import org.springframework.data.domain.Page;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/v1/doctors")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface DoctorResource {

    @POST
    DoctorDto create(DoctorDto doctor);

    @GET
    @Path("{id}")
    DoctorDto find(@PathParam("id") Long id);

    @PUT
    DoctorDto edit(DoctorDto doctor);

    @GET
    Page<DoctorDto> findAll(
            @DefaultValue("0") @QueryParam("page") Integer page,
            @DefaultValue("10") @QueryParam("size") Integer size
    );
}
