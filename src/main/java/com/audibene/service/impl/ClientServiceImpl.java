package com.audibene.service.impl;

import com.audibene.domain.Client;
import com.audibene.repository.ClientRepository;
import com.audibene.rest.dto.ClientDto;
import com.audibene.service.ClientService;
import com.audibene.service.GenericServiceMapperHelper;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
@AllArgsConstructor
public class ClientServiceImpl extends GenericServiceMapperHelper<Client, ClientDto, Long> implements ClientService {

    private final ClientRepository repository;

    @Override
    protected Class<Client> getEntityClass() {
        return Client.class;
    }

    @Override
    protected Class<ClientDto> getDtoClass() {
        return ClientDto.class;
    }

    @Override
    protected JpaRepository getRepository() {
        return repository;
    }
}
