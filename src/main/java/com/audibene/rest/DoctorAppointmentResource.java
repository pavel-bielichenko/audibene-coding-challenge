package com.audibene.rest;

import com.audibene.rest.dto.AppointmentDto;
import org.springframework.data.domain.Page;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.time.OffsetDateTime;

@Path("/v1/doctors/{doctor-id}/appointments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface DoctorAppointmentResource {

    @GET
    Page<AppointmentDto> findBetween(
            @PathParam("doctor-id") Long doctorId,
            @QueryParam("from") OffsetDateTime from,
            @QueryParam("to") OffsetDateTime to,
            @DefaultValue("0") @QueryParam("page") Integer page,
            @DefaultValue("10") @QueryParam("size") Integer size
    );
}
