package ru.example.emailservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
        "spring.mail.host=test",
        "spring.mail.port=465",
        "spring.mail.username=user",
        "spring.mail.password=password",
        "spring.kafka.bootstrap-servers=localhost:9092"
})
class EmailServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
