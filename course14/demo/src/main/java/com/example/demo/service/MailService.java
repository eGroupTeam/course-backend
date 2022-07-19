package com.example.demo.service;

import org.springframework.mail.MailException;

public interface MailService {
  public void prepareAndSend(String recipient, String message) throws MailException;

}
