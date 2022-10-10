package com.algaworks.example.spring.cloud.stream.metrics.config;

import com.algaworks.example.spring.cloud.stream.metrics.core.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public Mapper mapper() {
        final ModelMapper modelMapper = new ModelMapper();
        return new Mapper() {
            @Override
            public <D> D map(Object source, Class<D> destinationType) {
                return modelMapper.map(source, destinationType);
            }
        };
    }

}