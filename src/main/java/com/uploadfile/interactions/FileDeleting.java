package com.uploadfile.interactions;

import com.uploadfile.repositories.FileRepository;
import com.uploadfile.services.aws.StorageService;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class FileDeleting {
  private final FileRepository fileRepository;
  private final StorageService storageService;

  public void execute(UUID id) {
    var fileOpt = fileRepository.findById(id);

    if (fileOpt.isPresent()) {
      storageService.deleteFile(fileOpt.get().getFileName());
      fileRepository.deleteById(id);

      log.info("DELETED FILE ID - {}", id);
    }
  }
}
