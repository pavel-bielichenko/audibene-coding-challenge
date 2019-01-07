package com.audibene.service.impl;

import com.audibene.AudibeneApplication;
import com.audibene.domain.Appointment;
import com.audibene.domain.Rating;
import com.audibene.repository.AppointmentRepository;
import com.audibene.rest.dto.AppointmentDto;
import com.audibene.rest.dto.RatingUpdateDto;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.OffsetDateTime;

import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AudibeneApplication.class)
@FixMethodOrder(NAME_ASCENDING)
public class AppointmentServiceImplTest {

    private static final Long ID = 1L;
    private static final OffsetDateTime now = OffsetDateTime.now();

    private Appointment entity = mockAppointment(now, now);

    private AppointmentDto dto = mockAppointmentDto(now, now);

    @Mock
    private AppointmentRepository repository;

    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(AppointmentServiceImplTest.class);
    }

    @Test
    public void shouldConvertDtoToEntity() {
        Appointment actual = appointmentService.mapDto(dto);
        assertThat(actual, equalTo(entity));
    }

    @Test
    public void shouldConvertEntityToDto() {
        AppointmentDto actual = appointmentService.mapEntity(entity);
        assertThat(actual, equalTo(dto));
    }

    @Test
    public void shouldConvertPageableToDtoPageable() {
        Pageable pageable = PageRequest.of(0, 10);
        PageImpl<Appointment> page = new PageImpl<>(singletonList(entity), pageable, 12);

        Page<AppointmentDto> actual = appointmentService.mapEntityPage(page);

        assertThat(actual, is(new PageImpl<>(singletonList(dto), pageable, 12)));
    }


    @Test
    public void shouldSaveAppointmentAndConvertResultToDto() {
        when(repository.save(entity)).thenReturn(entity);

        AppointmentDto actual = appointmentService.save(dto);
        assertThat(actual, equalTo(dto));
        verify(repository).save(entity);
    }

    @Test
    public void shouldGetOneAndConvertResultToDto() {
        when(repository.getOne(ID)).thenReturn(entity);

        AppointmentDto actual = appointmentService.getOne(ID);
        assertThat(actual, equalTo(dto));
        verify(repository).getOne(ID);
    }

    @Test
    public void shouldEditAppointmentAndConvertResultToDto() {
        when(repository.saveAndFlush(entity)).thenReturn(entity);

        AppointmentDto actual = appointmentService.update(dto);
        assertThat(actual, equalTo(dto));
        verify(repository).saveAndFlush(entity);
    }

    @Test
    public void shouldFinAllAndConvertResultToDto() {
        Pageable pageable = PageRequest.of(0, 10);
        PageImpl<Appointment> page = new PageImpl<>(singletonList(entity), pageable, 10);
        when(repository.findAll(pageable)).thenReturn(page);

        Page<AppointmentDto> result = appointmentService.findAll(0, 10);
        assertThat(result, is(new PageImpl<>(singletonList(dto), pageable, 10)));
    }

    @Test
    public void shouldFinNextAppointmentForDoctorAndClient() {
        when(repository.findFirstByDoctorIdAndClientIdAndStartsAtAfterOrderByStartsAtAsc(ID, ID, now)).thenReturn(entity);

        AppointmentDto actual = appointmentService.findNextAppointmentForDoctorAndClient(ID, ID, now);
        assertThat(actual, equalTo(dto));
        verify(repository).findFirstByDoctorIdAndClientIdAndStartsAtAfterOrderByStartsAtAsc(ID, ID, now);
    }

    @Test
    public void shouldFinAllBetweenDatesAndConvertResultToDto() {
        Pageable pageable = PageRequest.of(0, 10);
        PageImpl<Appointment> page = new PageImpl<>(singletonList(entity), pageable, 10);
        when(repository.findAppointmentByStartsAtAfterAndEndsAtBeforeAndDoctorId(now, now, ID, pageable)).thenReturn(page);

        Page<AppointmentDto> result = appointmentService.findAppointmentsBetweenDates(now, now, ID, pageable);
        assertThat(result, is(new PageImpl<>(singletonList(dto), pageable, 10)));
    }

    @Test
    public void shouldFinNextAppointmentForClient() {
        when(repository.findFirstByClientIdAndStartsAtAfterOrderByStartsAtAsc(ID, now)).thenReturn(entity);

        AppointmentDto actual = appointmentService.findNextAppointment(ID, now);
        assertThat(actual, equalTo(dto));
        verify(repository).findFirstByClientIdAndStartsAtAfterOrderByStartsAtAsc(ID, now);
    }

    @Test
    public void shouldFinLastAppointmentForClient() {
        when(repository.findFirstByClientIdAndEndsAtIsBeforeOrderByEndsAtDesc(ID, now)).thenReturn(entity);

        AppointmentDto actual = appointmentService.findLastAppointment(ID, now);
        assertThat(actual, equalTo(dto));
        verify(repository).findFirstByClientIdAndEndsAtIsBeforeOrderByEndsAtDesc(ID, now);
    }

    @Test
    public void shouldRateAppointmentForClient() {
        when(repository.findFirstByClientIdAndId(ID, ID)).thenReturn(entity);
        when(repository.getOne(ID)).thenReturn(entity);
        entity.setRating(Rating.FIVE);
        dto.setRating(Rating.FIVE);
        when(repository.saveAndFlush(entity)).thenReturn(entity);

        AppointmentDto actual = appointmentService.rate(ID, ID, new RatingUpdateDto(Rating.FIVE));
        assertThat(actual, equalTo(dto));
        verify(repository).saveAndFlush(entity);
    }

    private static AppointmentDto mockAppointmentDto(OffsetDateTime start, OffsetDateTime end) {
        return new AppointmentDto(ID, ID, ID, start, end, null);
    }

    private static Appointment mockAppointment(OffsetDateTime start, OffsetDateTime end) {
        return Appointment.builder()
                .id(ID)
                .clientId(ID)
                .doctorId(ID)
                .startsAt(start)
                .endsAt(end)
                .build();
    }
}