package com.audibene.rest.impl;

import com.audibene.rest.ClientResource;
import com.audibene.rest.dto.ClientDto;
import com.audibene.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import static org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST;

@Component
@AllArgsConstructor
@Scope(SCOPE_REQUEST)
public class ClientResourceImpl implements ClientResource {

    private final ClientService service;

    @Override
    public ClientDto create(ClientDto client) {
        return service.save(client);
    }

    @Override
    public ClientDto find(Long id) {
        return service.getOne(id);
    }

    @Override
    public ClientDto edit(ClientDto client) {
        return service.update(client);
    }

    @Override
    public Page<ClientDto> findAll(Integer page, Integer size) {
        return service.findAll(page, size);
    }
}
