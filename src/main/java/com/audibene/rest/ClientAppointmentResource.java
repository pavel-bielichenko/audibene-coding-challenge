package com.audibene.rest;

import com.audibene.rest.dto.AppointmentDto;
import com.audibene.rest.dto.RatingUpdateDto;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
