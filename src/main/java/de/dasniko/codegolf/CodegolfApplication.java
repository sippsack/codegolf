package de.dasniko.codegolf;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.AccessToken;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Niko Köbler, http://www.n-k.de, @dasniko
 */
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

    @Bean
    @Scope(scopeName = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public AccessToken accessToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return ((KeycloakPrincipal) request.getUserPrincipal()).getKeycloakSecurityContext().getToken();
    }
}
