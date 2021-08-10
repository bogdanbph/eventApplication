package com.erykandbogdan.eventapp.web.mapper;

import com.erykandbogdan.eventapp.model.base.BaseEntity;
import com.erykandbogdan.eventapp.web.dto.BaseDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface BaseMapper<D extends BaseDto, E extends BaseEntity> {
    D convert(E entity);

    default D convert(Optional<E> optional) {
        return optional.isPresent() ? convert(optional.get()) : null;
    }

    E convert(D dto);

    default List<D> convertEntities(List<E> entities) {
        return entities.stream().map(this::convert).collect(Collectors.toList());
    }

    default List<E> covertDtos(List<D> dtos) {
        return dtos.stream().map(this::convert).collect(Collectors.toList());
    }
}
