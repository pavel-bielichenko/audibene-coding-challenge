package com.audibene.service;

import com.audibene.domain.Appointment;
import com.audibene.rest.dto.AppointmentDto;
import com.audibene.rest.dto.RatingUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.OffsetDateTime;

public interface AppointmentService extends ServiceMapperHelper<Appointment, AppointmentDto, Long> {

    AppointmentDto findNextAppointmentForDoctorAndClient(Long doctorId, Long clientId, OffsetDateTime now);

    Page<AppointmentDto> findAppointmentsBetweenDates(OffsetDateTime startsAt, OffsetDateTime endsAt, Long clientId, Pageable pageable);

    AppointmentDto findNextAppointment(Long doctorId, OffsetDateTime now);

    AppointmentDto findLastAppointment(Long clientId, OffsetDateTime now);

    AppointmentDto rate(Long clientId, Long id, RatingUpdateDto rating);
}
