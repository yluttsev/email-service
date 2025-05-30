package ru.example.emailservice.kafka.payload;

public record EmailVerificationEvent(
        String email,
        String code
) {
}
