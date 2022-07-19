package com.example.demo.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.MailService;

@Service
public class MailServiceImpl implements MailService {
  private JavaMailSender mailSender;

  @Autowired
  public MailServiceImpl(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  public void prepareAndSend(String recipient, String message, MultipartFile file) {
    MimeMessagePreparator messagePreparator = mimeMessage -> {
      MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
      messageHelper.setFrom("409401057@mail.fju.edu.tw");
      messageHelper.setTo(recipient);
      messageHelper.setSubject(message);
      messageHelper.setText(message);
      File newFile = new File(file.getOriginalFilename());
      file.transferTo(newFile);
      messageHelper.addAttachment("file.jpg", newFile);
      System.out.println(file + "message");
    };
    mailSender.send(messagePreparator);
  }

}
