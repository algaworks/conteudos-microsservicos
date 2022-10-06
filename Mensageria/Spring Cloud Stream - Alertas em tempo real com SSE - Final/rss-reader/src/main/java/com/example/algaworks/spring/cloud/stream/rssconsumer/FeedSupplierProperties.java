package com.example.algaworks.spring.cloud.stream.rssconsumer;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.net.URI;

@Data
@Component
public class FeedSupplierProperties {
    private URI feedUri = URI.create("http://localhost:5000/feed");
    private String metadataKey = "guid";
}
