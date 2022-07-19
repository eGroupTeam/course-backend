package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.StorageService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class StorageServiceImpl implements StorageService {

  @Override
  public void store(MultipartFile file) {
    try {
      Path p = Paths.get("src/main/resources/static/test.jpg");
      Files.copy(file.getInputStream(), p, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      System.out.println(e);
    }
  }
}
