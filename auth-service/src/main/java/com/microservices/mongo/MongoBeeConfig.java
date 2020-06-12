package com.microservices.mongo;

import com.github.mongobee.Mongobee;
import com.microservices.authservice.config.MongoProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.mongodb.core.MongoTemplate;

//@Configuration
//@DependsOn("mongoTemplate")
public class MongoBeeConfig {

    private static final String MONGODB_URL_FORMAT = "mongodb://%s:%d/%s";
    private static final String MONGODB_CHANGELOGS_PACKAGE = "com.microservices.authservice.config.mongodb.changelogs";

//    @Autowired
    private MongoProperties mongoProperties;

//    @Autowired
    private MongoTemplate mongoTemplate;

//    @Bean
    public Mongobee mongobee() {
        Mongobee runner = new Mongobee(String.format(MONGODB_URL_FORMAT,
                mongoProperties.getHost(),
                mongoProperties.getPort(),
                mongoProperties.getDatabase()));
        runner.setMongoTemplate(mongoTemplate);
        runner.setDbName(mongoProperties.getDatabase());
        runner.setChangeLogsScanPackage(MONGODB_CHANGELOGS_PACKAGE);
        return runner;
    }

}