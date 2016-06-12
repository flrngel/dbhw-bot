package com.example.repository;



import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import com.example.dao.User;
import com.example.dao.UserUsage;

@Configuration
public class MyCoolConfiguration extends RepositoryRestMvcConfiguration {
 
    @Override
    protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(User.class);
        config.exposeIdsFor(UserUsage.class);
    }
}