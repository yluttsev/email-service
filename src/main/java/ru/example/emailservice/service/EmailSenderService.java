package ru.example.emailservice.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailSenderService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String mailFrom;

    public void sendEmail(String email, String templateName, Map<String, String> variables) {
        String messageTemplate = buildMessageTemplate(templateName, variables);
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
            messageHelper.setTo(email);
            messageHelper.setFrom(mailFrom);
            messageHelper.setText(messageTemplate, true);
            messageHelper.setSubject("Подтверждение email");
            mailSender.send(message);
            log.info("Письмо отправлено по адресу: {}", email);
        } catch (MessagingException e) {
            log.error("Ошибка отправки письма по адресу: {}. Сообщение: {}", email, e.getMessage());
        }
    }

    private String buildMessageTemplate(String templateName, Map<String, String> variables) {
        Context context = new Context();
        variables.forEach(context::setVariable);
        return templateEngine.process(templateName, context);
    }
}
