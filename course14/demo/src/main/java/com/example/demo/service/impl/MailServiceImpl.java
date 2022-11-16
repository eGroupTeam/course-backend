package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.example.demo.service.MailService;

@Service
public class MailServiceImpl implements MailService {
  private JavaMailSender mailSender;

  @Autowired
  public MailServiceImpl(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  public void prepareAndSend(String recipient, String message) {
    MimeMessagePreparator messagePreparator = mimeMessage -> {
      MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
      messageHelper.setFrom("409401538@mail.fju.edu.tw");
      messageHelper.setTo(recipient);
      messageHelper.setSubject(message);
      messageHelper.setText(message);
    };
    mailSender.send(messagePreparator);
  }

}
