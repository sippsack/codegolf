package de.dasniko.codegolf;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDynamoDBRepositories
public class CodegolfApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodegolfApplication.class, args);
    }

    @Bean
    public AmazonS3 amazonS3() {
        return new AmazonS3Client();
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient();
        amazonDynamoDB.setRegion(Region.getRegion(Regions.EU_WEST_1));
        return amazonDynamoDB;
    }
}
