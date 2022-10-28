package com.algaworks.example.s3.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.net.URL;

@Data
@Validated
@Configuration
@ConfigurationProperties("aw.storage")
public class StorageProperties {

    @Valid
    private S3 s3 = new S3();
    @Valid
    private Image image = new Image();
    @Valid
    private Document document = new Document();

    @Data
    public class S3 {
        @NotBlank
        private String keyId;
        @NotBlank
        private String keySecret;
        @NotBlank
        private String bucket;
        @NotBlank
        private String region;
    }

    @Data
    public class Image {
        @NotNull
        private URL downloadUrl;
    }

    @Data
    public class Document {
        @NotNull
        private URL downloadUrl;
    }
}
