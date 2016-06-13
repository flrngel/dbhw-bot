package com.example.repository;



import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import com.example.dao.Users;
import com.example.dao.Items;
import com.example.dao.UserUsages;

@Configuration
public class MyCoolConfiguration extends RepositoryRestMvcConfiguration {
 
    @Override
    protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Users.class);
        config.exposeIdsFor(UserUsages.class);
        config.exposeIdsFor(Items.class);
    }
}