package com.audibene.rest;

import com.audibene.rest.dto.AppointmentDto;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
