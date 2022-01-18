package com.uploadfile.interactions;

import com.uploadfile.controllers.dtos.FileOutput;
import com.uploadfile.entities.File;
import com.uploadfile.entities.types.FileType;
import com.uploadfile.repositories.FileRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class FileReading {
  private final FileRepository fileRepository;

  public List<FileOutput> execute() {
    var files = fileRepository.findAll();

    return files.stream().map(File::toOutput).collect(Collectors.toList());
  }

  public FileOutput execute(UUID id) {

    var fileOpt = fileRepository.findById(id);

    if (fileOpt.isPresent()) {
      var file = fileOpt.get();

      return file.toOutput();
    }

    return null;
  }
}
