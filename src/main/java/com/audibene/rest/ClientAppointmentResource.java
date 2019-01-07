package com.audibene.rest;

import com.audibene.rest.dto.AppointmentDto;
import com.audibene.rest.dto.RatingUpdateDto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/v1/clients/{client-id}/appointments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ClientAppointmentResource {

    @GET
    @Path("/last")
    AppointmentDto findLast(@PathParam("client-id") Long clientId);

    @GET
    @Path("/next")
    AppointmentDto findNext(@PathParam("client-id") Long clientId);

    @PUT
    @Path("/{appointment-id}/rate")
    AppointmentDto rate(
            @PathParam("client-id") Long clientId,
            @PathParam("appointment-id") Long appointmentId,
            RatingUpdateDto rating
    );
}
