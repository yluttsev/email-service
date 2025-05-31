package ru.example.emailservice.kafka.payload;

public record EmailVerificationEvent(
        String email,
        int code
) {
}
