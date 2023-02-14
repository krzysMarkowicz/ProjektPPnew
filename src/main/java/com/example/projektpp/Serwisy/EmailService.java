package com.example.projektpp.Serwisy;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void send(String to, String title, String contents) {

        MimeMessage mail = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(to);
            helper.setFrom("jakubkrulikowski7@gmail.com");
            helper.setSubject(title);
            helper.setText(contents);
        } catch (MessagingException e) {
            e.printStackTrace();
        } finally {
        }
        javaMailSender.send(mail);
    }
}
