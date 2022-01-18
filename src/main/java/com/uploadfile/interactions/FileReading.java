package com.uploadfile.interactions;

import com.uploadfile.controllers.dtos.FileOutput;
import com.uploadfile.entities.types.FileType;
import com.uploadfile.repositories.FileRepository;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class FileReading {
  private final FileRepository fileRepository;

  public FileOutput execute(UUID id) {

    var fileOpt = fileRepository.findById(id);

    if (fileOpt.isPresent()) {
      var file = fileOpt.get();

      return FileOutput.builder()
          .id(file.getId())
          .title(file.getTitle())
          .link(file.getLink())
          .type(FileType.valueOf(file.getType()))
          .build();
    }

    return null;
  }
}
