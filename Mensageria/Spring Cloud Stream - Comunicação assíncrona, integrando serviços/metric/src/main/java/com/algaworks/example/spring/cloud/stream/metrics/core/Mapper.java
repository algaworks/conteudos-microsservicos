package com.algaworks.example.spring.cloud.stream.metrics.core;

public interface Mapper {
    <D> D map(Object source, Class<D> destinationType);
}
