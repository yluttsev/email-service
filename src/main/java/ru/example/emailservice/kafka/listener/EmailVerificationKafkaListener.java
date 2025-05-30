package ru.example.emailservice.kafka.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.example.emailservice.kafka.payload.EmailVerificationEvent;
import ru.example.emailservice.service.EmailSenderService;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailVerificationKafkaListener {

    private final EmailSenderService emailSenderService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${topic.email-verification}")
    public void receiveEmailVerificationEvent(@Payload String event) {
        try {
            EmailVerificationEvent emailVerificationEvent = objectMapper.readValue(event, EmailVerificationEvent.class);
            emailSenderService.sendEmail(emailVerificationEvent.email(), "email-verification", Map.of("code", emailVerificationEvent.code()));
        } catch (JsonProcessingException e) {
            log.error("Ошибка преобразования JSON: {}", e.getMessage());
        }
    }
}
