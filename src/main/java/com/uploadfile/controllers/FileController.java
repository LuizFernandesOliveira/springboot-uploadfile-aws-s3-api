package com.uploadfile.controllers;

import com.uploadfile.controllers.dtos.FileInput;
import com.uploadfile.controllers.dtos.FileOutput;
import com.uploadfile.interactions.FileCreating;
import com.uploadfile.interactions.FileDeleting;
import com.uploadfile.interactions.FileReading;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/files")
@AllArgsConstructor
public class FileController {
  private final FileCreating fileCreating;
  private final FileReading fileReading;
  private final FileDeleting fileDeleting;

  @PostMapping("/{title}")
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@RequestParam(value = "file") MultipartFile file, @PathVariable String title) {
    var fileInput = FileInput.builder().file(file).title(title).build();
    fileCreating.execute(fileInput);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<FileOutput> findAll() {
    return fileReading.execute();
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public FileOutput find(@PathVariable UUID id) {
    return fileReading.execute(id);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void delete(@PathVariable UUID id) {
    fileDeleting.execute(id);
  }
}
