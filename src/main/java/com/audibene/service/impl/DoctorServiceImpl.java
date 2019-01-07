package com.audibene.service.impl;

import com.audibene.domain.Doctor;
import com.audibene.repository.DoctorRepository;
import com.audibene.rest.dto.DoctorDto;
import com.audibene.service.DoctorService;
import com.audibene.service.GenericServiceMapperHelper;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
@AllArgsConstructor
public class DoctorServiceImpl extends GenericServiceMapperHelper<Doctor, DoctorDto, Long> implements DoctorService {

    private final DoctorRepository repository;

    @Override
    protected Class<Doctor> getEntityClass() {
        return Doctor.class;
    }

    @Override
    protected Class<DoctorDto> getDtoClass() {
        return DoctorDto.class;
    }

    @Override
    protected JpaRepository getRepository() {
        return repository;
    }
}
