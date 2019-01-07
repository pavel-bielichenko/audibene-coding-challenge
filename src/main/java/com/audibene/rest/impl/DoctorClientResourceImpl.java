package com.audibene.rest.impl;

import com.audibene.rest.DoctorClientResource;
import com.audibene.rest.dto.AppointmentDto;
import com.audibene.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

import static org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST;

@Component
@AllArgsConstructor
@Scope(SCOPE_REQUEST)
public class DoctorClientResourceImpl implements DoctorClientResource {

    private final AppointmentService service;

    @Override
    public AppointmentDto findNext(Long doctorId, Long clientId) {
        return service.findNextAppointmentForDoctorAndClient(doctorId, clientId, OffsetDateTime.now());
    }
}
