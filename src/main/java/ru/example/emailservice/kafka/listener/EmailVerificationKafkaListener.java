package ru.example.emailservice.kafka.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.example.emailservice.kafka.payload.EmailVerificationEvent;
import ru.example.emailservice.service.EmailSenderService;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class EmailVerificationKafkaListener {

    private final EmailSenderService emailSenderService;

    @KafkaListener(topics = "${topic.email-verification}")
    public void receiveEmailVerificationEvent(@Payload EmailVerificationEvent event) {
        emailSenderService.sendEmail(event.email(), "email-verification", Map.of("code", event.code()));
    }
}
