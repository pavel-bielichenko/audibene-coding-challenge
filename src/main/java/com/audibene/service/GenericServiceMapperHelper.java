package com.audibene.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
public abstract class GenericServiceMapperHelper<E, D, ID> implements ServiceMapperHelper<E, D, ID> {

    private static ModelMapper modelMapper = new ModelMapper();

    protected abstract Class<E> getEntityClass();

    protected abstract Class<D> getDtoClass();

    protected abstract JpaRepository<E, ID> getRepository();

    @Override
    public Page<D> findAll(Integer page, Integer size) {
        return mapEntityPage(getRepository().findAll(PageRequest.of(page, size)));
    }

    @Override
    public D getOne(ID id) {
        return mapEntity(getRepository().getOne(id));
    }

    @Override
    public Optional<E> findById(ID id) {
        return getRepository().findById(id);
    }

    @Override
    public D save(D dto) {
        E entity = mapDto(dto);
        return mapEntity(getRepository().save(entity));
    }

    @Override
    public D update(D dto) {
        return mapEntity(getRepository().saveAndFlush(mapDto(dto)));
    }

    @Override
    public D mapEntity(E entity) {
        return modelMapper.map(entity, getDtoClass());
    }

    @Override
    public E mapDto(D dto) {
        return modelMapper.map(dto, getEntityClass());
    }

    @Override
    public Page<D> mapEntityPage(Page<E> entities) {
        List<D> content = entities.getContent().stream()
                .map(e -> new ModelMapper().map(e, getDtoClass()))
                .collect(Collectors.toList());
        PageRequest pageable = PageRequest.of(entities.getNumber(), entities.getSize());
        return new PageImpl<>(content, pageable, entities.getTotalElements());
    }

}
