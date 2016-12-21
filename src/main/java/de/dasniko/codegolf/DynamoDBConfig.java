package de.dasniko.codegolf;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories(basePackages = "de.dasniko.codegolf")
public class DynamoDBConfig {

    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient();

        if (null != amazonDynamoDBEndpoint && !amazonDynamoDBEndpoint.isEmpty()) {
            amazonDynamoDB.setEndpoint(amazonDynamoDBEndpoint);
        }

        return amazonDynamoDB;
    }

}