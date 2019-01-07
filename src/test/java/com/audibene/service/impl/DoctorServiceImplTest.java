package com.audibene.service.impl;

import com.audibene.AudibeneApplication;
import com.audibene.domain.Doctor;
import com.audibene.repository.DoctorRepository;
import com.audibene.rest.dto.DoctorDto;
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
public class DoctorServiceImplTest {

    private static final Long ID = 1L;
    private static final String NAME = "Bruce";

    private Doctor entity = mockDoctor();

    private DoctorDto dto = mockDoctorDto();

    @Mock
    private DoctorRepository repository;

    @InjectMocks
    private DoctorServiceImpl doctorService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(DoctorServiceImplTest.class);
    }

    @Test
    public void shouldConvertDtoToEntity() {
        Doctor actual = doctorService.mapDto(dto);
        assertThat(actual, equalTo(entity));
    }

    @Test
    public void shouldConvertEntityToDto() {
        DoctorDto actual = doctorService.mapEntity(entity);
        assertThat(actual, equalTo(dto));
    }

    @Test
    public void shouldConvertPageableToDtoPageable() {
        Pageable pageable = PageRequest.of(0, 10);
        PageImpl<Doctor> page = new PageImpl<>(singletonList(entity), pageable, 12);

        Page<DoctorDto> actual = doctorService.mapEntityPage(page);

        assertThat(actual, is(new PageImpl<>(singletonList(dto), pageable, 12)));
    }


    @Test
    public void shouldSaveDoctorAndConvertResultToDto() {
        when(repository.save(entity)).thenReturn(entity);

        DoctorDto actual = doctorService.save(dto);
        assertThat(actual, equalTo(dto));
        verify(repository).save(entity);
    }

    @Test
    public void shouldGetOneAndConvertResultToDto() {
        when(repository.getOne(ID)).thenReturn(entity);

        DoctorDto actual = doctorService.getOne(ID);
        assertThat(actual, equalTo(dto));
        verify(repository).getOne(ID);
    }

    @Test
    public void shouldEditDoctorAndConvertResultToDto() {
        when(repository.saveAndFlush(entity)).thenReturn(entity);

        DoctorDto actual = doctorService.update(dto);
        assertThat(actual, equalTo(dto));
        verify(repository).saveAndFlush(entity);
    }

    @Test
    public void shouldFinAllAndConvertResultToDto() {
        Pageable pageable = PageRequest.of(0, 10);
        PageImpl<Doctor> page = new PageImpl<>(singletonList(entity), pageable, 10);
        when(repository.findAll(pageable)).thenReturn(page);

        Page<DoctorDto> result = doctorService.findAll(0, 10);
        assertThat(result, equalTo(new PageImpl<>(singletonList(dto), pageable, 10)));
    }

    private static DoctorDto mockDoctorDto() {
        return new DoctorDto(ID, NAME);
    }


    private static Doctor mockDoctor() {
        return Doctor.builder()
                .id(ID)
                .name(NAME)
                .build();
    }
}