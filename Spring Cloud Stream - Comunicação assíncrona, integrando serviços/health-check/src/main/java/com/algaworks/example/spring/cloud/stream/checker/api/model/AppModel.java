package com.algaworks.example.spring.cloud.stream.checker.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppModel {
    private UUID id;
    private String name;
    private String address;
}
