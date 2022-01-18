package com.uploadfile.services.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class StorageService {
  private final AmazonS3 amazonS3;

  public StorageService(AmazonS3 amazonS3) {
    this.amazonS3 = amazonS3;
  }

  @Value("${application.bucket.name}")
  private String bucketName;

  public String uploadFile(MultipartFile file) {
    var fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
    var fileObj = converterMultiPartFileToFile(file);
    var awsObj = new PutObjectRequest(bucketName, fileName, fileObj);
    amazonS3.putObject(awsObj);
    fileObj.delete();
    return fileName;
  }

  public URI viewFile(String fileName) {
    var obj = amazonS3.getObject(bucketName, fileName);
    return obj.getObjectContent().getHttpRequest().getURI();
  }

  public byte[] downloadFile(String fileName) {
    var s3Obj = amazonS3.getObject(bucketName, fileName);
    var inputStream = s3Obj.getObjectContent();
    try {
      return IOUtils.toByteArray(inputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public String deleteFile(String fileName) {
    amazonS3.deleteObject(bucketName, fileName);
    return fileName;
  }

  private File converterMultiPartFileToFile(MultipartFile file) {
    var fileConverted = new File(Objects.requireNonNull(file.getOriginalFilename()));
    try (FileOutputStream fos = new FileOutputStream(fileConverted)) {
      fos.write(file.getBytes());
    } catch (IOException e) {
      log.info(e.getMessage());
    }

    return fileConverted;
  }
}
