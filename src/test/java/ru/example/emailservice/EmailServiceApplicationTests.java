package ru.example.emailservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
        "spring.mail.host=test",
        "spring.mail.port=465",
        "spring.mail.username=user",
        "spring.mail.password=password",
        "spring.kafka.bootstrap-servers=localhost:9092",
        "eureka.instance.hostname=app",
        "eureka.instance.non-secure-port=8080",
        "eureka.client.service-url.defaultZone=localhost:8761"
})
class EmailServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
