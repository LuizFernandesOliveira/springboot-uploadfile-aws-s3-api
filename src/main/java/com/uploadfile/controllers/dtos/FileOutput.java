package com.uploadfile.controllers.dtos;

import com.uploadfile.entities.types.FileType;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FileOutput {
  private UUID id;
  private String title;
  private String link;
  private FileType type;
}
