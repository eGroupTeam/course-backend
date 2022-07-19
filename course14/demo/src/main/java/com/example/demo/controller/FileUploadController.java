package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.service.MailService;
import com.example.demo.service.StorageService;

@RestController
@CrossOrigin
public class FileUploadController {
  @Autowired
  private StorageService storageService;
  @Autowired
  private MailService mailService;

  @PostMapping("/file")
  public String handleFileUpload(@RequestParam("file") MultipartFile file) {
    System.out.println(file.getOriginalFilename());
    try {
      storageService.store(file);
      mailService.prepareAndSend("a109223017@mail.shu.edu.tw", "檔案已上傳");
    } catch (MailException e) {
      System.out.println(e);
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "email未能送出");

    }
    return "OK";

  }
}
