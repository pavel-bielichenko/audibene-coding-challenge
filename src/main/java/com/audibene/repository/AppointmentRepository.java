package com.audibene.repository;

import com.audibene.domain.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Transactional
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Appointment findFirstByDoctorIdAndClientIdAndStartsAtAfterOrderByStartsAtAsc(Long doctorId, Long clientId, OffsetDateTime now);

    Page<Appointment> findAppointmentByStartsAtAfterAndEndsAtBeforeAndDoctorId(OffsetDateTime startsAt, OffsetDateTime endsAt, Long clientId, Pageable pageable);

    Appointment findFirstByClientIdAndStartsAtAfterOrderByStartsAtAsc(Long clientId, OffsetDateTime now);

    Appointment findFirstByClientIdAndEndsAtIsBeforeOrderByEndsAtDesc(Long clientId, OffsetDateTime now);

    Appointment findFirstByClientIdAndId(Long clientId, Long id);
}
