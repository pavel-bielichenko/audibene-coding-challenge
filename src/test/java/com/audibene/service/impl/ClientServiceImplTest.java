package com.audibene.service.impl;

import com.audibene.AudibeneApplication;
import com.audibene.domain.Client;
import com.audibene.repository.ClientRepository;
import com.audibene.rest.dto.ClientDto;
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
public class ClientServiceImplTest {

    private static final Long ID = 1L;
    private static final String NAME = "Joker";

    private Client entity = mockClient();

    private ClientDto dto = mockClientDto();

    @Mock
    private ClientRepository repository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(ClientServiceImplTest.class);
    }

    @Test
    public void shouldConvertDtoToEntity() {
        Client actual = clientService.mapDto(dto);
        assertThat(actual, equalTo(entity));
    }

    @Test
    public void shouldConvertEntityToDto() {
        ClientDto actual = clientService.mapEntity(entity);
        assertThat(actual, equalTo(dto));
    }

    @Test
    public void shouldConvertPageableToDtoPageable() {
        Pageable pageable = PageRequest.of(0, 10);
        PageImpl<Client> page = new PageImpl<>(singletonList(entity), pageable, 12);

        Page<ClientDto> actual = clientService.mapEntityPage(page);

        assertThat(actual, is(new PageImpl<>(singletonList(dto), pageable, 12)));
    }


    @Test
    public void shouldSaveClientAndConvertResultToDto() {
        when(repository.save(entity)).thenReturn(entity);

        ClientDto actual = clientService.save(dto);
        assertThat(actual, equalTo(dto));
        verify(repository).save(entity);
    }

    @Test
    public void shouldGetOneAndConvertResultToDto() {
        when(repository.getOne(ID)).thenReturn(entity);

        ClientDto actual = clientService.getOne(ID);
        assertThat(actual, equalTo(dto));
        verify(repository).getOne(ID);
    }

    @Test
    public void shouldEditClientAndConvertResultToDto() {
        when(repository.saveAndFlush(entity)).thenReturn(entity);

        ClientDto actual = clientService.update(dto);
        assertThat(actual, equalTo(dto));
        verify(repository).saveAndFlush(entity);
    }

    @Test
    public void shouldFinAllAndConvertResultToDto() {
        Pageable pageable = PageRequest.of(0, 10);
        PageImpl<Client> page = new PageImpl<>(singletonList(entity), pageable, 10);
        when(repository.findAll(pageable)).thenReturn(page);

        Page<ClientDto> result = clientService.findAll(0, 10);
        assertThat(result, equalTo(new PageImpl<>(singletonList(dto), pageable, 10)));
    }

    private static ClientDto mockClientDto() {
        return new ClientDto(ID, NAME);
    }


    private static Client mockClient() {
        return Client.builder()
                .id(ID)
                .name(NAME)
                .build();
    }
}