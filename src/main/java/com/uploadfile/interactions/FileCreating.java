package com.uploadfile.interactions;

import com.uploadfile.controllers.dtos.FileInput;
import com.uploadfile.entities.File;
import com.uploadfile.entities.types.FileType;
import com.uploadfile.repositories.FileRepository;
import com.uploadfile.services.aws.StorageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class FileCreating {
  private final FileRepository fileRepository;
  private final StorageService storageService;

  public void execute(FileInput fileInput) {
    log.info("CREATING FILE TITLE - {}", fileInput.getTitle());

    var fileName = storageService.uploadFile(fileInput.getFile());
    var link = storageService.viewFile(fileName);
    var file =
        File.builder()
            .title(fileInput.getTitle())
            .link(link.toString())
            .type(FileType.IMAGE.name())
            .fileName(fileName)
            .build();

    fileRepository.save(file);

    log.info("CREATED FILE ID - {} - filename - {}", file.getId(), fileName);
  }
}
