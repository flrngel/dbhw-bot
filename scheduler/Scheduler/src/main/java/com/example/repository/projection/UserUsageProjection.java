package com.example.repository.projection;

import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

import com.example.dao.Users;
import com.example.dao.UserUsages;

@Projection(name = "UserUsageProjection", types = UserUsages.class)
public interface UserUsageProjection {

	int getUserUsageId();

	String getWord();

	int getPrice();

	Date getDate();

	Users getUser();
}
