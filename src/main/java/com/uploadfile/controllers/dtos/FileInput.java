package com.uploadfile.controllers.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
public class FileInput {
  private String title;
  private MultipartFile file;
}
