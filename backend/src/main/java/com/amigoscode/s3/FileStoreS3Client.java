package com.amigoscode.s3;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.utils.IoUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FileStoreS3Client implements S3Client {

    // TODO: Not sure if this will work for Windows users
    private static final String PATH =
            System.getProperty("user.home") + "/.amigoscode/s3";

    @Override
    public String serviceName() {
        return "fake";
    }

    @Override
    public void close() {

    }

    @Override
    public PutObjectResponse putObject(PutObjectRequest putObjectRequest,
                                       RequestBody requestBody)
            throws AwsServiceException, SdkClientException {

        try {
            InputStream inputStream = requestBody.contentStreamProvider().newStream();
            byte[] buffer = IoUtils.toByteArray(inputStream);
            FileUtils.writeByteArrayToFile(
                    new File(getObjectFullPath(
                            putObjectRequest.bucket(),
                            putObjectRequest.key())),
                    buffer
            );
        } catch (IOException e) {
            throw AwsServiceException
                    .builder()
                    .cause(e)
                    .build();
        }

        return PutObjectResponse.builder().build();
    }

    @Override
    public ResponseInputStream<GetObjectResponse> getObject(GetObjectRequest getObjectRequest)
            throws AwsServiceException, SdkClientException {
        try {
            return new ResponseInputStream<>(
                    GetObjectResponse.builder().build(),
                    new FileInputStream(getObjectFullPath(getObjectRequest.bucket(), getObjectRequest.key()))
            );
        } catch (FileNotFoundException e) {
            throw AwsServiceException
                    .builder()
                    .cause(e)
                    .build();
        }
    }

    private String getObjectFullPath(String bucket, String key) {
        return PATH + "/" + bucket + "/" + key;
    }
}
