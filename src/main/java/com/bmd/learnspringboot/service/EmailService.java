package com.bmd.learnspringboot.service;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import jakarta.mail.internet.MimeMessage;
import org.thymeleaf.context.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    private final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    public EmailService(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Async
    public void sendResetEmail(String uname,String token) {
        String email = uname+"@cb.students.amrita.edu";
        String[] to = {email};
        String subject = "Request to reset password";
        String template = "reset"; // Name of your HTML email template file without the extension
        // Create a Thymeleaf context with any required variables for the template
        Context context = new Context();
        context.setVariable("resetUrl", "http://34.125.242.142:3000/resetpass?resetToken="+token);
        try {
            sendResetPasswordEmail(to, subject, template, context);
            logger.debug("Email sent successfully to : "+email);
        } catch (MessagingException e) {
            logger.debug("Failed to send email: " + e.getMessage());
        }
    }
    public void sendResetPasswordEmail(String[] to, String subject, String template, Context context) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        String htmlContent = templateEngine.process(template, context);
        helper.setText(htmlContent, true);
        javaMailSender.send(message);
    }
}
