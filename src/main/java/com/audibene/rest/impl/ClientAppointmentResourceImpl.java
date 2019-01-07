package com.audibene.rest.impl;

import com.audibene.rest.ClientAppointmentResource;
import com.audibene.rest.dto.AppointmentDto;
import com.audibene.rest.dto.RatingUpdateDto;
import com.audibene.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

import static org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST;

@Component
@AllArgsConstructor
@Scope(SCOPE_REQUEST)
public class ClientAppointmentResourceImpl implements ClientAppointmentResource {

    private final AppointmentService service;

    @Override
    public AppointmentDto findLast(Long clientId) {
        return service.findLastAppointment(clientId, OffsetDateTime.now());
    }

    @Override
    public AppointmentDto findNext(Long clientId) {
        return service.findNextAppointment(clientId, OffsetDateTime.now());
    }

    @Override
    public AppointmentDto rate(Long clientId, Long appointmentId, RatingUpdateDto rating) {
        return service.rate(clientId, appointmentId, rating);
    }
}
