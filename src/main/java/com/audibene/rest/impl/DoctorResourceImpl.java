package com.audibene.rest.impl;

import com.audibene.rest.DoctorResource;
import com.audibene.rest.dto.DoctorDto;
import com.audibene.service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import static org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST;

@Component
@AllArgsConstructor
@Scope(SCOPE_REQUEST)
public class DoctorResourceImpl implements DoctorResource {

    @Autowired
    private DoctorService service;

    @Override
    public DoctorDto create(DoctorDto doctor) {
        return service.save(doctor);
    }

    @Override
    public DoctorDto find(Long id) {
        return service.getOne(id);
    }

    @Override
    public DoctorDto edit(DoctorDto doctor) {
        return service.update(doctor);
    }

    @Override
    public Page<DoctorDto> findAll(Integer page, Integer size) {
        return service.findAll(page, size);
    }
}
