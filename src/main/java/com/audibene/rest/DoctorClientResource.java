package com.audibene.rest;

import com.audibene.rest.dto.AppointmentDto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/v1/doctors/{doctor-id}/clients/{client-id}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface DoctorClientResource {

    @GET
    AppointmentDto findNext(
            @PathParam("doctor-id") Long doctorId,
            @PathParam("client-id") Long clientId
    );

}
