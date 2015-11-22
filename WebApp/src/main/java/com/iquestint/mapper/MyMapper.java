package com.iquestint.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vladu
 */
public abstract class MyMapper<S, D> {

    @Autowired
    private ModelMapper modelMapper;

    private Class<S> sourcePersistentClass = (Class<S>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    private Class<D> destinationPersistentClass = (Class<D>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];

    public <D> D map(S source) {
        return modelMapper.map(source, (Class<D>) destinationPersistentClass);
    }

    public <S> S reverseMap(D destination) {
        return modelMapper.map(destination, (Class<S>) sourcePersistentClass);
    }

    public <D> List<D> mapList(List<S> sourceList) {
        List<D> destinationList = new ArrayList<>();

        for (S source : sourceList) {
            destinationList.add(modelMapper.map(source, (Class<D>) destinationPersistentClass));
        }

        return destinationList;
    }

    public <S> List<S> reverseMapList(List<D> destinationList) {
        List<S> sourceList = new ArrayList<>();

        for (D destination : destinationList) {
            sourceList.add(modelMapper.map(destination, (Class<S>) sourcePersistentClass));
        }

        return sourceList;
    }

}
