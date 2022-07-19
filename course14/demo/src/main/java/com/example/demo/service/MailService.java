package com.example.demo.service;

import org.springframework.mail.MailException;
import org.springframework.web.multipart.MultipartFile;

public interface MailService {
  public void prepareAndSend(String recipient, String message, MultipartFile file) throws MailException;

}
