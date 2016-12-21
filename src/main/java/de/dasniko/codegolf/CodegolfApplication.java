package de.dasniko.codegolf;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CodegolfApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodegolfApplication.class, args);
    }

    @Bean
    public AmazonS3 amazonS3() {
        return new AmazonS3Client();
    }
}
