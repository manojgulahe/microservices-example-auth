package com.microservices.authservice.config.mongodb.changelogs;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.github.mongobee.changeset.ChangeSet;
import com.microservices.entity.AuthClientDetails;
import com.microservices.entity.Authorities;
import com.microservices.entity.User;

public class InitialValuesChangeLog {
	@ChangeSet(order = "001", id = "insertBrowserClientDetails", author = "Marcus Hert Da Corégio")
    public void insertBrowserClientDetails(MongoTemplate mongoTemplate) {
        AuthClientDetails browserClientDetails = new AuthClientDetails();
        browserClientDetails.setClientId("browser");
        browserClientDetails.setClientSecret("$2a$10$fWNTd3H.u7G/aNROVQSifebOkZ2xzU5nUPOCI2Ld42M8E25/ljJqK");
        browserClientDetails.setScopes("ui");
        browserClientDetails.setGrantTypes("refresh_token,password");

        System.out.println("InitialValuesChangeLog.insertBrowserClientDetails()");
        mongoTemplate.save(browserClientDetails);
    }

    @ChangeSet(order = "002", id = "insertUserToTestAuthentication", author = "Marcus Hert Da Corégio")
    public void insertUserToTestAuthentication(MongoTemplate mongoTemplate) {
        Set<Authorities> authorities = new HashSet<>();
        authorities.add(Authorities.ROLE_USER);

        User user = new User();
        user.setActivated(true);
        user.setAuthorities(authorities);
        user.setPassword("$2a$10$fWNTd3H.u7G/aNROVQSifebOkZ2xzU5nUPOCI2Ld42M8E25/ljJqK");
        user.setUsername("randomuser");

        System.out.println("InitialValuesChangeLog.insertUserToTestAuthentication()");
        mongoTemplate.save(user);
    }

    @ChangeSet(order = "003", id = "insertAccountServiceClientDetails", author = "Marcus Hert Da Corégio")
    public void insertAccountServiceClientDetails(MongoTemplate mongoTemplate) {
        AuthClientDetails accountServiceClientDetails = new AuthClientDetails();
        accountServiceClientDetails.setClientId("account-service");
        accountServiceClientDetails.setClientSecret("$2a$10$fWNTd3H.u7G/aNROVQSifebOkZ2xzU5nUPOCI2Ld42M8E25/ljJqK");
        accountServiceClientDetails.setScopes("server");
        accountServiceClientDetails.setGrantTypes("refresh_token,client_credentials");

        System.out.println("InitialValuesChangeLog.insertAccountServiceClientDetails()");
        mongoTemplate.save(accountServiceClientDetails);
    }

    
}
