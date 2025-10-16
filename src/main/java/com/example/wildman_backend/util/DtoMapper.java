package com.example.wildman_backend.util;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DtoMapper {

    private final ModelMapper modelMapper;

    public <M, D> M toModel(D dto, Class<M> destinationClass) {
        return modelMapper.map(dto, destinationClass);
    }

    public <M, D> D toDto(M model, Class<D> destinationClass) {
        return modelMapper.map(model, destinationClass);
    }

    public <M, D> List<D> toDto(List<M> models, Class<D> destinationClass) {
        List<D> dtos = new ArrayList<>();
        for (M model : models) {
            dtos.add(toDto(model, destinationClass));
        }
        return dtos;
    }
}