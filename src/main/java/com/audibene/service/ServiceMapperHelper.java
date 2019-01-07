package com.audibene.service;

import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ServiceMapperHelper<E, D, ID> {

    Page<D> findAll(Integer page, Integer size);

    D getOne(ID id);

    Optional<E> findById(ID id);

    D save(D dto);

    D update(D dto);

    D mapEntity(E entity);

    E mapDto(D dto);

    Page<D> mapEntityPage(Page<E> entities);
}
