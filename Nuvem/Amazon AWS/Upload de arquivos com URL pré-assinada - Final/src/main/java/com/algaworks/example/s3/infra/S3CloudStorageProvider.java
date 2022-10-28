package com.algaworks.example.s3.infra;

import com.algaworks.example.s3.core.properties.StorageProperties;
import com.algaworks.example.s3.domain.exception.StorageCloudException;
import com.algaworks.example.s3.domain.model.FileReference;
import com.algaworks.example.s3.domain.service.CloudStorageProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.awscore.AwsRequestOverrideConfiguration;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.net.URL;
import java.time.Duration;

@Component
@AllArgsConstructor
@Slf4j
public class S3CloudStorageProvider implements CloudStorageProvider {

    private final S3Client s3Client;
    private final S3Presigner s3Presigner;
    private final StorageProperties storageProperties;

    @Override
    public URL generatePresignedUploadUrl(FileReference fileReference) {
        var builder = AwsRequestOverrideConfiguration.builder();

        if(fileReference.isPublicAccessible()) {
            builder.putRawQueryParameter("x-amz-acl", "public-read");
        }

        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(getBucket())
                .key(fileReference.getPath())
                .contentType(fileReference.getContentType())
                .contentLength(fileReference.getContentLength())
                .acl(fileReference.isPublicAccessible() ? "public-read" : null)
                .overrideConfiguration(builder.build())
                .build();

        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(30))
                .putObjectRequest(objectRequest)
                .build();

        return s3Presigner.presignPutObject(presignRequest).url();
    }

    @Override
    public URL generatePresignedDownloadUrl(FileReference fileReference) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(getBucket())
                .key(fileReference.getPath())
                .build();

        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(30))
                .getObjectRequest(getObjectRequest)
                .build();

        return s3Presigner.presignGetObject(presignRequest).url();
    }

    @Override
    public boolean fileExists(String filePath) {
        if (filePath == null || filePath.isEmpty()) {
            return false;
        }
        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(getBucket())
                .key(filePath)
                .build();

        try {
            s3Client.getObject(request);
            return true;
        } catch (NoSuchKeyException e) {
            log.warn(String.format("Arquivo não encontrado na nuvem %s", filePath));
            return false;
        }
    }

    @Override
    public void moveFile(String fromPath, String toPath) {
        CopyObjectRequest copyObjectRequest = CopyObjectRequest.builder()
                .sourceKey(fromPath)
                .destinationKey(toPath)
                .sourceBucket(getBucket())
                .destinationBucket(getBucket())
                .build();

        try {
            s3Client.copyObject(copyObjectRequest);
        } catch (S3Exception e) {
            log.error(String.format("Erro ao copiar o arquivo %s para %s", fromPath, toPath), e);
            throw new StorageCloudException(String.format("Erro ao copiar o arquivo %s para %s", fromPath, toPath));
        }

        removeFile(fromPath);
    }

    @Override
    public void removeFile(String filePath) {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(getBucket())
                .key(filePath)
                .build();

        try {
            s3Client.deleteObject(deleteObjectRequest);
        } catch (S3Exception e) {
            log.error(String.format("Erro ao remover arquivo %s", filePath), e);
            throw new StorageCloudException(String.format("Erro ao remover arquivo %s", filePath));
        }
    }

    private String getBucket() {
        return storageProperties.getS3().getBucket();
    }
}
