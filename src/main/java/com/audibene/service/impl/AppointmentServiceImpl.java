package com.audibene.service.impl;

import com.audibene.domain.Appointment;
import com.audibene.repository.AppointmentRepository;
import com.audibene.rest.dto.AppointmentDto;
import com.audibene.rest.dto.RatingUpdateDto;
import com.audibene.service.AppointmentService;
import com.audibene.service.GenericServiceMapperHelper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.Optional;

@Component
@Transactional
@AllArgsConstructor
public class AppointmentServiceImpl extends GenericServiceMapperHelper<Appointment, AppointmentDto, Long> implements AppointmentService {

    private final AppointmentRepository repository;

    @Override
    protected Class<Appointment> getEntityClass() {
        return Appointment.class;
    }

    @Override
    protected Class<AppointmentDto> getDtoClass() {
        return AppointmentDto.class;
    }

    @Override
    protected JpaRepository<Appointment, Long> getRepository() {
        return repository;
    }

    @Override
    public AppointmentDto findNextAppointmentForDoctorAndClient(Long doctorId, Long clientId, OffsetDateTime now) {
        return mapEntity(repository.findFirstByDoctorIdAndClientIdAndStartsAtAfterOrderByStartsAtAsc(doctorId, clientId, now));
    }

    @Override
    public Page<AppointmentDto> findAppointmentsBetweenDates(OffsetDateTime startsAt, OffsetDateTime endsAt, Long clientId, Pageable pageable) {
        return mapEntityPage(repository.findAppointmentByStartsAtAfterAndEndsAtBeforeAndDoctorId(startsAt, endsAt, clientId, pageable));
    }

    @Override
    public AppointmentDto findNextAppointment(Long clientId, OffsetDateTime now) {
        return mapEntity(repository.findFirstByClientIdAndStartsAtAfterOrderByStartsAtAsc(clientId, now));
    }

    @Override
    public AppointmentDto findLastAppointment(Long clientId, OffsetDateTime now) {
        return mapEntity(repository.findFirstByClientIdAndEndsAtIsBeforeOrderByEndsAtDesc(clientId, now));
    }

    @Override
    public AppointmentDto rate(Long clientId, Long id, RatingUpdateDto rating) {
        //refactoring needed
        Optional.ofNullable(repository.findFirstByClientIdAndId(clientId, id))
                .orElseThrow(() -> new EntityNotFoundException("Appointment with id: " + id + " was not registered in system"));
        Appointment appointment = repository.getOne(id);
        appointment.setRating(rating.getRating());
        return mapEntity(repository.saveAndFlush(appointment));
    }
}
