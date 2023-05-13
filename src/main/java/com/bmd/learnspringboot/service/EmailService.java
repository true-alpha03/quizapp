package com.bmd.learnspringboot.service;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import jakarta.mail.internet.MimeMessage;
import org.thymeleaf.context.Context;


@Service
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Autowired
    public EmailService(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    public void sendResetEmail(String uname) {
        String[] to = {uname};
        String subject = "Request to reset password";
        String template = "reset"; // Name of your HTML email template file without the extension
        // Create a Thymeleaf context with any required variables for the template
        Context context = new Context();
        context.setVariable("resetUrl", "https://www.google.com/");
        try {
            sendResetPasswordEmail(to, subject, template, context);
            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            System.out.println("Failed to send email: " + e.getMessage());
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
