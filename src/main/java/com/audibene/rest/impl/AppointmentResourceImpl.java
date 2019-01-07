package com.audibene.rest.impl;

import com.audibene.rest.AppointmentResource;
import com.audibene.rest.dto.AppointmentDto;
import com.audibene.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import static org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST;

@Component
@AllArgsConstructor
@Scope(SCOPE_REQUEST)
public class AppointmentResourceImpl implements AppointmentResource {

    private final AppointmentService service;

    @Override
    public AppointmentDto create(AppointmentDto appointment) {
        return service.save(appointment);
    }

    @Override
    public AppointmentDto find(Long id) {
        return service.getOne(id);
    }

    @Override
    public AppointmentDto edit(AppointmentDto appointment) {
        return service.update(appointment);
    }

    @Override
    public Page<AppointmentDto> findAll(Integer page, Integer size) {
        return service.findAll(page, size);
    }

}
