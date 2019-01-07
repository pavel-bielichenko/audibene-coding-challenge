package com.audibene.rest.impl;

import com.audibene.rest.DoctorAppointmentResource;
import com.audibene.rest.dto.AppointmentDto;
import com.audibene.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

import static org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST;

@Component
@AllArgsConstructor
@Scope(SCOPE_REQUEST)
public class DoctorAppointmentResourceImpl implements DoctorAppointmentResource {

    private final AppointmentService service;

    @Override
    public Page<AppointmentDto> findBetween(Long doctorId, OffsetDateTime from, OffsetDateTime to, Integer page, Integer size) {
        return service.findAppointmentsBetweenDates(from, to, doctorId, PageRequest.of(page, size));
    }
}
